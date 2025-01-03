package com.notsauce.parkd.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//@Getter
//@Setter
//@NoArgsConstructor
@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean isMatchingPassword(String password){
        return encoder.matches(password,pwHash);
    }

}
