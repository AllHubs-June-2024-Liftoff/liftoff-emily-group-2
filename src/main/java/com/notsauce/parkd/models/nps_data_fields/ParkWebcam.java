package com.notsauce.parkd.models.nps_data_fields;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "webcams")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkWebcam {

    private String id;
    private String url;
    private String title;
    private String description;
    private String relatedParks; //Format: [{states:, parkCode:, designation:, fullName:, url:, name:}]
    private String status;
    private String tags;
    private String latitude;
    private String longitude;

}
