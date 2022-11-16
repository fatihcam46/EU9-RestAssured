package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;//I copied before class
import static io.restassured.RestAssured.*;//I copied before class
import static org.junit.jupiter.api.Assertions.*;//I copied before class
public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.145.124.192:8000";//MY IP ADDRESS
    }
     /*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */
    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test1(){

        Response response = given().                             //Given accept type is Json
                                      accept(ContentType.JSON)
                                      .and().pathParam("id", 5)  //And Id parameter value is 5
                            .when()   //When user sends GET request to /api/spartans/{id}
                                     .get("/api/spartans/{id}");
        //verify status code
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());
        //verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Blythe"));
//If you want to print it:
// response.prettyPrint();
//{
//    "id": 5,
//    "name": "Blythe",
//    "gender": "Female",
//    "phone": 3677539542
//}
    }
//---------------------------------------------------------------
       /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @DisplayName("GET request /api/spartans/{id} Negative Test")
    @Test
    public void test2(){
                                               // Given accept type is Json
        Response response = given().accept(ContentType.JSON).log().all() //log().all() means :in the console all url shows
                                      .pathParam("id", 500)//And Id parameter value is 500
                           .when()
                                    .get("/api/spartans/{id}");
                                       //When user sends GET request to /api/spartans/{id}
        //verify status code 404
        assertEquals(404,response.statusCode()); //Then response status code should be 404
        //verify content type
        assertEquals("application/json",response.contentType()); //And response content-type: application/json
        //verify NotFound in the json payload/body
        assertTrue(response.body().asString().contains("Not Found")); //And "Not Found" message should be in response payload

         //  response.prettyPrint();
        //{
        //    "timestamp": "2022-11-15T23:14:44.163+00:00",
        //    "status": 404,
        //    "error": "Not Found",
        //    "message": "",
        //    "path": "/api/spartans/500"
        //}
    }
//---------------------------------------------------------------
     /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
//http://54.145.124.192:8000/api/spartans//search?nameContains=e&gender=Female
    //log().all(). is printing all url
    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3(){
    Response response= given().log().all().
                                accept(ContentType.JSON)
                              .and().queryParam("nameContains","e")
                              .and().queryParam("gender","Female")
                .when()
                        .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());
        //verify NotFound in the json payload/body

        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));
       // response.prettyPrint();
        //{
        //            "id": 4,
        //            "name": "Paige",
        //            "gender": "Female",
        //            "phone": 3786741487
        //        },,,,,,
    }

    //-----------------------same before change map add query parameters--------------------------
    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4(){
        //create a map and add query parameters
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response = given().
                                    log().all()
                                    .accept(ContentType.JSON)
                                    .and().queryParams(queryMap)
                            .when()
                                    .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());
        //verify NotFound in the json payload/body

        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));

       // response.prettyPrint();

    }



}
