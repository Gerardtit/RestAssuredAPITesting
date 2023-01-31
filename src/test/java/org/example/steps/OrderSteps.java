package org.example.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.example.properties.PropertiesFile;
import org.testng.Assert;
import utils.Utils;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    public static Response response;
    public static JSONArray jsonResponse;

    @Before("@Order")
    public void beforeScenario (){
        BaseSteps.userSetsTheBaseAPIRequestURL("base");
        UserSteps.setsPostDataForCreatingUser();
        UserSteps.userCallsPostCustomerAPI();
        BaseSteps.saveBearer();
    }

    @Given("^user calls Post order a book by id \"([^\"]*)\"$")
    public void userCallsPostSingleBookById(String id) {
        JSONObject request = new JSONObject();
        request.put("bookId", id);
        request.put("customerName", Utils.getFirstName() + " "+Utils.getLastName());
        String path = "/orders";
        response =  given()
                        .contentType("application/json")
                        .body(request.toJSONString())
                        .auth().oauth2(PropertiesFile.readAPIProperty("bearer"))
                    .when()
                        .post(path)
                    .then()
                        //.log().all()
                        .extract().response();
        Utils.setResponse(response);
    }

    @Given("^user calls Get all books orders$")
    public void userCallsGetAllBooksOrders() {
        String path = "/orders";
        response =  given()
                        .contentType("application/json")
                        .auth().oauth2(PropertiesFile.readAPIProperty("bearer"))
                    .when()
                        .get(path)
                    .then()
                        //.log().all()
                        .extract().response();
        Utils.setResponse(response);
    }

    @Given("^user calls Get order by id$")
    public void userCallsGetOrderById() {
        String path = "/orders/{id}";
        response =  given()
                        .contentType("application/json")
                        .pathParam("id", PropertiesFile.readAPIProperty("orderId"))
                        .auth().oauth2(PropertiesFile.readAPIProperty("bearer"))
                    .when()
                        .get(path)
                    .then()
                        //.log().all()
                        .extract().response();
        Utils.setResponse(response);
    }

    @Given("^user calls Update order user name by id$")
    public void userCallsUpdateOrderUsernameById() {
        JSONObject request = new JSONObject();
        request.put("customerName", PropertiesFile.readAPIProperty("updateName"));
        String path = "/orders/{orderId}";
        response =  given()
                        .contentType("application/json")
                        .pathParam("orderId", PropertiesFile.readAPIProperty("orderId"))
                        .body(request.toJSONString())
                        .auth().oauth2(PropertiesFile.readAPIProperty("bearer"))
                    .when()
                        .patch(path)
                    .then()
                        //.log().all()
                        .extract().response();
        Utils.setResponse(response);
    }

    @Given("^user calls Delete an order by id$")
    public void userCallsDeleteAnOrderById() {
        String path = "/orders/{orderId}";
        response =  given()
                        .contentType("application/json")
                        .pathParam("orderId", PropertiesFile.readAPIProperty("orderId"))
                        .auth().oauth2(PropertiesFile.readAPIProperty("bearer"))
                    .when()
                        .delete(path)
                    .then()
                         //.log().all()
                        .extract().response();
        Utils.setResponse(response);
    }

    @Then("^user saves orderId")
    public static void saveOrderId() {
        Response response = Utils.getResponse();
        JsonPath jsonPathEvaluator = response.jsonPath();
        String orderId = jsonPathEvaluator.get("orderId");
        PropertiesFile.setAPIProperty("orderId", orderId);
    }


    @Then("^validate error message for no existing order by id$")
    public void validateErrorMessageForNoExistingOrderById() {
        Response response = Utils.getResponse();
        Assert.assertEquals(com.jayway.jsonpath.JsonPath.read(response.asString(), "$.error").toString() , "No order with id " + PropertiesFile.readAPIProperty("orderId") + ".","Error message for no existing order is incorrect");
    }
}
