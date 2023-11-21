package test.java.com.test;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import main.java.com.test.TestConfig;
import main.java.com.test.clients.SimpleRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StepDefinitions {
  private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper().configure(
      DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  private SimpleRestClient simpleRequestClient;

  @When("You enter the example com")
  public void createV2WarrantyForModelNumber(String modelNumber) {
    simpleRequestClient.sampleRequest();
  }

  @Then("You are on example com")
  public void verifyWarrantyAcceptedStatusCode() {
    assertEquals(200, response.statusCode());
  }


  @Given("The example com is online")
  public void createWarrantySkipGLM(String modelNumber) {
    response = simpleRequestClient.sampleRequest();
  }

}
