package com.notsauce.parkd.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Blog extends AbstractEntity {

    @Column(length = 16777215)
    private String blogPost;
    @ManyToOne
    private User author;
    @ManyToOne
    private Park subject;


}
