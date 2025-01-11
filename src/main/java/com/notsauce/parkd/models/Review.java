package com.notsauce.parkd.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Review extends AbstractEntity {
    private String parkCodeId;
    private String userId;
    private int review;
}
