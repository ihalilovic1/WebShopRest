package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.DTOs.RegisterDTO;
import com.irhad.restwebshop.Domain.DTOs.UserDTO;
import com.irhad.restwebshop.Domain.Models.User;

import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    UserDTO addUser(UserDTO userDTO);

    UserDTO addUser(User user);

    UserDTO addUser(String firstName, String lastName, String username, String password, String email);

    Optional<User> findById(UUID uuid);

    Optional<User> findByUsernamePassword(String username, String password);

    Optional<User> findByUsername(String username);

}
