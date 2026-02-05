package com.ulises.possystem.service;

import com.ulises.possystem.dto.user.UserCreateDTO;
import com.ulises.possystem.dto.user.UserDTO;
import com.ulises.possystem.entities.User;
import com.ulises.possystem.enums.UserType;
import com.ulises.possystem.repositories.UserRepository;
import com.ulises.possystem.services.UserServiceManager;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceManager userService;

    @Test
    void shouldCreateUserSuccessfully() {
        // Arrange
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setName("Juan");
        createDTO.setSurname("Perez");
        createDTO.setIdentification("12345678");
        createDTO.setUserType(UserType.CUSTOMER);

        User savedEntity = new User();
        savedEntity.setId(1L);
        savedEntity.setName("Juan");
        savedEntity.setSurname("Perez");
        savedEntity.setMemberIdentification("12345678");
        savedEntity.setActive(true);


        when(userRepository.save(any(User.class))).thenReturn(savedEntity);

        // Act
        UserDTO result = userService.save(createDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Juan", result.getName());
        assertEquals("Perez", result.getSurname());
        assertEquals("12345678", result.getMemberIdentification());
        assertTrue(result.isActive());

        verify(userRepository, times(1)).save(any(User.class));
    }
}
