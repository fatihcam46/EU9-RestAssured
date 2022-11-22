package com.cydeo.pojo;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;

@Getter //from lombok dependency
@Setter //from lombok dependency
@ToString //from lombok dependency
@JsonIgnoreProperties(ignoreUnknown = true)  //this is from jackson dependency
public class Region {

    //region_id
    //if your jsonkey and variable name not matching, you can map it with jsonProperty
    @JsonProperty("region_id")//inside write keyname
    private int rId;

    @JsonProperty("region_name")
    private String region_name;

    @JsonProperty("links")
    private List<Link> links;

}