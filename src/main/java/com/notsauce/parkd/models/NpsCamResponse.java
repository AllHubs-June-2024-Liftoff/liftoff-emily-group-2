package com.notsauce.parkd.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
// TODO: Following Code Has Issues Probably 1x16:

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NpsCamResponse {
    private String total;
    private String limit;
    private String start;
    private List<Webcam> data;

//    private String parkCode; //array of strings
//    private String stateCode; //array of strings
//    private String id;
//    private String limit; // Default is 50, actually an integer??
//    private String start; //also an integer, default is 0
//    private List<Webcam> data;
}
