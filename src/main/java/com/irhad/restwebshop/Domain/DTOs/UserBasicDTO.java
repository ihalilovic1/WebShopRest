package com.irhad.restwebshop.Domain.DTOs;

import com.irhad.restwebshop.Domain.Models.User;
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
public class UserBasicDTO {
    private UUID id;
    private String userName;
    private Date createdAt;

    public UserBasicDTO(User user) {
        this(user.getId(), user.getUsername(), user.getCreatedAt());
    }
}
