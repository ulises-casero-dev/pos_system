package com.ulises.possystem.services;

import com.ulises.possystem.discount.service.DiscountServiceManager;
import com.ulises.possystem.discount.strategy.DiscountStrategy;
import com.ulises.possystem.dto.discount.DiscountDTO;
import com.ulises.possystem.dto.order.OrderCreateDTO;
import com.ulises.possystem.dto.order.OrderDTO;
import com.ulises.possystem.dto.orderItem.OrderItemCreateDTO;
import com.ulises.possystem.entities.Order;
import com.ulises.possystem.entities.OrderItem;
import com.ulises.possystem.entities.Product;
import com.ulises.possystem.entities.User;
import com.ulises.possystem.enums.DiscountType;
import com.ulises.possystem.enums.OrderState;
import com.ulises.possystem.exception.ResourceNotFoundException;
import com.ulises.possystem.helper.ItemsDiscountResult;
import com.ulises.possystem.helper.UserDiscountResult;
import com.ulises.possystem.repositories.OrderRepository;
import com.ulises.possystem.repositories.ProductRepository;
import com.ulises.possystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceManager implements OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DiscountServiceManager discountServiceManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Map<String, DiscountStrategy> discountStrategies;

    private UserDiscountResult applyUserDiscount(User user, BigDecimal totalPrice) {

        String strategyKey = user.getUserType().name().toLowerCase(Locale.ROOT) + "DiscountStrategy";

        DiscountStrategy strategy = discountStrategies.get(strategyKey);

        if (strategy == null) {
            return new UserDiscountResult(BigDecimal.ZERO, totalPrice);
        }

        return strategy.applyDiscount(user, totalPrice);
    }

    private ItemsDiscountResult applyItemsDiscunts(OrderCreateDTO orderCreateDto, Order order) {
        List<DiscountDTO> GeneralDiscounts = this.discountServiceManager.findByDiscountType(DiscountType.GENERAL);

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal totalDiscount = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItemCreateDTO orderItemDto : orderCreateDto.getOrderItems()) {
            Product product = this.productRepository.findById(orderItemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

            OrderItem orderItemEntity = new OrderItem();

            orderItemEntity.setOrder(order);
            orderItemEntity.setProduct(product);
            orderItemEntity.setQuantity(orderItemDto.getQuantity());
            orderItemEntity.setUnitPrice(product.getPrice());

            Optional<DiscountDTO> productDiscount = GeneralDiscounts.stream()
                    .filter(discount -> discount.getProductId() != null && discount.getProductId().equals(product.getId()))
                    .findFirst();

            BigDecimal subTotal = BigDecimal.ZERO;

            if (productDiscount.isPresent()) {
                DiscountDTO discount = productDiscount.get();
                BigDecimal discountUnitPrice = discount.applyDiscount(product.getPrice());

                BigDecimal itemDiscount = product.getPrice().subtract(discountUnitPrice)
                        .multiply(BigDecimal.valueOf(orderItemDto.getQuantity()));

                totalDiscount = totalDiscount.add(itemDiscount);

                subTotal = discountUnitPrice.multiply(BigDecimal.valueOf(orderItemDto.getQuantity()));
            } else {
                subTotal = product.getPrice().multiply(BigDecimal.valueOf(orderItemDto.getQuantity()));
            }

            orderItemEntity.setSubTotal(subTotal);
            totalPrice = totalPrice.add(subTotal);

            orderItems.add(orderItemEntity);
        }

        order.setOrderItems(orderItems);

        return new ItemsDiscountResult(totalDiscount, totalPrice);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = this.repository.findAllWithDetails();
        return orders.stream()
                .map(order -> this.modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(Long id) {
        Order orderEntity = this.repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Order not found."));

        return this.modelMapper.map(orderEntity, OrderDTO.class);
    }

    @Override
    public OrderDTO save(OrderCreateDTO orderCreateDto) {
        Order orderEntity = new Order();

        orderEntity.setDate(LocalDateTime.now());

        ItemsDiscountResult itemsDiscountResult = applyItemsDiscunts(orderCreateDto, orderEntity);

        BigDecimal totalDiscount = BigDecimal.ZERO;

        if (orderCreateDto.getUserId() != null) {
            User user = this.userRepository.findById(orderCreateDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found."));

            orderEntity.setUser(user);

            UserDiscountResult userDiscountResult = applyUserDiscount(user, itemsDiscountResult.getTotalAfterItemsDiscounts());

            totalDiscount = userDiscountResult.getUserDiscountAmount().add(itemsDiscountResult.getItemsDiscountAmount());
            orderEntity.setTotalPrice(userDiscountResult.getTotalAfterUserDiscount());

        } else {
            totalDiscount = itemsDiscountResult.getItemsDiscountAmount();
            orderEntity.setTotalPrice(itemsDiscountResult.getTotalAfterItemsDiscounts());
        }

        orderEntity.setTotalDiscount(totalDiscount);

        orderEntity.setState(OrderState.CREATED);
        Order savedOrder = this.repository.save(orderEntity);

        return this.modelMapper.map(savedOrder, OrderDTO.class);
    }

    @Override
    public  OrderDTO update(Long id, OrderDTO orderDto) {
        Order orderEntity = this.repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Order not found."));

        orderEntity.setState(orderDto.getState());
        orderEntity.setTotalDiscount(orderDto.getTotalDiscount());

        Order updatedOrder = this.repository.save(orderEntity);

        return this.modelMapper.map(updatedOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO cancelOrder(Long id)  {
        Order orderEntity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found."));

        orderEntity.setCancelated(true);

        Order orderCanceled = this.repository.save(orderEntity);

        return this.modelMapper.map(orderCanceled, OrderDTO.class);
    }
}

