Feature: Login as a Tempus User
  As a Tempus User
  I want to able to login to Tempus,
  with a home page design for given user

  @Logout @SmokeTest @Browser
  Scenario: Login with correct TENANT user credentials
    Given a web browser is on the Tempus login page
    And I am not logged on
    When I fill in correct "TENANT" username
    And I fill in correct "TENANT" password
    And I hit enter for submit
    Then I should see Tempus home page for "TENANT"


  @Logout @SmokeTest @Browser
  Scenario: Login with correct CUSTOMER user credentials
    Given a web browser is on the Tempus login page
    And I am not logged on
    When I fill in correct "CUSTOMER" username
    And I fill in correct "CUSTOMER" password
    And I hit enter for submit
    Then I should see Tempus home page for "CUSTOMER"

  @Logout @SmokeTest @Browser
  Scenario: Login with correct SYSADMIN user credentials
    Given a web browser is on the Tempus login page
    And I am not logged on
    When I fill in correct "SYS_ADMIN" username
    And I fill in correct "SYS_ADMIN" password
    And I hit enter for submit
    Then I should see Tempus home page for "SYS_ADMIN"

  @SmokeTest @Browser
  Scenario: Login with incorrect user credentials
    Given a web browser is on the Tempus login page
    And I am not logged on
    When I fill in incorrect "fake@email.com" username
    And I fill in incorrect "fake" password
    And I hit enter for submit
    Then I should see login error

#  @SmokeTest
#  Scenario: Successful Login with Valid Credentials
#    Given User is on Home Page
#    When User Navigate to LogIn Page
#    And User enters Credentials to LogIn
#      | Username   | Password |
#      | testuser_1 | Test@153 |
#      | testuser_2 | Test@154 |
#    Then Message displayed Login Successfully