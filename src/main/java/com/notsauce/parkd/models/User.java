package com.notsauce.parkd.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "registered_users")
public class User extends AbstractEntity {



    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "pw_hash")
    private String pwHash;

   


    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "userReview")
    private List<Review> reviews;

    @OneToMany(mappedBy = "author")
    private List<Blog> blogPosts;

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean isMatchingPassword(String password){
        return encoder.matches(password,pwHash);
    }

    public List<Review> getReviews() {
        return reviews;
    }
}

// For Vincent mainly:
// Would like a # of logins counter/data point
// Also a last logged in tracker (though granularity only necessary to 1-day intervals)