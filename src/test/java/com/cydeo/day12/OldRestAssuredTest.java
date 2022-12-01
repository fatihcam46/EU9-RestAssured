package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.filter.log.*;
import io.restassured.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OldRestAssuredTest extends SpartanNewBase {


    @Test
    public void getAllSpartan(){
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
        .when()
                .get("/spartans")//"http://3.87.65.105/api"  till here we wrote in SpartanNewBase (utilities class)
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(2))   //failed
                .body("id[5]",is(199))//I cannot see any info before is compiled error
                .log().all();

    }
//-------------------.expect()  used-------------------
    @Test
    public void test2(){

        /*
            in previous version of Rest assured, the given when then style
            was actually written in given expect when formatted.
            it will assert all the result and give one answer and does not fail whole thing
            if first one fail unlike new structure.
         */
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
                .expect() //before is same but .expect() different>>>
                .statusCode(200)
        .and()
                .contentType("application/json")
                .body("id[0]",is(10))  //failed
                .body("id[5]",is(199))  //failed
                .logDetail(LogDetail.ALL)   //log way using with expect()
        .when()
                .get("/spartans");
    }
}
