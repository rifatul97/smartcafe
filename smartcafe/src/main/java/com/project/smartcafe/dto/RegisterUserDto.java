package com.project.smartcafe.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterUserDto {

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String username;

    private String email;

    @NotNull
    private String password;

    public RegisterUserDto(@NotNull String firstname,
                           @NotNull String lastname,
                           @NotNull String username,
                           @NotNull String email,
                           @NotNull String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
