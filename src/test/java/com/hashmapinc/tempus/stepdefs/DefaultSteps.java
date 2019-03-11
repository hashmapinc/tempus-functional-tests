package com.hashmapinc.tempus.stepdefs;

import com.hashmapinc.tempus.SpringApplicationTest;
import com.hashmapinc.tempus.managers.WebDriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;

//This is to initialize spring context
public class DefaultSteps extends SpringApplicationTest implements En {

    @Autowired
    private WebDriverManager webDriverManager;

    @After("@Browser")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("Scenario failed");
            final byte[] screenshot = ((TakesScreenshot) webDriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); //stick it in the report
        }
        //webDriverManager.getDriver().close();
    }
}
