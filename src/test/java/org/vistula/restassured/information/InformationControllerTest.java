package org.vistula.restassured.information;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
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
                .body("size()", is(5));
    }

    @Test
    public void shouldPostData() {

        JSONObject update = new JSONObject();
        int _id = 10;
        String _nam = "Iza";
        String _nat = "Polska";
        int _sal = 1000;

        update.put("id", _id);
        update.put("name", _nam);
        update.put("nationality", _nat);
        update.put("salary", _sal);

        given().header("Content-Type", "application/json")
                .body(update.toString())
                .post("/information")
                .then()
                .log().all()
                .statusCode(201)

                .body("name", CoreMatchers.equalTo(_nam))
                .body("nationality", CoreMatchers.equalTo(_nat))
                .body("salary", CoreMatchers.equalTo(_sal))
                .body("id", greaterThan(9));
    }


    @Test
    public void shouldPatchData() {

        JSONObject update = new JSONObject();
        String _nam = "Iza";
        int _id = 10;

        update.put("id", _id);
        update.put("name", _nam);

        given().header("Content-Type", "application/json")
                .body(update.toString())
                .patch("/information/10")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", CoreMatchers.equalTo(_nam))
                .body("id", greaterThan(0));
    }

}