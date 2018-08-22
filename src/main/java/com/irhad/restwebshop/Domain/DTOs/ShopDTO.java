package com.irhad.restwebshop.Domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    private UUID id;
    private String name;
    private String description;
    private String adress;
    private UserDTO owner;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;
}
