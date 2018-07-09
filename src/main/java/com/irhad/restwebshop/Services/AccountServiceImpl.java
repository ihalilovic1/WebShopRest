package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login() {
        return userRepository.findById(UUID.fromString("76ebe2b1-576f-4a7e-911a-38237f2c319d")).get();
    }
}
