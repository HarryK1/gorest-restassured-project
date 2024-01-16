package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
        List<Integer> total = response.extract().path("id");
        Assert.assertEquals(total.size(), 25);
    }


    //   2. Verify the if the title of id = 93997 is equal to ”Ambitus thesaurus contabesco tero amplitudo confugo et tutamen vulgivagus.”

    @Test
    public void Test002() {
        response.body("find{it.id=93997}.title", equalTo("Ambitus thesaurus contabesco tero amplitudo confugo et tutamen vulgivagus."));
    }


    //   3. Check the single user_id in the Array list (5914156)
    @Test
    public void Test003() {
        response.body("user_id", hasItem(5914156));
    }

// 4. Check the multiple ids in the ArrayList (5914184, 5914184, 5914181)

    @Test
    public void Test004() {
        response.body("user_id", hasItems(5914184, 5914184, 5914181));
    }


// 5. Verify the body of userid = 5914184 is equal Ago tenus temperantia. Reprehenderit tersus vigor. Desparatus coruscus anser. Temeritas amicitia in. Bellicus patruus canto. Adsidue vobis aut. Delego denique vehemens. Est sono aperte. Abscido qui ulterius. Ut speciosus at. Tredecim asper et. Vel crur tero. Vobis odio damnatio. Subnecto adhaero defero. Cubo textus succurro. Expedita thymum vetus. Confido bellicus alo. Velit viduo cubitum. Vorax clementia testimonium.”


    @Test
    public void Test005() {
        response.body("find{it.user_id=5914184}.body",equalTo("Ago tenus temperantia. Reprehenderit tersus vigor. Desparatus coruscus anser. Temeritas amicitia in. Bellicus patruus canto. Adsidue vobis aut. Delego denique vehemens. Est sono aperte. Abscido qui ulterius. Ut speciosus at. Tredecim asper et. Vel crur tero. Vobis odio damnatio. Subnecto adhaero defero. Cubo textus succurro. Expedita thymum vetus. Confido bellicus alo. Velit viduo cubitum. Vorax clementia testimonium."));

    }


}
