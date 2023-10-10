package Steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification; 
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class APISteps {

    private static RequestSpecification request;
    private Response response;
    private ValidatableResponse json;

    @Given("^I send a get request to the (.+) URI$")
    public void sendGETRequest(String URI){
        request = given()
                    .baseUri(URI)
                    .contentType(ContentType.JSON);
                    
                    

}

    @Then("^I get a (\\d+) status code$")
    public void validateListOfUsers(int expectedStatusCode){
        response = request
                    .when()
                    .get("/users/TheFreeRangeTester/repos");

                    json = response.then().statusCode(expectedStatusCode);

    }


    @Then("^I validate there are (\\d+) items on the (.+) endpoint$")
    public void validateSize(int expectedSize,String endpoint){
            
        response = request
                    .when()
                    .get(endpoint);

                    List<String> jsonResponse = response.jsonPath().getList("$");
                    int actualSize = jsonResponse.size();

                    assertEquals(expectedSize, actualSize);



    }

    @Then("^I validate there is a value: (.+) in the response at (.+) endpoint$")
    public void validateValue(String expectedValue, String endpoint){

         response = request
                    .when()
                    .get(endpoint);

                    List<String> jsonResponse = response.jsonPath().getList("username");// se puede valdiar cualquyier campo que venga del JSON en este ejemplo se valda el username
                   //manera 1 para validar el campo
                    //String actualValue = jsonResponse.get(0);
                    //assertEquals(expectedValue,actualValue);

                    //manera 2 mas optima de validar el campo
                    assertTrue("El valor "+expectedValue+" no se encuetra en la lista",jsonResponse.contains(expectedValue));

    }

    @Then("^I validate there is a value of street: (.+) in the response at (.+) endpoint$")
    public void validateNestedValue(String expectedStreet, String endpoint){

         response = request
                    .when()
                    .get(endpoint);

                    String jsonResponse = response.jsonPath().getString("address.street");// Para validar json anidados
                 
                    assertTrue("El valor "+expectedStreet+" no se encuetra en la lista",jsonResponse.contains(expectedStreet));

    }
}
