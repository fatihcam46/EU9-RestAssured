package com.cydeo.utilities;

import io.restassured.filter.log.*;
import io.restassured.http.*;
import io.restassured.specification.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {

    public static RequestSpecification requestSpec;  //some of them repeating copied firstly @BeforeAll
    public static ResponseSpecification responseSpec; //some of them repeating
    public static RequestSpecification userSpec;     //some of them repeating
    public static RequestSpecification adminSpec;

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://3.87.65.105";//ip address:
        port = 7000;
        basePath ="/api";

        requestSpec = given()               //some of them repeating>>so that we copied here
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")  //adminSpec = ... we can write
                .log().all();

        userSpec =given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("user", "user")
                .log().all();

        responseSpec = expect().statusCode(200)  //some of them repeating>>so that we copied here
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);  //logging with response specficiation



    }

    @AfterAll
    public static void close(){
        //reset the info we set above ,method comes from restassured
        reset();//default means that
    }
}
