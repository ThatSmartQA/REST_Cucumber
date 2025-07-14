package stepDefinitions;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws FileNotFoundException {
        placeValidationStepDefinition p=new placeValidationStepDefinition();

        if (placeValidationStepDefinition.place_id==null) {
            p.addPlacePayloadWith("Queen Palace");
            p.userCallsUsingHttpMethod("AddPlaceAPI", "POST");
            p.place_idFromAddPlaceAPI();
        }
    }
}
