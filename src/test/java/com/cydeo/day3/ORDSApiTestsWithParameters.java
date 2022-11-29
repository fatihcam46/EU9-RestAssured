package com.cydeo.day3;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiTestsWithParameters {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://3.87.65.105:1000/ords/hr";//MY IP ADDRESS
    }

    /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1(){
      Response response= given().accept(ContentType.JSON)
                    .and().queryParam("q","{\"region_id\":2}")//all region id 2 :olanlar print et
                    .log().all()
            .when()
                    .get("/countries");

      assertEquals(200,response.statusCode());
      assertEquals("application/json",response.header("Content-Type"));
      assertTrue(response.body().asString().contains("United States of America"));

      response.prettyPrint();
//{
//            "country_id": "AR",
//            "country_name": "Argentina",
//            "region_id": 2,
//            "links": [
//                {
//                    "rel": "self",
//                    "href": "http://54.145.124.192:1000/ords/hr/countries/AR"
//                },,,,,,,,,
    }
//-----------------------------------------------------------------------------
    /*
        Send a GET request to employees and get only employees who works as a IT_PROG

     */
//http://54.145.124.192:1000/ords/hr/employees?q={"job_id": "IT_PROG"}
// IP ADDRESS/ords/hr/employees?   q   =  { ..........}
    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                //queryParam("q","{\"job_id\": \"IT_PROG\"}") if I delete , I can find all employees IT PROG
                .log().all()
                .when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();

    }


}
