package com.irhad.restwebshop.Controllers;

import com.irhad.restwebshop.Domain.DTOs.LoginDTO;
import com.irhad.restwebshop.Domain.DTOs.RegisterDTO;
import com.irhad.restwebshop.Domain.DTOs.UserDTO;
import com.irhad.restwebshop.Domain.Models.User;
import com.irhad.restwebshop.Services.AccountService;
import com.irhad.restwebshop.Services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    UserAuthenticationService authentication;

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    @ResponseBody
    public UserDTO register(@RequestBody @Valid RegisterDTO model) {
        UserDTO user = accountService.addUser(model.getFirstName(), model.getLastName(), model.getUserName(),
                model.getPassword(), model.getEmail());

        return user;
    }

    @RequestMapping(value = "/GetCurrentUser", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getCurrentUser(final HttpServletRequest request) {
        final String param = ofNullable(request.getHeader(AUTHORIZATION))
                .orElse(request.getParameter("t"));

        final String token = removeStart(param, "Bearer").trim();

        User user = authentication.findByToken(token).get();
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    String login(
            @RequestBody @Valid LoginDTO model) {
        return authentication
                .login(model.getUsername(), model.getPassword())
                .orElseThrow(() -> new RuntimeException("invalid username and/or password"));
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    boolean logout(@AuthenticationPrincipal final User user) {
        authentication.logout(user);
        return true;
    }
}
