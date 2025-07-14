package Resources.Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PlaceValidationUtils {
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws FileNotFoundException {

        if(req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                    addQueryParam("key", "qaclick123").
                    setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(log)).
                    addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
            return req;
        }
        return req;
    }

    public ResponseSpecification responseSpecification(){
        return new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    public String getJsonPath(Response response, String key){
        String resp=response.asString();
        JsonPath jp=new JsonPath(resp);
        return jp.get(key).toString();


    }
}
