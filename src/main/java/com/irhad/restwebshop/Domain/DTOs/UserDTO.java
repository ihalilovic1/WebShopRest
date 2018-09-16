package com.irhad.restwebshop.Domain.DTOs;

import com.irhad.restwebshop.Domain.Models.Role;
import com.irhad.restwebshop.Domain.Models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
    private Set<RoleDTO> roles;

    public UserDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getCreatedAt(),
                user.getUpdatedAt(), user.getEnabled(), RoleDTO.getRoleDTOSet(user.getRoles()));
    }

    public User createUserObject() {
        Set<Role> roleSet = new HashSet<>();
        for (RoleDTO roleDTO : roles) {
            roleSet.add(new Role(roleDTO.getId(), roleDTO.getName(), roleDTO.getDescription()));
        }
        return User.builder().createdAt(createdAt).email(email).enabled(enabled)
                .firstName(firstName).lastName(lastName).id(id).username(userName).updatedAt(updatedAt)
                .build();
    }
}
