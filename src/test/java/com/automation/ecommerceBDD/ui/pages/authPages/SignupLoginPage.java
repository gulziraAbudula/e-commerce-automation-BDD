package com.automation.ecommerceBDD.ui.pages.authPages;

import com.automation.ecommerceBDD.ui.stepdefinitions.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.automation.ecommerceBDD.ui.utilities.reportRelated.Log4jManager.error;
import static com.automation.ecommerceBDD.ui.utilities.reportRelated.Log4jManager.info;

public class SignupLoginPage extends Base {

    @FindBy(xpath = "//h2[contains(text(), 'New User Signup!')]")
    private WebElement newUserSignupElement;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement loginEmailElement;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement loginPasswordElement;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginBtnElement;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement signupNameElement;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmailElement;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signupBtnElement;

    @FindBy(xpath = "//p[contains(text(), 'Email Address already exist')]")
    private WebElement emailExistElement;

    // ======================= CONSTRUCTOR =======================
    public SignupLoginPage() {
        // Initialize all @FindBy annotated elements
        PageFactory.initElements(driver, this);
    }

    // ======================= ACTION METHODS =======================

    /**
     * Complete login action: enter email, password, and click login
     */
    public SignupLoginPage enterLogin(String email, String password) {
        return enterLoginEmail(email)
                .enterLoginPassword(password);
    }

    /**
     * Complete signup action: enter name, email, and click signup
     */
    public SignupLoginPage enterSignup(String name, String email) {
        return enterSignupName(name)
                .enterSignupEmail(email);
    }

    /**
     * Enter login email
     */
    private SignupLoginPage enterLoginEmail(String email) {
        try {
            // Wait for email field to be visible and clickable
            WebElement visibleField = waitUtil.waitForVisibility(loginEmailElement);
            WebElement clickableField = waitUtil.waitForClickable(visibleField);

            // Clear field and enter email
            clickableField.clear();
            clickableField.sendKeys(email);

            info("User entered login email: " + email);
        } catch (Exception e) {
            error("Failed to enter login email: " + e.getMessage());
            throw new RuntimeException("Enter login email failed", e);
        }
        return this;
    }

    /**
     * Enter login password
     */
    private SignupLoginPage enterLoginPassword(String password) {
        try {
            // Wait for password field to be visible and clickable
            WebElement visibleField = waitUtil.waitForVisibility(loginPasswordElement);
            WebElement clickableField = waitUtil.waitForClickable(visibleField);

            // Clear field and enter password
            clickableField.clear();
            clickableField.sendKeys(password);

            info("User entered login password");
        } catch (Exception e) {
            error("Failed to enter login password: " + e.getMessage());
            throw new RuntimeException("Enter login password failed", e);
        }
        return this;
    }

    /**
     * Click login button
     */
    public void clickLoginBtn() {
        try {
            clickUtil.safeClick(loginBtnElement);
            info("User clicked login button");
        } catch (Exception e) {
            error("Failed to click login button: " + e.getMessage());
            throw new RuntimeException("Click login button failed", e);
        }
    }

    /**
     * Enter signup name
     */
    private SignupLoginPage enterSignupName(String name) {
        try {
            // Wait for name field to be visible and clickable
            WebElement visibleField = waitUtil.waitForVisibility(signupNameElement);
            WebElement clickableField = waitUtil.waitForClickable(visibleField);

            // Clear field and enter name
            clickableField.clear();
            clickableField.sendKeys(name);

            info("User entered signup name: " + name);
        } catch (Exception e) {
            error("Failed to enter signup name: " + e.getMessage());
            throw new RuntimeException("Enter signup name failed", e);
        }
        return this;
    }

    /**
     * Enter signup email
     */
    private SignupLoginPage enterSignupEmail(String email) {
        try {
            // Wait for email field to be visible and clickable
            WebElement visibleField = waitUtil.waitForVisibility(signupEmailElement);
            WebElement clickableField = waitUtil.waitForClickable(visibleField);

            // Clear field and enter email
            clickableField.clear();
            clickableField.sendKeys(email);

            info("User entered signup email: " + email);
        } catch (Exception e) {
            error("Failed to enter signup email: " + e.getMessage());
            throw new RuntimeException("Enter signup email failed", e);
        }
        return this;
    }

    /**
     * Click signup button
     */
    public void clickSignupBtn() {
        try {
            clickUtil.safeClick(signupBtnElement);
            info("User clicked signup button");
        } catch (Exception e) {
            error("Failed to click signup button: " + e.getMessage());
            throw new RuntimeException("Click signup button failed", e);
        }
    }

    /************************ Assertions *************************/

    /**
     * Verify 'New User Signup!' message is visible
     * Waits for element to be visible before assertion
     */
    public void verifyNewUserSignUpMsg(String expectedValue) {
        try {
            // Wait for the "New User Signup!" message to be visible
            WebElement visibleMsg = waitUtil.waitForVisibility(newUserSignupElement);

            // Assert that the message is displayed
            assert visibleMsg.isDisplayed() : "New User Signup message is not visible";
            info("'New User Signup!' message is visible successfully!");

            assertUtil.verifyText(visibleMsg, expectedValue);
            info(visibleMsg.getText() + " matched with " + expectedValue);

        } catch (Exception e) {
            error("Failed to verify 'New User Signup!' message: " + e.getMessage());
            throw new AssertionError("New User Signup message verification failed", e);
        }
    }

    /**
     * Verify email already exists error message
     */
    public void verifyEmailExistMsg() {
        try {
            // Wait for error message to be visible
            WebElement visibleMsg = waitUtil.waitForVisibility(emailExistElement);

            // Assert error message is displayed
            assert visibleMsg.isDisplayed() : "Email already exist message is not visible";

            info("Email exist error message is visible successfully!");
        } catch (Exception e) {
            error("Failed to verify email exist message: " + e.getMessage());
            throw new AssertionError("Email exist message verification failed", e);
        }
    }
}