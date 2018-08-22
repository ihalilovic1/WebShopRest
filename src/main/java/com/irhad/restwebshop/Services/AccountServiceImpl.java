package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.DTOs.RegisterDTO;
import com.irhad.restwebshop.Domain.DTOs.UserDTO;
import com.irhad.restwebshop.Domain.Models.Role;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Repositories.RoleRepository;
import com.irhad.restwebshop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        userDTO.setId(UUID.randomUUID());
        addUser(userDTO.createUserObject());

        return userDTO;
    }

    @Override
    public UserDTO addUser(User user) {
        try {
            return new UserDTO(userRepository.save(user));
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public UserDTO addUser(String firstName, String lastName, String username, String password, String email) {
        Role regularRole = roleRepository.findByName("regular");
        User newUser = new User(UUID.randomUUID(), firstName, lastName, password, email, username, true);
        newUser.getRoles().add(regularRole);
        return addUser(newUser);
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public Optional<User> findByUsernamePassword(String username, String password) {
        try {
            return Optional.ofNullable(userRepository.findByUsernameAndPassword(username, password));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.ofNullable(userRepository.findByUsername(username));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
