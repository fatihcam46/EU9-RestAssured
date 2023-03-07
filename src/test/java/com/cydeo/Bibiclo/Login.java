package com.cydeo.Bibiclo;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Login {
    @Test
    public void test1(){

        given()
                .accept(ContentType.JSON)
        .when().baseUri("https://qa.trycloud.net/index.php/login?clear=1")
                //request is starting
         .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8");
               //.get("/customer")
               // .prettyPrint();
              //  .assertThat()



    }


}
