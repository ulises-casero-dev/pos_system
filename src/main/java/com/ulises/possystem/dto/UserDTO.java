package com.ulises.possystem.dto;

import com.ulises.possystem.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String memberIdentification;
    private String celphone;
    private String email;
    private UserType type;
    private boolean active;
}
