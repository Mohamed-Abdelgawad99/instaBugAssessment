package stepDefinitions.restAPISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.experimental.categories.Category;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserApiSteps {

    private RequestSpecification request;
    private Response response;
    private Map<String, String> requestBody;

    private final String BASE_URL = "https://asses.free.beeceptor.com";
    private Scenario scenario;

    @Before
    public void scenario(Scenario scenario){
        this.scenario = scenario;
    }

    @Category(Category.class)
    @Severity(SeverityLevel.BLOCKER)
    @When("I send a POST request to {string} with:")
    public void sendPostRequestWithBody(String endpoint, DataTable dataTable){
        try {
            requestBody = dataTable.asMap(String.class, String.class);
            System.out.println(requestBody);
            scenario.log("Preparing POST request to: " + endpoint);
            scenario.log("Request body: " + requestBody);

            request = given()
                    .baseUri(BASE_URL)
                    .header("Content-Type", "application/json")
                    .body(requestBody);

            response = request.post(endpoint);
            scenario.log("Response received from endpoint: " + endpoint);
            response.then().log().all();

        } catch (Exception e) {
            scenario.log("Exception while sending POST request: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Severity(SeverityLevel.BLOCKER)
    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatusCode) {
        try {
            int actualStatusCode = response.getStatusCode();
            scenario.log("Asserting status code. Expected: " + expectedStatusCode + ", Actual: " + actualStatusCode);
            Assert.assertEquals("Unexpected status code",response.getStatusCode(),expectedStatusCode);
            scenario.log("Status code matched: " + expectedStatusCode);
        } catch (AssertionError e) {
            scenario.log("❌ Status code assertion failed: " + e.getMessage());
            throw e;
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @And("the response body should contain:")
    public void theResponseBodyShouldContain(DataTable expectedData) {
        Map<String, String> expectedMap = expectedData.asMap(String.class, String.class);
        scenario.log("🔍 Validating response body fields...");
        for (Map.Entry<String, String> entry : expectedMap.entrySet()) {
            try {
                String key = entry.getKey();
                String expectedValue = entry.getValue();
                String actualValue = response.jsonPath().getString(key);

                scenario.log("→ Checking key: '" + key + "', Expected: '" + expectedValue + "', Actual: '" + actualValue + "'");
                assertThat("Mismatch in value for key: " + key, actualValue, equalTo(expectedValue));
                scenario.log("Matched: " + key + " = " + actualValue);
            }
            catch(Exception e){
                scenario.log("❌ Exception while validating key: " + entry.getKey() + " - " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
