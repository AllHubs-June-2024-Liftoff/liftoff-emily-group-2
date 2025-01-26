package com.notsauce.parkd.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Blog extends AbstractEntity {

    private String blogPost;
    private User author;
    private Park subject;


}
