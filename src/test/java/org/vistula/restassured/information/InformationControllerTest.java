package org.vistula.restassured.information;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.vistula.restassured.RestAssuredTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class InformationControllerTest extends RestAssuredTest {

    @Test
    public void shouldGetAll() {
        given().get("/information")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", is(2));
    }
    @Test
    public void shouldcreateNewPlayer() {
        JSONObject requestParams = new JSONObject();
        String myName = RandomStringUtils.randomAlphabetic(17);
        String nationality = "Polska";
        int mySalary = 100000000;

        requestParams.put ("name", myName);
                requestParams.put("nationality", nationality);
                requestParams.put("salary", mySalary);
        requestParams.put ("id", 4);

        given().header("Content-Type", "application/json")
                .body(requestParams.toString())
                .post("/information/")
                .then()
                .log().all()
                .statusCode(201)

                .body("nationality", equalTo(nationality))
                .body ("salary", equalTo (mySalary))
                .body("name", equalTo (myName))
                .body("id", greaterThan (0));

    }
    @Test
    public void shouldDeleteInfo(){
        given().delete("/information/2")
                .then()
                .log().all()
                .statusCode(204);
    }
}