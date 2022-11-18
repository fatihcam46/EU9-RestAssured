package com.cydeo.day5;

import com.cydeo.utilities.*;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanHamcrestTest extends SpartanTestBase {

    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test1(){

        //along with this statement, I want to save names inside the List<String>
//http://54.145.124.192:8000/api/spartans/search?nameContains=j&gender=male   look from POSTMAN
        List<String> names = given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains","j",
                        "gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement",greaterThanOrEqualTo(3))
                .extract().response().jsonPath().getList("content.name");//extract() one by one list of string

        System.out.println(names);//[Julio, Juan, Julie]

    }

    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test2(){
// just change status code:
        //save status code

        int statusCode = given().accept(ContentType.JSON)// what you save? status code:change here
                .and()
                .queryParams("nameContains","j",
                        "gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement",greaterThanOrEqualTo(3))
                .extract().response().statusCode();//.extract().statusCode();  you can use

        System.out.println(statusCode);//200



    }
}