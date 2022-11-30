package com.cydeo.day11;
import io.restassured.http.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CsvSourceParametrizedTest {

    // Test first number + second number = third number
    //            1, 3 , 4
    //            2, 3 , 5
    //            8, 7 , 15
    //            10, 9 , 19

    @ParameterizedTest
    @CsvSource({  "1, 3 , 4",     //make sure CsvSource   //her satir testin alt bolumu 1.bolum
                  "2, 3 , 5",     //each row one string                             //2.bolum
                  "8, 7 , 15",                                                      //3.bolum
                  "10, 9 , 19"})                                                    //4.bolum
    public void testAddition(int num1, int num2, int sum) {  //I need 3 parameters here:

        System.out.println("num1 = " + num1);// num1 = 1
        System.out.println("num2 = " + num2);//num2 = 3
        System.out.println("sum = " + sum);   // sum = 4
        // assert num1+ num2 equals to sum  assertion

        assertThat(num1 + num2, equalTo(sum));  //for hamcrest.MatcherAssert
    }
//-------------------------------------------------------------------
    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*  "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({ "NY, New York",
                 "CO, Denver",
                  "VA, Fairfax",
                  "VA, Arlington",
                  "MA, Boston",
                 "NY, New York",
                 "MD, Annapolis"})
    public void zipCodeMultiInputTest(String state,String city){  //7times state and city is looking ,testing
        System.out.println("state = " + state);
        System.out.println("city = " + city);

        int placeNumber =
              given()
                .baseUri("https://api.zippopotam.us")  //we need to write base uri
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .log().uri()  //to see full link https://api.zippopotam.us/us/NY/New%20York bu sadece birisi icin link
              .when()
                .get("/us/{state}/{city}")  //https://api.zippopotam.us oncesine bu eklenip baseUri silinebilir //path param/ betweeen /
              .then()
                .statusCode(200)
              .and()
                      //verify place name contains your city name
                .body("places.'place name'", everyItem(containsStringIgnoringCase(city))) // matcher places.'place name
                //hasItem just one is checking, everyItem all check
                //.log().body()
                .extract()
                .jsonPath().getList("places").size();
        //we added int placeNumber after size();
        //print number of places for each request
        System.out.println("placeNumber = " + placeNumber);
/*  bu islemi her biri icin yapiyor,
state = NY
city = New York
Request URI:	https://api.zippopotam.us/us/NY/New%20York
placeNumber = 166
 */

    }
}
