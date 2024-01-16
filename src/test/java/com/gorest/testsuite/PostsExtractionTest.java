package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }

//    1. Extract the title

    @Test
    public void test001() {

        List<String> title = response.extract().path("title");
        System.out.println("List of title are : " + title);
    }

//2. Extract the total number of record

    @Test
    public void test002() {

        List<Integer> total = response.extract().path("id");
        System.out.println("The total number of record : " + total.size());
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("[14].body");
        System.out.println("The body of 15th record : " + body);
    }

//4. Extract the user_id of all the records

    @Test
    public void test004() {
        List<?> userId = response.extract().path("user_id");
        System.out.println("The user_id of all the records : " + userId);
    }

//5. Extract the title of all the records

    @Test
    public void test005() {
        List<?> title = response.extract().path("title");
        System.out.println("The title of all the records : " + title);
    }

    //6. Extract the title of a records whose user_id = 5914200
    @Test
    public void test006() {
        List<?> titleOfAllRecords = response.extract().path("findAll{it.user_id = '5914200'}.title");
        System.out.println("Title of all records whose user_id = 5914200 : " + titleOfAllRecords);
    }

//7. Extract the body of all records whose id = 93957

    @Test
    public void test007() {
        List<?> bodyOfAllRecords = response.extract().path("findAll{it.body = '93957'}.title");
        System.out.println("the body of all records whose id = 93957 : " + bodyOfAllRecords);
    }
}
