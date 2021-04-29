package com.helesto;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SessionRestTest {

    @Test
    public void testSessionSettingsEndpoint() {
        given()
          .when().get("/session/settings")
          .then()
             .statusCode(200);
    }

}