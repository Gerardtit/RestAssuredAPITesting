package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.properties.PropertiesFile;
import org.testng.Assert;
import utils.Utils;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class UserSteps{

    public static HashMap map = new HashMap();

    public static Response response;

    @Given("^user sets post data for creating an user$")
    public void setsPostDataForCreatingUser() {
        map.put("clientName", Utils.getFirstName());
        map.put("clientEmail", Utils.getEmail());
    }

    @Given("^user calls Post Customer API")
    public void userCallsGetAPIStatus() {
        String path = "/api-clients";
        response =  given()
                        .contentType("application/json")
                        .body(map)
                    .when()
                        .post(path)
                    .then()
                        .extract().response();
        Utils.setResponse(response);
    }
}
