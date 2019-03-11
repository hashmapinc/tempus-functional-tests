package com.hashmapinc.tempus.pages.login;

import com.hashmapinc.tempus.config.UserConfig;
import com.hashmapinc.tempus.enums.UserType;
import com.hashmapinc.tempus.managers.WebDriverManager;
import com.hashmapinc.tempus.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends Page {

    @Autowired
    protected UserConfig userConfig;

    private static final String TITLE = "Tempus | Login";

    public static final String ERROR_REQUESTING_ACCESS_TOKEN = "Error requesting access token.";

    @FindBy(how = How.ID, using = "username-input")
    private WebElement inpTxt_username;

    @FindBy(how = How.ID, using = "password-input")
    private WebElement inpTxt_password;

    @FindBy(how = How.LINK_TEXT, using = "Login")
    private WebElement btn_login;

    @FindBy(how = How.XPATH, using = "//*[@id=\"toast-parent\"]/md-toast/div/div")
    private WebElement errTxt_login;


    protected LoginPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public void navigateToLoginPage(){
        navigateToPage(appConfig.getHost());
    }

    public boolean isUserAlreadyLoggedIn(){
        waitForPageLoad();
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

    public boolean validateIfLoginFailed(){
        driverWait(5).until(ExpectedConditions.visibilityOf(errTxt_login));
        return errTxt_login.getText().equals(ERROR_REQUESTING_ACCESS_TOKEN);
    }

}
