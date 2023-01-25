package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.example.properties.PropertiesFile;
import org.testng.Assert;
import utils.Utils;
import com.jayway.jsonpath.*;

import static io.restassured.RestAssured.given;

public class BookSteps {

    public static Response response;
    public static JSONArray jsonResponse;

    @Given("^user calls Gets List of books type \"([^\"]*)\"$")
    public void userCallsGetListOfBooks(String type) {
        String path = "/books";
        response =  given()
                        .contentType("application/json")
                        .queryParam("type",type)
                    .when()
                        .get(path)
                    .then()
                        //.log().all()
                        .extract().response();
        Utils.setResponse(response);
    }
    @Given("^user calls Gets Single of book by id \"([^\"]*)\"$")
    public void userCallsGetSingleBookById(String id) {
        String path = "/books/";
        response =  given()
                        .contentType("application/json")
                    .when()
                        .get(path + id)
                    .then()
                        //.log().all()
                        .extract().response();
        Utils.setResponse(response);
    }

    @When("user filter books which are available \"([^\"]*)\"$")
    public void userFiltersBooksByAvailability(String availability) {
        Response response = Utils.getResponse();
        JSONArray book = JsonPath.read(response.asString(), "$.[?(@.available==" +availability+ ")]");
        jsonResponse = book;
    }

    @Then("^validate book title is \"([^\"]*)\"$")
    public void validateBookTitle(String title) {
        Assert.assertEquals(JsonPath.read(jsonResponse, "$[0]name"), title,"Error in title response");
    }

    @Then("^validate book type is \"([^\"]*)\"$")
    public void validateBookType(String type) {
        Assert.assertEquals(JsonPath.read(jsonResponse, "$[0]type"), type,"Error in type response");
    }

    @Then("^validate book has stock$")
    public void validateBookHasStock() {
        Response response = Utils.getResponse();
        Assert.assertTrue(Integer.parseInt(JsonPath.read(response.asString(), "$.current-stock").toString()) > 0,"Error Book should have stock");
    }
}
