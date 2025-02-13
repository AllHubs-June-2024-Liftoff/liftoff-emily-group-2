package com.notsauce.parkd.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class NpsActivitiesParksResponse {
    private String total;
    private String limit;
    private String start;
    private List<ActivitiesParks> data;
}
