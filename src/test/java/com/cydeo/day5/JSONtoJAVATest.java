package com.cydeo.day5;
import com.cydeo.utilities.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {
    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map


        Map<String,Object> jsonMap = response.as(Map.class);//IT WILL JSON CONVERT TO MAP
//Normally it will fail> you will add dependency>>
// mvnrepository>>Jackson Databind>>add // pom.xml

        System.out.println(jsonMap.toString());//{id=15, name=Meta, gender=Female, phone=1938695106}

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");//get("name") // cast it,
        assertThat(actualName,is("Meta"));//assert is it Meta?
    }

    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan(){

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        //we need to convert json to java  which is deserialized

        List<Map<String,Object>> jsonList = response.as(List.class);//IT WILL JSON CONVERT TO MAP

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));
        //jsonList.get(1).get("name") = Fidole  //index number 0  1  2  3  >>Fidole is second so index is 1

        Map<String,Object> spartan3 = jsonList.get(2);
        System.out.println(spartan3);//{id=4, name=Paige, gender=Female, phone=3786741487}  //3rd spartan
    }
}
