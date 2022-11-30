package com.cydeo.day10;
import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ResponseTimeTest extends SpartanAuthTestBase {


    @Test
    public void test1(){

        Response response = given()
                .auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
//how can we verify time between some range?
                .then()
                .time(both(greaterThan(500L)).and(lessThanOrEqualTo(2200L)))//bu iki zaman araliginda mi?
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());//response.getTime() = 1835... checking response time


    }
}
