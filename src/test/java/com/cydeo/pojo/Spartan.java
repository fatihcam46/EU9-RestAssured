package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter  //From lombok
@Setter
@ToString

/*
{
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
}
 */
@JsonIgnoreProperties(value = "id",allowSetters = true)//For the ID value, ignore it to serialization JAVA to convert JSON
public class Spartan {
    //field
    //getter setter
    //toString
    private int id;
    private String name;
    private String gender;
    private long phone;

}