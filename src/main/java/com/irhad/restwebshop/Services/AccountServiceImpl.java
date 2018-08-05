package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.DTOs.RegisterDTO;
import com.irhad.restwebshop.Domain.DTOs.UserDTO;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        userDTO.setId(UUID.randomUUID());
        addUser(userDTO.createUserObject());

        return userDTO;
    }

    @Override
    public UserDTO addUser(User user) {
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO addUser(String firstName, String lastName, String username, String password, String email) {
        return addUser(new User(UUID.randomUUID(), firstName, lastName, password, email, username, new Date(), null, true));
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

}
