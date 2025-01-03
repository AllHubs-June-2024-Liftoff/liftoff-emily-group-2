package com.notsauce.parkd.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrationFormDTO extends LoginFormDTO{

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    @Size(min = 5, max = 20, message = "Invalid Password. Must be 5-20 characters long.")
    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
