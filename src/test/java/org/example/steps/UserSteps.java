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
    public static void setsPostDataForCreatingUser() {
        map.put("clientName", Utils.getFirstName());
        map.put("clientEmail", Utils.getEmail());
    }

    @Given("^user calls Post Customer API")
    public static void userCallsPostCustomerAPI() {
        String path = "/api-clients";
        response =  given()
                        .contentType("application/json")
                        .body(map)
                    .when()
                        .post(path)
                    .then()
                        .log().all()
                        .extract().response();
        Utils.setResponse(response);
    }

    @Then("^validate updated user name$")
    public void validateUpdatedUsername() {
        Response response = Utils.getResponse();
        Assert.assertEquals(com.jayway.jsonpath.JsonPath.read(response.asString(), "$.customerName").toString() , PropertiesFile.readAPIProperty("updateName"),"Error new name has not been updated");
    }
}
