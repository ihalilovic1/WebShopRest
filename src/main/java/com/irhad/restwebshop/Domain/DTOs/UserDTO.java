package com.irhad.restwebshop.Domain.DTOs;

import com.irhad.restwebshop.Domain.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;

    public User createUserObject() {
        return new User(id, firstName, lastName, "", email, userName, createdAt, updatedAt, enabled);
    }

    public UserDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getCreatedAt(),
                user.getUpdatedAt(), user.getEnabled());
    }
}
