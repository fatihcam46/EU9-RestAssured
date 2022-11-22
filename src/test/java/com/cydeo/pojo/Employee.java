package com.cydeo.pojo;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)//this is from jackson dependency
//We want to get just 4 result these are firstName, lastName, jobId, salary.. ignore others
public class Employee {

    @JsonProperty("first_name")//write here exactly same words from POSTMAN
    private String firstName;//It is not matching from POSTMAN ,
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("job_id")
    private String jobId;

    private int salary;
}
