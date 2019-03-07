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

    @FindBy(how = How.XPATH, using = "/html/body/ui-view/div/md-toolbar/div/tb-user-menu/section/md-menu/button/md-icon")
    private WebElement menu_logout;

    @FindBy(how = How.XPATH, using = "//*[@id=\"menu_container_0\"]/md-menu-content/md-menu-item[2]/button/span")
    private WebElement btn_logout;


    protected HomePage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public boolean isHomePageFor(UserType userType) {
        return txt_userRole.getText().equals(userType.getRole());
    }

    public void logout(){
        wait.untilPageLoadComplete(getWebDriverManager().getDriver());
        //driverWait(20).until(ExpectedConditions.visibilityOf(menu_logout));
        menu_logout.click();
        //driverWait(20).until(ExpectedConditions.visibilityOf(btn_logout));
        btn_logout.click();
        wait.untilPageLoadComplete(getWebDriverManager().getDriver());
    }
}
