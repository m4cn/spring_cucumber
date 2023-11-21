package main.java.com.test.clients;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import main.java.com.test.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthClient {

  private final Proxy proxy;

  @Value("${token.url}")
  private String url;

  @Value("${client.id}")
  private String client;

  @Value("${client.secret}")
  private String secret;


  public String getToken() {
    RequestSpecification requestSpecification = proxy.apply(given().relaxedHTTPSValidation().auth().preemptive()
        .basic(client, secret).config(RestAssured.config().encoderConfig(
            EncoderConfig.encoderConfig()
                .encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
        .contentType(ContentType.URLENC.withCharset("UTF-8"))
        .formParam("grant_type", "client_credentials"));

    String accessToken = requestSpecification.post(url).jsonPath().getString("access_token");

    assertNotNull(accessToken);
    return accessToken;
  }
}
