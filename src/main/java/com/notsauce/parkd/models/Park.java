package com.notsauce.parkd.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.notsauce.parkd.models.nps_data_fields.ParkImage;
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
public class Park {
    @Id
    @Column(unique = true)
    private String parkCode;

    @Transient
    @JsonInclude
    private String url;
    private String fullName;
    @Transient
    @JsonInclude
    private String description;
    @Transient
    @JsonInclude
    private String latitude;
    @Transient
    @JsonInclude
    private String longitude;
    @Transient
    @JsonInclude
    private String latLong;
    private String states;
    @Transient
    @JsonInclude
    private List<ParkImage> images;

}
