package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;

import org.example.properties.PropertiesFile;
import org.testng.Assert;

import static io.restassured.RestAssured.given;


public class APIStepDefinitions {

    RequestSpecification request;
    Response response;

    @Given("^user calls get API status$")
    public void userCallsGetAPIStatus() {
        String path = "/status";
        response = given().when().get(path).then().extract().response();
    }

    @And("^user authenticates the API request with (.*)$")
    public void userAuthenticatesTheAPIRequestWithToken(String token) {
        request = given().auth().oauth2(token);
    }

    @When("^user sends the API request to get all repositories$")
    public void userSendsTheAPIRequestToGetAllRepositories() {
        String path = "/user/repos";
        response = request.get(path).then().extract().response();
    }



    @Then("^validate response status is (.*)$")
    public void  validateResponseStatusisStatus(String status) {
        Assert.assertTrue(response.getStatusLine().contains(status), "Error in status response");
    }

    @Then("^user validates the response status code is (.*)$")
    public void userValidatesTheResponseStatusCodeIsCode(int code) {
        Assert.assertEquals(code, response.getStatusCode(),"Error in code response");
    }
}
