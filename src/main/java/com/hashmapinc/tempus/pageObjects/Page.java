package com.hashmapinc.tempus.pageObjects;

import com.hashmapinc.tempus.config.AppConfig;
import com.hashmapinc.tempus.config.UserConfig;
import com.hashmapinc.tempus.managers.WebDriverManager;
import com.hashmapinc.tempus.selenium.Wait;
import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PreDestroy;


public abstract class Page {

    @Autowired
    protected AppConfig appConfig;

    @Autowired
    protected UserConfig userConfig;

    @Autowired
    protected Wait wait;

    @Getter(AccessLevel.PROTECTED)
    private WebDriverManager webDriverManager;

    @Autowired
    protected Page(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
        PageFactory.initElements(webDriverManager.getDriver(), this);

    }

    protected WebDriverWait driverWait(long timeoutSeconds) {
        return new WebDriverWait(webDriverManager.getDriver(), timeoutSeconds);
    }

    protected void navigateToPage(String pageUrl){
        getWebDriverManager().getDriver().navigate().to(pageUrl);
    }

    protected boolean isPageWithTitle(String title) {
        return getWebDriverManager().getDriver().getTitle().equals(title);
    }

    public void quit(){
        webDriverManager.getDriver().quit();
    }

    @PreDestroy
    private void destroy(){
        webDriverManager.quitDriver();
    }

}
