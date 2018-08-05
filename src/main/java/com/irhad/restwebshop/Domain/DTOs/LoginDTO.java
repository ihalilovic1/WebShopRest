package com.irhad.restwebshop.Domain.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
