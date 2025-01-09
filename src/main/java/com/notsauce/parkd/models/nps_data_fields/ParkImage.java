package com.notsauce.parkd.models.nps_data_fields;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "images")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkImage {

    private int id;
    private String credit;
    private String title;
    private String altText;
    private String caption;
    private String url;

}