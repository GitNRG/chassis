package com.example.chassis.api;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestControllerApiTest {

    @Test
    public void sayHiShouldReturnHttpCodeOk() {
        given()
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .body("")
        .when()
            .get("/sayHi")
        .then()
            .statusCode(HttpStatus.SC_OK);

        // it's possible to validate other response attributes, like content type or response body
        // but it's not api test responsibility
        //
        // Response response = <insert code above>
        //    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        //    .extract().response();
        //
        // assertEquals("{\"message\" : \"Oh, hi!\"}", response.asString());
    }
}
