package com.cydeo.day6;

import com.cydeo.pojo.*; //pojo: plain old java object  //Search , Spartan look from pojo class
import com.cydeo.utilities.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one spartan and convert it to Spartan Object")
    @Test
    public void oneSpartanPojo(){ //from POSTMAN

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()// log().body() //yazdirdiginin ustundekiler icin yazdirma
                /*
                {  "id": 15,
                   "name": "Meta",
                   "gender": "Female",
                   "phone": 1938695106 }
                 */
                .extract().response();

        //De serialize --> JSON to POJO (java custom class)
        //2 different way to do this
        //1.using as() method
        //we convert json response to spartan object with the help of jackson
        //as() method uses jackson to de serialize(converting JSON to Java class)
        Spartan spartan15 = response.as(Spartan.class);//as(CLASSNAME.class);
        System.out.println(spartan15);
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
/*
Spartan{id=15, name='Meta', gender='Female', phone=1938695106}
spartan15.getId() = 15
spartan15.getGender() = Female
 */
   //----------------------------------------------------------------------
        //second way of deserialize json to java
        //2.using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("", Spartan.class);//getObject method


        System.out.println(s15);//Spartan{id=15, name='Meta', gender='Female', phone=1938695106}
        System.out.println("s15.getName() = " + s15.getName());//s15.getName() = Meta
        System.out.println("s15.getPhone() = " + s15.getPhone());//s15.getPhone() = 1938695106

    }
//-----------------------------------------------------------------------------------
    @DisplayName("Get one spartan from search endpoint result and use POJO")
    @Test
    public void spartanSearchWithPojo(){
        ///spartans/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type Spartan POJO
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        //get the first spartan from content list and put inside spartan object
        Spartan s1 = jsonPath.getObject("content[0]", Spartan.class);//id 16

    System.out.println("s1 = " + s1);//s1 = Spartan{id=16, name='Sinclair', gender='Male', phone=9714460354}
     System.out.println("s1.getName() = " + s1.getName());//s1.getName() = Sinclair
    System.out.println("s1.getGender() = " + s1.getGender());//s1.getGender() = Male

    }
    //---------------------------------------------------------------------

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);

        System.out.println(searchResult.getContent().get(0).getName());//first element:index is 0:Sinclair

    }

    @DisplayName("GET  /spartans/search and save as List<Spartan>")
    @Test
    public void test4(){
        List<Spartan> spartanList = given()
                .accept(ContentType.JSON)
                .and()
                .queryParams("nameContains", "a",
                        "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("content", Spartan.class);

        System.out.println(spartanList.get(0).getName());//first is Sinclair


    }

}