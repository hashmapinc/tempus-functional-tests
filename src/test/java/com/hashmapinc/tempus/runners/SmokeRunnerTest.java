package com.hashmapinc.tempus.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "junit:target/cucumber.xml"},
        features = "src/test/resources/features",
        glue = {"com.hashmapinc.tempus.stepdefs"},
        tags = {"@SmokeTest"})
public class SmokeRunnerTest {
}
