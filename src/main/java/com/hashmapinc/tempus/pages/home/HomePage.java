package com.hashmapinc.tempus.pages.home;

import com.hashmapinc.tempus.enums.UserType;
import com.hashmapinc.tempus.managers.WebDriverManager;
import com.hashmapinc.tempus.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
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
        waitForPageLoad();

        menu_logout.click();
        btn_logout.click();

        waitForPageLoad();
    }
}
