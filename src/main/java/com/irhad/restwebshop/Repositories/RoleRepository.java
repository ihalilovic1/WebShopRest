package com.irhad.restwebshop.Repositories;

import com.irhad.restwebshop.Domain.Models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}