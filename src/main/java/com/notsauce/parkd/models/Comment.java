package com.notsauce.parkd.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Comment extends AbstractEntity {

    private String parkCodeId;
    @ManyToOne
    private User user;
    private String comment;
    private String parkCode;


}
