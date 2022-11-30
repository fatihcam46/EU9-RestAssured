package com.cydeo.day11;
import io.restassured.http.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CsvFileSourceParametrizedTest {

    // Write a parameterized test for this request
    // Get the data from csv source
    // GET https://api.zippopotam.us/us/{state}/{city}
    @ParameterizedTest                                       //headers skipped started 1
    @CsvFileSource(resources = "/postalcode.csv",numLinesToSkip = 1)//resources = "/postalcode.csv"  den geliyor
    public void zipCodeTestWithFile(String stateArg,String cityArg,int zipCountArg){
        System.out.println("stateArg = " + stateArg);
        System.out.println("cityArg = " + cityArg);
        System.out.println("zipCountArg = " + zipCountArg);
        //send a request and verify place number matches with zipCount
        given()
                .baseUri("https://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", stateArg)
                .pathParam("city", cityArg)
                .log().uri()
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places",hasSize(zipCountArg));//third element  that is running each time
    }
}