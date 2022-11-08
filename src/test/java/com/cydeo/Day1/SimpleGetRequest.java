package com.cydeo.Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
//day1:
public class SimpleGetRequest {

    String url = "http://54.145.124.192:8000/api/spartans";
//my ip adres:8000 from POSTMAN
    @Test
    public void test1(){
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        //print response status code
        System.out.println(response.statusCode());

        //print response body
        response.prettyPrint();

    }





}
