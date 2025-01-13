package com.notsauce.parkd.models;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Comment extends AbstractEntity {

    private String parkCodeId;
    @ManyToOne
    private String user;
    private String comment;
    private String parkCode;


}
