package com.cydeo.day4;
import com.cydeo.utilities.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.get;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1(){

        Response response = get("/countries");
        //get the second country name with JsonPath

        //to use jsonpath we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();
        //response.prettyPrint();//ALL PRINT
        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);//secondCountryName = Australia

        //get all country ids
        //items.country_id
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);//[AR, AU, BE, BR, CA, CH, CN, DE, DK, EG, FR, IL, IN, IT, JP, KW, ML, MX, NG, NL, SG, UK, US, ZM, ZW]

        //get all country names where their region id is equal to 3
        List<String> countryNameWithRegionId3 = jsonPath.getList("items.findAll {it.region_id==3}.country_name");
                                                                     //ALL items. it means:each item  3.item
        System.out.println(countryNameWithRegionId3);//[Australia, China, India, Japan, Malaysia, Singapore]

    }

    @DisplayName("GET request /employees with query param")
    @Test
    public void test2(){
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();
      //  response.prettyPrint(); PRINT ALL
        //get me all email of employees who is working as IT_PROG
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);//[AHUNOLD, BERNST, DAUSTIN, VPATABAL, DLORENTZ]

        //get me first name of employees who is making more than 10000
        List<String> empNames = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(empNames);//[Steven, Neena, Lex, Nancy, Den, John, Karen, Alberto, Gerald, Eleni, Clara, Lisa, Ellen, Michael, Shelley]

        //get the max salary first_name//look web site: www.james-willett.com/rest-assured-gpath-json/
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);//kingFirstName = Steven

        String kingNameWithPathMethod = response.path("items.max {it.salary}.first_name");
        System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);//kingNameWithPathMethod = Steven
    }

}
