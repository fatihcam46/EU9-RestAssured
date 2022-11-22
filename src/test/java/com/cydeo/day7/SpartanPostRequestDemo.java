package com.cydeo.day7;
import com.cydeo.pojo.*;
import com.cydeo.utilities.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class SpartanPostRequestDemo extends SpartanTestBase {

       /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
       @Test
       public void postMethod1() {
              String requestJsonBody = "{\"gender\":\"Male\",\n" +
                      "\"name\":\"Severus\",\n" +
                      "\"phone\":8877445596}";  //copy paste from POSTMAN
              Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                      .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                      .body(requestJsonBody)  //.body method  body(variable paste here)
                      .when()
                      .post("/api/spartans");//post method(/api/spartans)    add, here

              //verify status code
              assertThat(response.statusCode(),is(201));
              assertThat(response.contentType(),is("application/json"));

              String expectedResponseMessage = "A Spartan is Born!";
              assertThat(response.path("success"),is(expectedResponseMessage));//success is key:
              assertThat(response.path("data.name"),is("Severus"));//tell me path of name: data.name
              assertThat(response.path("data.gender"),is("Male"));
              assertThat(response.path("data.phone"),is(8877445596L));//PATHmethod is long : L



       }
}
