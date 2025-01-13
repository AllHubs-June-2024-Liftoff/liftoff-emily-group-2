package com.notsauce.parkd.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.notsauce.parkd.models.nps_data_fields.ParkImage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Park {
    @Id
    @Column(unique = true)
    private String parkCode;
    private String url;
    private String fullName;
    @Transient
    @JsonInclude
    private String designation;
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

    @JsonIgnore
    @OneToMany(mappedBy = "park")
    private List<Comment> comments;

}

/*

Basic Park Data is structured in JSON

Example:

{
          "states": "ME",
          "parkCode": "acad",
          "designation": "National Park",
          "fullName": "Acadia National Park",
          "url": "https://www.nps.gov/acad/index.htm",
          "name": "Acadia"
        }
Things to note:

1) Some parks can be in multiple states if translating into an object, listed as comma separated 2-letter abbreviations.
2) parkCode is a 4-letter unique identifier string
3) There is also seemingly also a unique alphanumeric id associated with each park
4) Designation is important for filtering - the NPS API also contains data on other sites like "National Monument"
"National Recreation Area", "National Memorial", "National Historical Site", etc. We
5) url might useful - just links to the official landing page for each park
6) name vs. fullName is an interesting distinction - name could be used to fill in certain places (card titles?)
while fullName can be used in others (full pages/exploding elements for each park?)

"weatherInfo" - simple description string of the typical weather

Park activities also come in JSON with a unique alphanumeric id and a name string:

Example:

 {
      "id": "B12FAAB9-713F-4B38-83E4-A273F5A43C77",
      "name": "Climbing"
    }

Seemingly there are 1418 activities/descriptors so we definitely should limit to a subset that makes sense
-- Optimal way to address this might be (either directly through API if possible or caching data) to find X most popular
activities based on frequency via algorithm across all parks and populate those as tags for each park.

Could further develop to indicate most unique activity per park (shares with fewest other parks?)

Images example structure:

"images": [
          {
            "credit": "NPS/Jim Peaco",
            "altText": "Crowd watching Aurum Geyser erupt",
            "title": "Aurum Geyser",
            "id": 1789,
            "caption": "Aurum Geyser Erupting",
            "url": "https://www.nps.gov/common/uploads/structured_data/3C7D2FBB-1DD8-B71B-0BED99731011CFCE.jpg"
          }

Video/multimedia example structure:

"multimedia": [
          {
            "title": "The Worthington Farm",
            "id": "4FD5B472-D6D9-43AA-9532-2AC5BF920883",
            "type": "multimedia/videos",
            "url": "https://www.nps.gov/media/video/view.htm?id=4FD5B472-D6D9-43AA-9532-2AC5BF920883"
          }

latLong is a useful/cute property to potentially incorporate if not other mapping data

"latLong": "lat:44.59824417, long:-110.5471695",



Subcategories to potentially use:

parks
activities
activities/parks
webcams - useful
thingstodo - potentially useful but very dense
multimedia/galleries
multimedia/galleries/assets
mapdata/parkboundaries - could be integrated into a map overlay somehow? But then would need some other map API probably


 */