package com.notsauce.parkd.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class APNatPark {
    @Transient
    @JsonInclude
    private String states;

    @Transient
    @JsonInclude
    private String fullName;

    @Transient
    @JsonInclude
    private String url;

    @Transient
    @JsonInclude
    private String parkCode;

    @Transient
    @JsonInclude
    private String designation;

    @Transient
    @JsonInclude
    private String name;
}
