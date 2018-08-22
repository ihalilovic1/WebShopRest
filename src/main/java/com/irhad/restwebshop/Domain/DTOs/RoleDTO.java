package com.irhad.restwebshop.Domain.DTOs;

import com.irhad.restwebshop.Domain.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private int id;
    private String name;
    private String description;

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
    }

    public static Set<RoleDTO> getRoleDTOSet(Set<Role> roleSet) {
        Set<RoleDTO> roleDTOS = new HashSet<>();
        for (Role role : roleSet) {
            roleDTOS.add(new RoleDTO(role));
        }
        return  roleDTOS;
    }
}
