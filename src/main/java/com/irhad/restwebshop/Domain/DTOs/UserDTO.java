package com.irhad.restwebshop.Domain.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;
}
