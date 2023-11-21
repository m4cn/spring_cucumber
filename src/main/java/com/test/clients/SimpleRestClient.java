package main.java.com.test.clients;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.function.UnaryOperator;
import lombok.RequiredArgsConstructor;
import main.java.com.test.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SimpleRestClient {

  private final static String PATH = "/test";
  private final static UnaryOperator<Response> logResponse = response -> {
    response.then().log().all();
    return response;
  };

  private final AuthClient authClient;
  private final Proxy proxy;

  @Value("${example.origin.url}")
  private String url;

  public Response sampleRequest() {
    return logResponse.apply(
        given().spec(createRequest()).log().all().get(PATH));
  }

  private RequestSpecification createRequest() {
    return proxy.apply(new RequestSpecBuilder()
        .setBaseUri(url)
        .setContentType(ContentType.JSON)
        .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authClient.getToken())
        .setRelaxedHTTPSValidation()
        .build());
  }

}
