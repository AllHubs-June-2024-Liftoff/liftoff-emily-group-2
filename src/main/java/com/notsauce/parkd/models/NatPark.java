package com.notsauce.parkd.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Transient;

public class NatPark extends Park {
    @Transient
    @JsonInclude
    private String designation;
}
