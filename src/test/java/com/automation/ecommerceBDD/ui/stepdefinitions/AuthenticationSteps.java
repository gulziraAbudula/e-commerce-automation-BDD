package com.automation.ecommerceBDD.ui.stepdefinitions;

import com.automation.ecommerceBDD.ui.pages.HomePage;
import com.automation.ecommerceBDD.ui.pages.authPages.CreateAccountPage;
import com.automation.ecommerceBDD.ui.pages.authPages.SignupLoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import java.util.List;
import java.util.Map;

public class AuthenticationSteps {

    private HomePage homepage;
    private SignupLoginPage signupLoginPage;
    private CreateAccountPage createAccountPage;

    /**
     * Feature: User Registration
     * Scenario: TC1 - User should successfully register a new account
     */
    @Given("User launches the browser")
    public void user_launches_the_browser() {
        homepage = new HomePage();
        signupLoginPage = new SignupLoginPage();
        createAccountPage = new CreateAccountPage();
    }
    @Given("User navigates to {string}")
    public void user_navigates_to(String string) {

    }
    @Then("User verifies that home page is visible successfully")
    public void user_verifies_that_home_page_is_visible_successfully() {
        homepage.verifyHomePageLogo();
    }
    @When("User clicks on Signup Login button")
    public void user_clicks_on_Signup_Login_button() {
        homepage.clickSignupLoginBtn();
    }
    @Then("User verifies that New User Signup is visible {string}")
    public void user_verifies_that_New_User_Signup_is_visible(String string) {
        signupLoginPage.verifyNewUserSignUpMsg(string);
    }
    @When("User enters the following signup details:")
    public void user_enters_the_following_signup_details(DataTable dataTable) {
        // Convert DataTable to List of Maps
        List<Map<String, String>> signupData = dataTable.asMaps(String.class, String.class);

        // Assuming only one row for signup
        String name = signupData.get(0).get("name");
        String email = signupData.get(0).get("email");

        // Perform signup
        signupLoginPage.enterSignup(name, email);
    }
    @When("User clicks Signup button")
    public void user_clicks_Signup_button() {
        signupLoginPage.clickSignupBtn();
    }
    @Then("User verifies that Enter Account Information is visible")
    public void user_verifies_that_Enter_Account_Information_is_visible() {
        createAccountPage.verifyEnterAccountInformationMsg();
    }
    @When("User fills the following account information:")
    public void user_fills_the_following_account_information(DataTable dataTable) {
        createAccountPage.fillAccountInformation(dataTable);
    }
    @When("User selects checkbox {string}")
    public void user_selects_checkbox(String string) {
        createAccountPage.selectNewsletterCheckbox().selectSpecialOffersCheckbox();
    }
    @When("User fills the following address details:")
    public void user_fills_the_following_address_details(DataTable dataTable) {
        createAccountPage.fillAddressDetails(dataTable);
    }
    @And("User clicks Create Account button")
    public void user_clicks_Create_Account_button() {
        createAccountPage.clickCreateAccountBtn();
    }
    @Then("User verifies that Account Created is visible")
    public void user_verifies_that_Account_Created_is_visible() {
        createAccountPage.verifyAccountCreatedMsg();
    }
    @When("User clicks Continue button")
    public void user_clicks_Continue_button() {
        createAccountPage.clickContinueBtn();
    }
    @Then("User verifies that {string} is visible")
    public void user_verifies_that_is_visible(String string) {
        createAccountPage.verifyLoggedInAsMsg(string);
    }
    @When("User clicks Delete Account button")
    public void user_clicks_Delete_Account_button() {
        createAccountPage.clickDeleteAccountBtn();
    }
    @Then("User verifies that Account Deleted is visible")
    public void user_verifies_that_Account_Deleted_is_visible() {
        createAccountPage.verifyAccountDeletedMsg();
    }
    @And("User clicks Continue2 button")
    public void user_clicks_Continue2_button() {
        createAccountPage.clickContinueBtn();
    }



    /**
     * Feature: User Registration
     * Scenario: TC1 - User should not be able to register with existing email
     */



    /**
     * Feature: User Registration
     * Scenario: TC1 - User should not be able to register without providing required fields
     */



    /**
     * Feature: User Registration
     * Scenario: TC1 - User should not be able to register with invalid email format
     */



    // *******************************************************************************************

    /**
     * Feature: User Login with Correct Credentials
     * Scenario: TC2 - User should successfully login with correct email and password
     */



    /**
     * Feature: User Login with Correct Credentials
     * Scenario: TC2 - User should not be able to login with incorrect password
     */



    /**
     * Feature: User Login with Correct Credentials
     * Scenario: TC2 - User should not be able to login with incorrect email
     */



    /**
     * Feature: User Login with Correct Credentials
     * Scenario: TC2 - User should not be able to login without providing email and password
     */



    /**
     * Feature: User Login with Correct Credentials
     * Scenario: TC2 - User should not be able to login with invalid email format
     */



    /**
     * Feature: User Login with Correct Credentials
     * Scenario: TC2 - User should be able to logout after login
     */





    // *******************************************************************************************

    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect email and password
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect email only
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect password only
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with case-sensitive incorrect password
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with special characters in password
     */




    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with spaces in email and password
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should see error message on invalid login attempt and be able to retry
     */




    // *******************************************************************************************

    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect email and password
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect email and password
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect email and password
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect email and password
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect email and password
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect email and password
     */



    /**
     * Feature: User Login with Incorrect Credentials
     * Scenario: TC3 - User should not be able to login with incorrect email and password
     */



}
