package com.hashmapinc.tempus.pageObjects.home;

import com.hashmapinc.tempus.config.AppConfig;
import com.hashmapinc.tempus.config.UserConfig;
import com.hashmapinc.tempus.enums.UserType;
import com.hashmapinc.tempus.managers.WebDriverManager;
import com.hashmapinc.tempus.pageObjects.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends Page {

    public static final String TITLE = "Tempus | Home";

    @FindBy(how = How.XPATH, using = "/html/body/ui-view/div/md-toolbar/div/tb-user-menu/section/div/div/span[2]")
    private WebElement txt_userRole;

    protected HomePage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public boolean isHomePageFor(UserType userType) {
        return txt_userRole.getText().equals(userType.getRole());
    }
}
