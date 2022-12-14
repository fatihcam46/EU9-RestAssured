package com.cydeo.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://3.87.65.105:8000";//my IP ADDRESS
    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test1(){
        Response response = given()  //Given Accept type application/xml
                                    .accept(ContentType.XML)
                            .when() //When user send GET request to /api/spartans/10 end point
                                    .get("/api/spartans/10");//POSTMANda nasil yazarsan burdan yazabilirsin
                                    //Then status code must be 406
        //verify status code is 406
        assertEquals(406,response.statusCode());
                                    //And response Content Type must be application/xml;charset=UTF-8
        //verify content type
        assertEquals("application/xml;charset=UTF-8",response.contentType());




    }

}
