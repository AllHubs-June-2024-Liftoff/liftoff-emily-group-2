package com.notsauce.parkd.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "userReview")
    private List<Review> reviews;

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean isMatchingPassword(String password){
        return encoder.matches(password,pwHash);
    }

}

// For Vincent mainly:
// Would like a # of logins counter/data point
// Also a last logged in tracker (though granularity only necessary to 1-day intervals)