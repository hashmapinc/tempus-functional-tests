package com.hashmapinc.tempus.cucumber;


import com.hashmapinc.tempus.managers.WebDriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestContext {
    @Autowired
    private WebDriverManager webDriverManager;
    @Autowired
    private ScenarioContext scenarioContext;
}