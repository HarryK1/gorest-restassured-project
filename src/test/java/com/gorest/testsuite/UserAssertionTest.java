package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
        List<Integer> total = response.extract().path("id");
        Assert.assertEquals(total.size(), 20);
    }

      //1. Verify the if the total record is 20

  //  @Test
//    public void test001() {
//        response.body("total.size", equalTo(20));
//    }

    //Verify the if the name of id = 5914143 is equal to ”Himadri Banerjee”
    @Test
    public void test002() {
        response.body("[2].name", equalTo("Himadri Banerjee"));
    }

    //Check the single ‘Name’ in the Array list (Manikya Asan MD)
    @Test
    public void test003() {
        response.body("[0].name", equalTo("Manikya Asan MD"));
    }

    //Check the multiple ‘Names’ in the ArrayList (Bilwa Embranthiri, Chandini Prajapat, Abani Butt)

    @Test
    public void test004() {
        response.body("name", hasItems("Bilwa Embranthiri","Chandini Prajapat","Abani Butt"));
    }

    //Verify the email of userid = 5914132 is equal “kailash_pillai@hauck.test”
    @Test
    public void test005() {
        response.body("[9].email", equalTo("kailash_pillai@hauck.test"));
    }

    //Verify the status is “active” of user name is “Ekalavya Embranthiri”
    @Test
    public void test006() {
        response.body("[7].status", equalTo("active"));
    }

    //Verify the Gender = male of user name is “Amb. Dandapaani Pilla”

    @Test
    public void test007() {
        response.body("[3].gender", equalTo("male"));
    }
}
