package com.irhad.restwebshop.Services;

import com.irhad.restwebshop.Domain.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private AccountService accountService;

    @Override
    public Optional<String> login(String username, String password) {
        Optional<User> user = accountService.findByUsernamePassword(username, password);
        if(user.isPresent())
            return Optional.of(user.get().getId().toString());
        else
            return Optional.empty();
    }

    @Override
    public Optional<User> findByToken(String token) {
        return accountService.findById(UUID.fromString(token));
    }

    @Override
    public void logout(User user) {

    }
}
