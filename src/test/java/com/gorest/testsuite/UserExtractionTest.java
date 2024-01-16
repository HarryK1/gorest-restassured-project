package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

//    1. Extract the All Ids

    @Test
    public void test001() {
        List<Integer> totalIds =  response.extract().path("id");
        System.out.println("List of Ids are : " + totalIds);
    }

//2. Extract the all Names

    @Test
    public void test002() {
        List<String> allNames =  response.extract().path("name");
        System.out.println("List of names are : " + allNames);
    }

//3. Extract the name of 5th object

    @Test
    public void test003() {
        String nameOfObject =  response.extract().path("[4].name");
        System.out.println("The name of 5th object : " + nameOfObject);
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> namesOfInactiveStatus =  response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("The names of all object whose status = inactive : " + namesOfInactiveStatus);
    }

//5. Extract the gender of all the object whose status = active

    @Test
    public void test005() {
        List<String> allGender =  response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("The  gender of all the object whose status = active : " + allGender);
    }

//6. Print the names of the object whose gender = female

    @Test
    public void test006() {
        List<String> namesOfObject =  response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("The names of the object whose gender = female : " + namesOfObject);
    }

//7. Get all the emails of the object where status = inactive

    @Test
    public void test007() {
        List<String> emails =  response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("All the emails of the object where status = inactive: " + emails);
    }

//8. Get the ids of the object where gender = male

    @Test
    public void test008() {
        List<String> idsOfAllObject =  response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("The ids of the object where gender = male: " + idsOfAllObject);
    }


//9. Get all the status

    @Test
    public void test009() {
        List<String> allTheStatus =  response.extract().path("status");
        System.out.println("All the status are: " + allTheStatus);
    }

//10. Get email of the object where name = Lal Dwivedi"

    @Test
    public void test0010() {
        String email =  response.extract().path("find{it.name == 'Lal Dwivedi'}.email");
        System.out.println("Email of the object where name = Lal Dwivedi: " + email);
    }

//11. Get gender of id = 5914189

    @Test
    public void test0011() {
        String gender =  response.extract().path("find{it.id == 5914189}.gender");
        System.out.println("gender of id = 5914189: " + gender);
    }

}
