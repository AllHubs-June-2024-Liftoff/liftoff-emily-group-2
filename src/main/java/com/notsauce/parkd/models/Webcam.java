package com.notsauce.parkd.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.notsauce.parkd.models.nps_data_fields.ParkWebcam;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Webcam {
    @Id
    @Column(unique = true)
    private String id;
    private String url;
    @Transient
    @JsonInclude
    private String title;
    @Transient
    @JsonInclude
    private String description;
    @Transient
    @JsonInclude
    private List<ParkWebcam> webcams;

    //Can Include Latitude/Longitude
}