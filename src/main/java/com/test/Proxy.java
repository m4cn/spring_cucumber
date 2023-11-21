package main.java.com.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Proxy {

  @Value("${proxy.host}")
  private String host;

  @Value("${proxy.port}")
  private Integer port;

  @Value("${proxy.scheme}")
  private String scheme;

  public RequestSpecBuilder apply(RequestSpecBuilder builder) {
    if (ObjectUtils.allNotNull(host, port, scheme)) {
      builder.setProxy(host, port, scheme);
    }
    return builder;
  }

  public RequestSpecification apply(RequestSpecification specification) {
    if (ObjectUtils.allNotNull(host, port, scheme)) {
      specification.proxy(host, port, scheme);
    }
    return specification;
  }

}
