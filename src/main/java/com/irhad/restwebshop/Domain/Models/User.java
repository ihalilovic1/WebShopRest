package com.irhad.restwebshop.Domain.Models;


import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter @Setter
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;
}
