package com.hashmapinc.tempus.managers;

import com.hashmapinc.tempus.config.AppConfig;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class WebDriverManager {

    private AppConfig appConfig;

    @Getter
    private WebDriver driver;

    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    @Autowired
    public WebDriverManager(AppConfig appConfig) {
        this.appConfig = appConfig;
        driver = createDriver();
    }

    private WebDriver createDriver() {
        switch (appConfig.getDriverType()) {
            case FIREFOX : driver = new FirefoxDriver();
                break;
            case CHROME :
                System.setProperty(CHROME_DRIVER_PROPERTY, appConfig.getDriverPath());
                driver = new ChromeDriver();
                break;
            case INTERNET_EXPLORER: driver = new InternetExplorerDriver();
                break;
        }

        if(appConfig.isWindowMaximize())
            driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(appConfig.getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void quitDriver() {
        driver.close();
        driver.quit();
    }

}
