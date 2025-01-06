package com.notsauce.parkd.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginFormDTO {

    @NotNull(message = "Username is required.")
    @NotBlank(message = "Username is required.")
    @Size(min = 4, max = 15, message = "Invalid username. Must be 4-15 characters long.")
    private String username;

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    @Size(min = 5, max = 20, message = "Invalid Password. Must be 5-20 characters long.")
    private String password;

}
