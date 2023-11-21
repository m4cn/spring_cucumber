package test.java.com.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/",
    glue = {"test.java.com.test"},
    monochrome = true,
    plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json",
        "junit:target/cucumber.xml"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
