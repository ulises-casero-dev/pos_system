package com.ulises.possystem.services;

import com.ulises.possystem.dto.order.OrderCreateDTO;
import com.ulises.possystem.dto.order.OrderDTO;
import com.ulises.possystem.dto.orderItem.OrderItemCreateDTO;
import com.ulises.possystem.entities.Order;
import com.ulises.possystem.entities.OrderItem;
import com.ulises.possystem.entities.Product;
import com.ulises.possystem.entities.User;
import com.ulises.possystem.enums.OrderState;
import com.ulises.possystem.enums.UserType;
import com.ulises.possystem.exception.ResourceNotFoundException;
import com.ulises.possystem.repositories.OrderRepository;
import com.ulises.possystem.repositories.ProductRepository;
import com.ulises.possystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private ModelMapper modelMapper;

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = this.repository.findAll();
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

        User user = this.userRepository.findById(orderCreateDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        orderEntity.setUser(user);
        orderEntity.setDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemCreateDTO orderItemDto : orderCreateDto.getOrderItems()) {
            Product product = this.productRepository.findById(orderItemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

            OrderItem orderItemEntity = new OrderItem();

            orderItemEntity.setOrder(orderEntity);
            orderItemEntity.setProduct(product);
            orderItemEntity.setQuantity(orderItemDto.getQuantity());
            orderItemEntity.setUnitPrice(product.getPrice());
            orderItemEntity.calculateSubTotal();

            orderItems.add(orderItemEntity);
        }

        //Funcion aplicadora de descuento?
        //Aplicar descuento
        if(user.getUserType() == UserType.EMPLOYEE){
            //Obtengo descuento empleado
            // compruebo cantidad acumulada en descuentos para ese empleado
            //Si cantidad no supera monto:
                //Aplicao descuento empleado, si llega al limite el descuento se aplica hasta cantiadad permitida
            //Si alcanzo monto, no aplica descuento
        } else { // Camino  de cliente
            //Obtener si existe descuento activo
            //Si existe desceunto y aplica a la compra:
                //Aplicar descuento hasta cantidad permitida
            //Si no existe descuento no se hace nada
        }

        orderEntity.setOrderItems(orderItems);

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

