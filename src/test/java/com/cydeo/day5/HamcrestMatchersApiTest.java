package com.cydeo.day5;

import io.restassured.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;//copy previous class

public class HamcrestMatchersApiTest {
    /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){

        given().log().all().
                accept(ContentType.JSON)                             //JSON
                .and().pathParam("id",15)                      //id 15
        .when()
                .get("http://54.145.124.192:8000/api/spartans/{id}")//my IP ADDRESS
        .then()              //still we are same request, verification write then()
                .statusCode(200)
                .and().assertThat()
                .contentType("application/json")
                .and()
                .body("id",equalTo(15),    //assertion looks like method>>>asserThat method equalTo
                        "name",is("Meta"),
                        "gender",is("Female"),
                        "phone",is(1938695106))
                .log().all();

        //{
        //    "id": 15,
        //    "name": "Meta",
        //    "gender": "Female",
        //    "phone": 1938695106
        //}
    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",2)
                .and()

                .when()
                .get("https://api.training.cydeo.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
               // .header("Content-Length",is("236"))
                .and()
              //  .header("Date",notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName",is("Ron"))
                .body("teachers[0].lastName",is("Tona"))
                .body("teachers[0].gender",equalTo("Male"));

    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest(){

        //verify Alexander,Darleen,Sean inside the all teachers
        given()
                .accept(ContentType.JSON)
                .when()
                .get("https://api.training.cydeo.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Ron","Tet","Valter"));


    }

}
