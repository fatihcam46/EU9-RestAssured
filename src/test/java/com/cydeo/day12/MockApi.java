package com.cydeo.day12;

import io.restassured.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//DAY 14 END OF RECORD
public class MockApi {


    //https://2c93c3f0-6cfd-4255-a83d-626470a2a852.mock.pstmn.io
    //I created from POSTMAN mock server


    @Test
    public void test1(){

        given().baseUri("https://2c93c3f0-6cfd-4255-a83d-626470a2a852.mock.pstmn.io")
                .accept(ContentType.JSON)
        .when()
                .get("/customer")
                .prettyPrint();
       // .then()
        //        .statusCode(200)
        //        .contentType(ContentType.JSON)
        //        .body("firstName",is("John"));


    }

    @Test
    public void test2(){

        given().baseUri("https://2c93c3f0-6cfd-4255-a83d-626470a2a852.mock.pstmn.io")

                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .prettyPrint();

    }
}
