package com.notsauce.parkd.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review extends AbstractEntity {

    @Transient
    private List<Integer> options = Arrays.asList(1, 2, 3, 4, 5);
    private int review;
    @ManyToOne
    private User userReview;
    @ManyToOne
    private Park parkReview;

    public Object getParkReview() {
        return parkReview;
    }
}
