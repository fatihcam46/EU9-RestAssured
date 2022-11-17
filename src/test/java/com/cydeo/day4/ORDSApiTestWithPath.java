package com.cydeo.day4;

import com.cydeo.utilities.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {
        Response response= given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":2}")
                .when()
                .get("/countries");
        assertEquals(200,response.statusCode());
        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));//response.path("limit") = 25

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));//response.path("hasMore") = false

        //print first CountryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);//firstCountryId = AR

        //print second country name
        String secondCountryName = response.path("items[1].country_name");//Parents to child>>
        System.out.println("secondCountryName = " + secondCountryName);//secondCountryName = Brazil

        //print "http://52.207.61.129:1000/ords/hr/countries/CA"
        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);//thirdHref = http://54.145.124.192:1000/ords/hr/countries/CA

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);//countryNames = [Argentina, Brazil, Canada, Mexico, United States of America]

        //assert that all regions ids are equal to 2  first we need to put the list.after loop
        List<Integer> allRegionsIDs = response.path("items.region_id");
        for (Integer regionsID : allRegionsIDs) {
            System.out.println("regionsID = " + regionsID);
            assertEquals(2,regionsID);  //regionsID = 2,regionsID = 2,regionsID = 2...
        }


    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG",jobID);//jobID = IT_PROG,jobID = IT_PROG,jobID = IT_PROG...
        }

        //HW
        //print name of each IT_PROGs

    }
}

