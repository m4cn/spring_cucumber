package main.java.com.test;

import main.java.com.test.clients.AuthClient;
import main.java.com.test.clients.SimpleRestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

  @Bean
  public Proxy proxy() {
    return new Proxy();
  }

  @Bean
  public AuthClient authClient(Proxy proxy) {
    return new AuthClient(proxy);
  }

  @Bean
  public SimpleRestClient simpleRestClient(AuthClient authClient, Proxy proxy) {
    return new SimpleRestClient(authClient, proxy);
  }

  public static void main(String[] args) {
    SpringApplication.run(TestConfig.class, args);
  }

}
