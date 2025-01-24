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
public class ParkWebcamImage {

    private String url;
    private String credit;
    private String altText;
    private String title;
    private String description;
    private String caption;
    //There is also a Crops object with Aspect Ratio and URL that I'm avoiding adding initially here

}
