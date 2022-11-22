package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
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
@JsonIgnoreProperties(value = "id",allowSetters = true)
public class Spartan {
    //field
    //getter setter
    //toString
    private int id;
    private String name;
    private String gender;
    private long phone;
//getter setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
//toString
    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}