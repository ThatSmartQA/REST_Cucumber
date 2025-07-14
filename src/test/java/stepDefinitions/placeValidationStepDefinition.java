package stepDefinitions;

import Resources.Payloads.AddPlaceAPIPayload;
import Resources.Payloads.DeletePlaceAPIPayload;
import Resources.Utils.APIEndPointResources;
import Resources.Utils.PlaceValidationUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class placeValidationStepDefinition extends PlaceValidationUtils {
    RequestSpecification res;
    static Response response;
    AddPlaceAPIPayload data = new AddPlaceAPIPayload();
    PlaceValidationUtils util = new PlaceValidationUtils();
    static String place_id;

    @Given("AddPlace payload with {string}")
    public void addPlacePayloadWith(String name) throws FileNotFoundException {
        res = given().spec(util.requestSpecification()).body(data.addPlacePayload(name));
    }

    @When("User calls {string} using {string} http method")
    public void userCallsUsingHttpMethod(String resource, String method) {
        APIEndPointResources apiEndPointResources = APIEndPointResources.valueOf(resource);
        if (method.equalsIgnoreCase("POST"))
            response = res.when().post(apiEndPointResources.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = res.when().get(apiEndPointResources.getResource());
        else if (method.equalsIgnoreCase("DELETE"))
            response = res.when().delete(apiEndPointResources.getResource());
    }

    @Then("the API call is successful with {int} status code")
    public void the_api_call_is_successful_with_status_code(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("Status attribute on response body is OK")
    public void status_attribute_on_response_body_is_ok() {
        // Write code here that turns the phrase above into concrete actions

        assertEquals("OK", getJsonPath(response, "status"));

    }

    @Given("Place_id from AddPlaceAPI")
    public void place_idFromAddPlaceAPI() throws FileNotFoundException {
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
    }

    @Then("{string} attribute on response body matches name attribute from AddPlaceAPI")
    public void attributeOnResponseBodyMatchesNameAttributeFromAddPlaceAPI(String expectedName) {
        String actualName = getJsonPath(response, "name");
        assertEquals(expectedName, actualName);

    }

    @Given("DeletePlace payload")
    public void deletePlacePayload() throws FileNotFoundException {
        DeletePlaceAPIPayload deletePlaceAPIPayload=new DeletePlaceAPIPayload();
        res=given().spec(requestSpecification()).body(deletePlaceAPIPayload.deletePlacePayload(place_id));

    }
}