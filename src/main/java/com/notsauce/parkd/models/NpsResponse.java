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
public class NpsResponse {
    private String total;
    private String limit; // Default is 50, need at least 63 for 63 National Parks
    private String start;
    private List<Park> data;
}
