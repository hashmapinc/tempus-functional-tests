package com.hashmapinc.tempus.pageObjects.login;

import com.hashmapinc.tempus.enums.UserType;
import com.hashmapinc.tempus.managers.WebDriverManager;
import com.hashmapinc.tempus.pageObjects.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends Page {

    private static final String TITLE = "Tempus | Login";

    public static final String ERROR_REQUESTING_ACCESS_TOKEN = "Error requesting access token.";

    @FindBy(how = How.ID, using = "username-input")
    private WebElement inpTxt_username;

    @FindBy(how = How.ID, using = "password-input")
    private WebElement inpTxt_password;

    @FindBy(how = How.LINK_TEXT, using = "Login")
    private WebElement btn_login;

    @FindBy(how = How.XPATH, using = "/html/body/ui-view/div/md-toolbar/div/tb-user-menu/section/md-menu/button/md-icon")
    private WebElement menu_logout;

    @FindBy(how = How.XPATH, using = "//*[@id=\"menu_container_0\"]/md-menu-content/md-menu-item[2]/button/span")
    private WebElement btn_logout;

    @FindBy(how = How.XPATH, using = "//*[@id=\"toast-parent\"]/md-toast/div/div")
    private WebElement errTxt_login;


    protected LoginPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public void navigateToLoginPage(){
        navigateToPage(appConfig.getHost());
    }

    public boolean isUserAlreadyLoggedIn(){
        wait.untilPageLoadComplete(getWebDriverManager().getDriver());
        return !isPageWithTitle(TITLE);
    }

    public void enterUsername(UserType userType){
        switch (userType){
            case TENANT: enterUsername(userConfig.getTenant().getUsername()); break;
            case CUSTOMER: enterUsername(userConfig.getCustomer().getUsername()); break;
            case SYS_ADMIN: enterUsername(userConfig.getSysAdmin().getUsername()); break;
        }
    }

    public void enterUsername(String username){
        inpTxt_username.sendKeys(username);
    }

    public void enterCorrectPassword(UserType userType){
        switch (userType){
            case TENANT: enterPassword(userConfig.getTenant().getPassword()); break;
            case CUSTOMER: enterPassword(userConfig.getCustomer().getPassword()); break;
            case SYS_ADMIN: enterPassword(userConfig.getSysAdmin().getPassword()); break;
        }
    }

    public void enterPassword(String password){
        inpTxt_password.sendKeys(password);
    }

    public void submitLogin(){
        inpTxt_password.submit();
        //driverWait(20).until(ExpectedConditions.titleContains(homePageTitle));
    }

    public void logout(){
        wait.untilPageLoadComplete(getWebDriverManager().getDriver());
        //driverWait(20).until(ExpectedConditions.visibilityOf(menu_logout));
        menu_logout.click();
        //driverWait(20).until(ExpectedConditions.visibilityOf(btn_logout));
        btn_logout.click();
        wait.untilPageLoadComplete(getWebDriverManager().getDriver());
    }

    public boolean validateIfLoginFailed(){
        driverWait(5).until(ExpectedConditions.visibilityOf(errTxt_login));
        return errTxt_login.getText().equals(ERROR_REQUESTING_ACCESS_TOKEN);
    }

}
