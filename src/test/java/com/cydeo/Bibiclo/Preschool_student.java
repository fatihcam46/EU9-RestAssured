package com.cydeo.Bibiclo;

import io.restassured.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;//copy previous class


public class Preschool_student {
    /*
       given accept type is Json
       And path param id is 2
       When user sends a get request to spartans/{studentId}
       Then status code is 200
       And content type is Json
       And json data has following
           "studentId": 2,
           "firstName": "Mark",
           "gender": "Male",
           "birthDate": "02/04/2001"
        */
    @DisplayName("CBTraining Student request with chaining and matchers")
    @Test
    public void studentData(){
//response is starting  structure BDD _ gherkin language
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",2)
                .and()
        .when()
                .get("https://api.training.cydeo.com/student/{id}")
        .then()
                //request is starting
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                // .header("Content-Length",is("236"))  no Content-Length
                .and()
                .header("Date",notNullValue())
                .and().assertThat()
                .body("students[0].firstName",is("Mark"))
                .body("students[0].lastName",is("Telesco"))
                .body("students[0].gender",is("Male"))
                .body("students[0].birthDate",equalTo("02/04/2001"));

    }
}
