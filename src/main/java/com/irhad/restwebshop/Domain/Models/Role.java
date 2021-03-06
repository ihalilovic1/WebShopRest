package com.irhad.restwebshop.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "roles")
    Set<User> users = new HashSet<>();

    public Role(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + getName();
    }
}
