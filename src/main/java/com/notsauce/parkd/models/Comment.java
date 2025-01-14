package com.notsauce.parkd.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Comment extends AbstractEntity {
    @ManyToOne
    private Park park;
    @ManyToOne
    private User user;
    private String input;

}
