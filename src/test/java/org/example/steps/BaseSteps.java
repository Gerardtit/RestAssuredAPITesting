package org.example.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.example.properties.PropertiesFile;
import org.testng.Assert;
import utils.Utils;


public class BaseSteps {



    @Given("^user sets the base API request \"([^\"]*)\"$")
    public void userSetsTheBaseAPIRequestURL(String URL) {
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
        JsonPath jsonPathEvaluator = response.jsonPath();
        String bearer = jsonPathEvaluator.get("accessToken");
        PropertiesFile.setAPIProperty("bearer", bearer);
    }
}
