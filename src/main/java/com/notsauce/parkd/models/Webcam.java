package com.notsauce.parkd.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.notsauce.parkd.models.nps_data_fields.ParkWebcamImage;
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
//    @Transient
//    @JsonInclude
//    private String relatedParks; //Format: [{states:, parkCode:, designation:, fullName:, url:, name:}]
    @Transient
    @JsonInclude
    private Boolean isStreaming;
    @Transient
    @JsonInclude
    private String statusMessage;
    @Transient
    @JsonInclude
    private String status; //Status can be "Active" or ???
//    @Transient
//    @JsonInclude
//    private String tags;
    @Transient
    @JsonInclude
    private List<ParkWebcamImage> images;


    //Can Include Latitude/Longitude
}

//private String id; //int or string since ParkImage may or may not be used properly?
//private String url;
//private String title;
//private String description;
//private String relatedParks; //Format: [{states:, parkCode:, designation:, fullName:, url:, name:}]
//private String status;
//private String tags;
//private String latitude;
//private String longitude;