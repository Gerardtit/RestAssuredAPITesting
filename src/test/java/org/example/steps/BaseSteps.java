package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.properties.PropertiesFile;
import org.testng.Assert;
import utils.Utils;


public class BaseSteps {

    @Given("^user sets the base API request \"([^\"]*)\"$")
    public static void userSetsTheBaseAPIRequestURL(String URL) {
        if (URL.equals("base")){
            RestAssured.baseURI = PropertiesFile.readAPIProperty("uri");
        } else {
            RestAssured.baseURI = URL;
        }
    }

    @Then("^validate response is (.*)$")
    public void validateResponseIsCode(int code) {
        Response response = Utils.getResponse();
        Assert.assertEquals(response.getStatusCode(), code,"Error in code response");
    }

    @Then("^user saves bearer$")
    public static void saveBearer() {
        Response response = Utils.getResponse();
        JsonPath jsonPathEvaluator = response.jsonPath();
        String bearer = jsonPathEvaluator.get("accessToken");
        PropertiesFile.setAPIProperty("bearer", bearer);
    }

}
