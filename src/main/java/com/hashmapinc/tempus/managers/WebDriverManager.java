package com.hashmapinc.tempus.managers;

import com.hashmapinc.tempus.config.AppConfig;
import com.hashmapinc.tempus.config.UserConfig;
import com.hashmapinc.tempus.enums.DriverType;
import com.hashmapinc.tempus.enums.EnvironmentType;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
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
        switch (appConfig.getEnvironmentType()) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
        }
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() {
        switch (appConfig.getDriverType()) {
            case FIREFOX : driver = new FirefoxDriver();
                break;
            case CHROME :
                System.setProperty(CHROME_DRIVER_PROPERTY, appConfig.getDriverPath());
                driver = new ChromeDriver();
                break;
            case INTERNETEXPLORER : driver = new InternetExplorerDriver();
                break;
        }

        if(appConfig.isWindowMaximize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(appConfig.getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void quitDriver() {
        driver.close();
        driver.quit();
    }

}
