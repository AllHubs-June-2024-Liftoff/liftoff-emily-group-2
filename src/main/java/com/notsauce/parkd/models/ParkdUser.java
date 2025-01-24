package com.notsauce.parkd.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity // Added to resolve compilation error
@Data
@EqualsAndHashCode(callSuper = true) // Added to resolve compilation error
@Table(name="parkd_users")
public class ParkdUser extends AbstractEntity{

    
    private String username;
    private String email;



}
