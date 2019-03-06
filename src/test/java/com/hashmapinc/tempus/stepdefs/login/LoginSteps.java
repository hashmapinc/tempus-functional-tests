package com.hashmapinc.tempus.stepdefs.login;

import com.hashmapinc.tempus.enums.UserType;
import com.hashmapinc.tempus.pageObjects.home.HomePage;
import com.hashmapinc.tempus.pageObjects.login.LoginPage;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps implements En {
    @Autowired
    private LoginPage loginPage;

    @Autowired
    private HomePage homePage;

    public LoginSteps(){
        Given("^a web browser is on the Tempus login page$", () -> {
            // Write code here that turns the phrase above into concrete actions
            loginPage.navigateToLoginPage();
        });

        Given("^I am not logged on$", () -> {
            // Write code here that turns the phrase above into concrete actions
            assertThat(loginPage.isUserAlreadyLoggedIn()).isFalse();
        });
        When("^I fill in correct \"([^\"]*)\" username$", (String userType) -> {
            loginPage.enterUsername(UserType.valueOf(userType));
        });
        And("^I fill in correct \"([^\"]*)\" password$", (String userType) -> {
            loginPage.enterCorrectPassword(UserType.valueOf(userType));
        });
        And("^I hit enter for submit$", () -> {
            loginPage.submitLogin();
        });
        Then("^I should see Tempus home page for \"([^\"]*)\"$", (String userType) -> {
            assertThat(homePage.isHomePageFor(UserType.valueOf(userType))).isTrue();
        });

        After(new String[]{"@logout"}, (Scenario scenario) -> loginPage.logout());

        // login Error Scenario
        When("^I fill in incorrect \"([^\"]*)\" username$", (String username) -> {
            loginPage.enterUsername(username);
        });
        And("^I fill in incorrect \"([^\"]*)\" password$", (String password) -> {
            loginPage.enterPassword(password);
        });

        Then("^I should see login error$", () -> {
            assertThat(loginPage.validateIfLoginFailed()).isTrue();
        });

    }
}
