package com.irhad.restwebshop.Repositories;

import com.irhad.restwebshop.Domain.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    //User findByUserName(String username, String password);
}
