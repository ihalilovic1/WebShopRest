package com.irhad.restwebshop.Repositories;

import com.irhad.restwebshop.Domain.Models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}