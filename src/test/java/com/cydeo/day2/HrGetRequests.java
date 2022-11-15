package com.cydeo.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;//we don't need all the time, import here, and do it down>>
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.145.124.192:1000/ords/hr";//my IP ADDRESS from HR from AWS

    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1(){

        Response response = get("/regions");

        //print the status code
        System.out.println(response.statusCode());//200

    }
//--------------------------------------------------------------------
     /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)//hey Java I want to see JSON type
                            .when()
                                    .get("/regions/2");

        //verify status code
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());

        response.prettyPrint();

        //verify body contains Americas
        assertTrue(response.body().asString().contains("Americas"));
//assertEquals it is same as assertTrue
    }





}
