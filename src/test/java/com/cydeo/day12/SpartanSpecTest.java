package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class SpartanSpecTest extends SpartanNewBase {  //in SpartanNewBase (utilities class)

    //all tests in this class will use admin credentials
    //all test in this class will expect json result from response

    //all test in this class response status code is 200
    //all test in this class response content type header is json

    @Test
    public void test1(){

//         RequestSpecification requestSpec = given()
//                                             .accept(ContentType.JSON)
//                                             .and()
//                                              .auth().basic("admin", "admin")
//                                              .log().all();
//
//         ResponseSpecification responseSpec = expect().statusCode(200)
//                                              .and()
//                                             .contentType(ContentType.JSON)
//                                              .logDetail(LogDetail.ALL);  //logging with response specification

        given().             //spec() method
                spec(requestSpec)   //in SpartanNewBase (utilities class)
        .when()
                .get("/spartans")
         .then()
                .spec(responseSpec);   //in SpartanNewBase (utilities class)
    }
//----------------------------------------------------------
    @Test
    public void test2(){

        given()
                .spec(requestSpec)    //in SpartanNewBase (utilities class)
                .pathParam("id",15)
                .when()
                .get("/spartans/{id}")
                .then()
                .spec(responseSpec);   //in SpartanNewBase (utilities class)
/*  {
    "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106   }
 */
    }
//---------------------------------------------------------
    @Test
    public void test3(){

        given()
                .spec(userSpec)               //in SpartanNewBase (utilities class)
                .and()
                .queryParams("nameContains","j",
                        "gender","Female")
        .when()
                .get("/spartans/search")
        .then()
                .spec(responseSpec)             //in SpartanNewBase (utilities class)
                .body("numberOfElements",is(6));   //

        /*
        "id": 13,
            "name": "Jaimie",
            "gender": "Female",
            "phone": 7842554879 ......
         */
    }
}
