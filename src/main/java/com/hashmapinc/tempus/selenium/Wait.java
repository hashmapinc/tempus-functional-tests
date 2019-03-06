package com.hashmapinc.tempus.selenium;

import com.hashmapinc.tempus.config.AppConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class Wait {

    @Autowired
    private AppConfig appConfig;

    public void untilJqueryIsDone(WebDriver driver){
        untilJqueryIsDone(driver, (long) appConfig.getImplicitlyWait());
    }

    public void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds){
        until(driver, (d) ->
        {
            Boolean isJqueryCallDone = (Boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) System.out.println("JQuery call is in Progress");
            return isJqueryCallDone;
        }, timeoutInSeconds);
    }

    public void untilPageLoadComplete(WebDriver driver) {
        untilPageLoadComplete(driver, (long) appConfig.getImplicitlyWait());
    }

    public void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds){
        until(driver, (d) ->
        {
            Boolean isPageLoaded = (Boolean)((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            if (!isPageLoaded) System.out.println("Document is loading");
            return isPageLoaded;
        }, timeoutInSeconds);
    }

    public void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition){
        until(driver, waitCondition, (long) appConfig.getImplicitlyWait());
    }


    private void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds){
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        webDriverWait.withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS));
        try{
            webDriverWait.until(waitCondition);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}