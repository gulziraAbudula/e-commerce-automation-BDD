package com.automation.ecommerceBDD.pages.authPages;

import com.automation.ecommerceBDD.stepdefinitions.Base;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

import static com.automation.ecommerceBDD.utilities.reportRelated.Log4jManager.error;
import static com.automation.ecommerceBDD.utilities.reportRelated.Log4jManager.info;

/**
 * CreateAccountPage - Page Object Model
 *
 * This class represents the Create Account page of Automation Exercise website
 * Integrated with WaitUtil for better wait handling and modern practices
 */
public class CreateAccountPage extends Base {

    // ======================= VARIABLES =======================

    // Gender Selection
    @FindBy(xpath = "//label[contains(@for,'id_gender')]")
    private List<WebElement> genderRadios;

    // Account Information
    @FindBy(xpath = "//h2/b[contains(text(), 'Enter Account Information')]")
    private WebElement enterAccountInfoMsg;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//select[@id='days']")
    private WebElement daysDropdown;

    @FindBy(xpath = "//select[@id='months']")
    private WebElement monthsDropdown;

    @FindBy(xpath = "//select[@id='years']")
    private WebElement yearsDropdown;

    // Checkboxes
    @FindBy(xpath = "//input[@id='newsletter']")
    private WebElement newsletterCheckbox;

    @FindBy(xpath = "//input[@id='optin']")
    private WebElement specialOffersCheckbox;

    // Address Information
    @FindBy(id = "first_name")
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement addressInput;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipCodeInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;

    // Account Actions
    @FindBy(xpath = "//button[contains(text(), 'Create Account')]")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//h2[@data-qa='account-created']/b")
    private WebElement accountCreatedMsg;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueBtn;

    @FindBy(xpath = "//*[@id='header']//a[contains(text(), 'Logged in as')]")
    private WebElement loggedInAsMsg;

    @FindBy(tagName = "b")
    private WebElement boldElement;

    @FindBy(xpath = "//a[contains(text(), 'Delete Account')]")
    private WebElement deleteAccountBtn;

    @FindBy(xpath = "//h2[@data-qa='account-deleted']/b")
    private WebElement accountDeletedMsg;

    // ======================= CONSTRUCTOR =======================
    public CreateAccountPage() {
        // Initialize all @FindBy annotated elements
        PageFactory.initElements(driver, this);
    }

    // ======================= FILL FORM METHODS =======================

    /**
     * Fill account information using DataTable
     * Feature: When User fills the following account information:
     * | fieldName | value       |
     * | title     | Mr.         |
     * | password  | Password123 |
     * etc.
     */
    public CreateAccountPage fillAccountInformation(DataTable dataTable) {
        try {
            List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

            for (Map<String, String> row : data) {
                String fieldName = row.get("fieldName");
                String value = row.get("value");

                switch (fieldName.toLowerCase()) {
                    case "title":
                        selectGender(value);
                        break;
                    case "password":
                        enterPassword(value);
                        break;
                    case "dob_day":
                    case "day":
                        selectDay(value);
                        break;
                    case "dob_month":
                    case "month":
                        selectMonth(value);
                        break;
                    case "dob_year":
                    case "year":
                        selectYear(value);
                        break;
                    default:
                        error("Unknown field: " + fieldName);
                }
            }

            info("Account information filled successfully!");
        } catch (Exception e) {
            error("Failed to fill account information: " + e.getMessage());
            throw new RuntimeException("Fill account information failed", e);
        }
        return this;
    }

    /**
     * Fill address details using DataTable
     * Feature: When User fills the following address details:
     * | fieldName    | value             |
     * | firstName    | John              |
     * | lastName     | Doe               |
     * etc.
     */
    public CreateAccountPage fillAddressDetails(DataTable dataTable) {
        try {
            List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

            for (Map<String, String> row : data) {
                String fieldName = row.get("fieldName");
                String value = row.get("value");

                switch (fieldName.toLowerCase()) {
                    case "firstname":
                        enterFirstName(value);
                        break;
                    case "lastname":
                        enterLastName(value);
                        break;
                    case "company":
                        enterCompany(value);
                        break;
                    case "address":
                        enterAddress(value);
                        break;
                    case "address2":
                        enterAddress2(value);
                        break;
                    case "country":
                        selectCountry(value);
                        break;
                    case "state":
                        enterState(value);
                        break;
                    case "city":
                        enterCity(value);
                        break;
                    case "zipcode":
                        enterZipCode(value);
                        break;
                    case "mobilenumber":
                        enterMobileNumber(value);
                        break;
                    default:
                        error("Unknown field: " + fieldName);
                }
            }

            info("Address details filled successfully!");
        } catch (Exception e) {
            error("Failed to fill address details: " + e.getMessage());
            throw new RuntimeException("Fill address details failed", e);
        }
        return this;
    }

    // ======================= ACCOUNT INFORMATION METHODS =======================

    /**
     * Select gender (title)
     */
    private CreateAccountPage selectGender(String gender) {
        try {
            // Find and click the gender radio button
            for (WebElement radio : genderRadios) {
                String radioText = radio.getText().toLowerCase();
                if (radioText.contains(gender.toLowerCase())) {
                    WebElement clickableRadio = waitUtil.waitForClickable(radio);
                    clickableRadio.click();
                    info("User selected gender: " + gender);
                    return this;
                }
            }
            throw new Exception("Gender not found: " + gender);
        } catch (Exception e) {
            error("Failed to select gender: " + e.getMessage());
            throw new RuntimeException("Select gender failed", e);
        }
    }

    /**
     * Enter password
     */
    private CreateAccountPage enterPassword(String password) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(passwordInput);
            clickableField.clear();
            clickableField.sendKeys(password);
            info("User entered password");
        } catch (Exception e) {
            error("Failed to enter password: " + e.getMessage());
            throw new RuntimeException("Enter password failed", e);
        }
        return this;
    }

    /**
     * Select day from dropdown
     */
    private CreateAccountPage selectDay(String day) {
        try {
            WebElement clickableDropdown = waitUtil.waitForClickable(daysDropdown);
            selectDropdownUtil.selectByVisibleText(clickableDropdown, day);
            //new Select(clickableDropdown).selectByValue(day);
            info("User selected day: " + day);
        } catch (Exception e) {
            error("Failed to select day: " + e.getMessage());
            throw new RuntimeException("Select day failed", e);
        }
        return this;
    }

    /**
     * Select month from dropdown
     */
    private CreateAccountPage selectMonth(String month) {
        try {
            WebElement clickableDropdown = waitUtil.waitForClickable(monthsDropdown);
            selectDropdownUtil.selectByVisibleText(clickableDropdown, month);
            info("User selected month: " + month);
        } catch (Exception e) {
            error("Failed to select month: " + e.getMessage());
            throw new RuntimeException("Select month failed", e);
        }
        return this;
    }

    /**
     * Select year from dropdown
     */
    private CreateAccountPage selectYear(String year) {
        try {
            WebElement clickableDropdown = waitUtil.waitForClickable(yearsDropdown);
            selectDropdownUtil.selectByVisibleText(clickableDropdown, year);
            info("User selected year: " + year);
        } catch (Exception e) {
            error("Failed to select year: " + e.getMessage());
            throw new RuntimeException("Select year failed", e);
        }
        return this;
    }

    /**
     * Select newsletter checkbox
     * Feature: And User selects checkbox 'Sign up for our newsletter!'
     */
    public CreateAccountPage selectNewsletterCheckbox() {
        try {
            clickUtil.clickCheckbox(newsletterCheckbox, "newsletter");
            info("User selected newsletter checkbox");
        } catch (Exception e) {
            error("Failed to select newsletter checkbox: " + e.getMessage());
            throw new RuntimeException("Select newsletter checkbox failed", e);
        }
        return this;
    }

    /**
     * Select special offers checkbox
     * Feature: And User selects checkbox 'Receive special offers from our partners!'
     */
    public CreateAccountPage selectSpecialOffersCheckbox() {
        try {
            clickUtil.clickCheckbox(specialOffersCheckbox, "special offers");
            info("User selected special offers checkbox");
        } catch (Exception e) {
            error("Failed to select special offers checkbox: " + e.getMessage());
            throw new RuntimeException("Select special offers checkbox failed", e);
        }
        return this;
    }

    // ======================= ADDRESS INFORMATION METHODS =======================

    /**
     * Enter first name
     */
    private CreateAccountPage enterFirstName(String firstName) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(firstNameInput);
            clickableField.clear();
            clickableField.sendKeys(firstName);
            info("User entered first name: " + firstName);
        } catch (Exception e) {
            error("Failed to enter first name: " + e.getMessage());
            throw new RuntimeException("Enter first name failed", e);
        }
        return this;
    }

    /**
     * Enter last name
     */
    private CreateAccountPage enterLastName(String lastName) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(lastNameInput);
            clickableField.clear();
            clickableField.sendKeys(lastName);
            info("User entered last name: " + lastName);
        } catch (Exception e) {
            error("Failed to enter last name: " + e.getMessage());
            throw new RuntimeException("Enter last name failed", e);
        }
        return this;
    }

    /**
     * Enter company
     */
    private CreateAccountPage enterCompany(String company) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(companyInput);
            clickableField.clear();
            clickableField.sendKeys(company);
            info("User entered company: " + company);
        } catch (Exception e) {
            error("Failed to enter company: " + e.getMessage());
            throw new RuntimeException("Enter company failed", e);
        }
        return this;
    }

    /**
     * Enter address line 1
     */
    private CreateAccountPage enterAddress(String address) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(addressInput);
            clickableField.clear();
            clickableField.sendKeys(address);
            info("User entered address: " + address);
        } catch (Exception e) {
            error("Failed to enter address: " + e.getMessage());
            throw new RuntimeException("Enter address failed", e);
        }
        return this;
    }

    /**
     * Enter address line 2
     */
    private CreateAccountPage enterAddress2(String address2) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(address2Input);
            clickableField.clear();
            clickableField.sendKeys(address2);
            info("User entered address line 2: " + address2);
        } catch (Exception e) {
            error("Failed to enter address line 2: " + e.getMessage());
            throw new RuntimeException("Enter address line 2 failed", e);
        }
        return this;
    }

    /**
     * Select country from dropdown
     */
    private CreateAccountPage selectCountry(String country) {
        try {
            WebElement clickableDropdown = waitUtil.waitForClickable(countryDropdown);
            new Select(clickableDropdown).selectByVisibleText(country);
            info("User selected country: " + country);
        } catch (Exception e) {
            error("Failed to select country: " + e.getMessage());
            throw new RuntimeException("Select country failed", e);
        }
        return this;
    }

    /**
     * Enter state
     */
    private CreateAccountPage enterState(String state) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(stateInput);
            clickableField.clear();
            clickableField.sendKeys(state);
            info("User entered state: " + state);
        } catch (Exception e) {
            error("Failed to enter state: " + e.getMessage());
            throw new RuntimeException("Enter state failed", e);
        }
        return this;
    }

    /**
     * Enter city
     */
    private CreateAccountPage enterCity(String city) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(cityInput);
            clickableField.clear();
            clickableField.sendKeys(city);
            info("User entered city: " + city);
        } catch (Exception e) {
            error("Failed to enter city: " + e.getMessage());
            throw new RuntimeException("Enter city failed", e);
        }
        return this;
    }

    /**
     * Enter zip code
     */
    private CreateAccountPage enterZipCode(String zipCode) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(zipCodeInput);
            clickableField.clear();
            clickableField.sendKeys(zipCode);
            info("User entered zip code: " + zipCode);
        } catch (Exception e) {
            error("Failed to enter zip code: " + e.getMessage());
            throw new RuntimeException("Enter zip code failed", e);
        }
        return this;
    }

    /**
     * Enter mobile number
     */
    private CreateAccountPage enterMobileNumber(String mobileNumber) {
        try {
            WebElement clickableField = waitUtil.waitForClickable(mobileNumberInput);
            clickableField.clear();
            clickableField.sendKeys(mobileNumber);
            info("User entered mobile number: " + mobileNumber);
        } catch (Exception e) {
            error("Failed to enter mobile number: " + e.getMessage());
            throw new RuntimeException("Enter mobile number failed", e);
        }
        return this;
    }

    // ======================= ACTION METHODS =======================

    /**
     * Click Create Account button
     * Feature: And User clicks 'Create Account' button
     */
    public CreateAccountPage clickCreateAccountBtn() {
        try {
            clickUtil.safeClick(createAccountBtn);
            info("User clicked Create Account button");
        } catch (Exception e) {
            error("Failed to click Create Account button: " + e.getMessage());
            throw new RuntimeException("Click Create Account button failed", e);
        }
        return this;
    }

    /**
     * Click Continue button
     * Feature: When User clicks 'Continue' button
     */
    public CreateAccountPage clickContinueBtn() {
        try {
            clickUtil.safeClick(continueBtn);
            info("User clicked Continue button");
        } catch (Exception e) {
            error("Failed to click Continue button: " + e.getMessage());
            throw new RuntimeException("Click Continue button failed", e);
        }
        return this;
    }

    /**
     * Click Delete Account button
     * Feature: When User clicks 'Delete Account' button
     */
    public CreateAccountPage clickDeleteAccountBtn() {
        try {
            clickUtil.safeClick(deleteAccountBtn);
            info("User clicked Delete Account button");
        } catch (Exception e) {
            error("Failed to click Delete Account button: " + e.getMessage());
            throw new RuntimeException("Click Delete Account button failed", e);
        }
        return this;
    }

    // ======================= VERIFICATION METHODS =======================

    /**
     * Verify 'ENTER ACCOUNT INFORMATION' message is visible
     * Feature: Then User verifies that 'ENTER ACCOUNT INFORMATION' is visible
     */
    public void verifyEnterAccountInformationMsg() {
        try {
            // Wait for the "ENTER ACCOUNT INFORMATION" message to be visible
            WebElement visibleMsg = waitUtil.waitForVisibility(enterAccountInfoMsg);

            // Assert that the message is displayed
            assert visibleMsg.isDisplayed() : "Enter Account Information message is not visible";

            info("'ENTER ACCOUNT INFORMATION' message is visible successfully!");
        } catch (Exception e) {
            error("Failed to verify 'ENTER ACCOUNT INFORMATION' message: " + e.getMessage());
            throw new AssertionError("Enter Account Information message verification failed", e);
        }
    }

    /**
     * Verify 'ACCOUNT CREATED!' message is visible
     * Feature: Then User verifies that 'ACCOUNT CREATED!' is visible
     */
    public void verifyAccountCreatedMsg() {
        try {
            // Wait for the "ACCOUNT CREATED!" message to be visible
            WebElement visibleMsg = waitUtil.waitForVisibility(accountCreatedMsg);

            // Assert that the message is displayed
            assert visibleMsg.isDisplayed() : "Account Created message is not visible";
            info("'ACCOUNT CREATED!' message is visible successfully!");
        } catch (Exception e) {
            error("Failed to verify 'ACCOUNT CREATED!' message: " + e.getMessage());
            throw new AssertionError("Account Created message verification failed", e);
        }
    }

    /**
     * Verify 'Logged in as [username]' message is visible
     * Feature: Then User verifies that 'Logged in as John' is visible
     */
    public void verifyLoggedInAsMsg(String username) {
        try {
            // Wait for logged in message to be visible
            WebElement visibleMsg = waitUtil.waitForVisibility(loggedInAsMsg);

            // Assert that the message is displayed
            assert visibleMsg.isDisplayed() : "Logged in message is not visible";
            info("'Logged in as " + username + "' is visible successfully!");

            // Verify the username in the message
            assertUtil.verifyText(boldElement, username);
            info("Logged in as " + boldElement.getText() + " matched with " + username);

        } catch (Exception e) {
            error("Failed to verify 'Logged in as' message: " + e.getMessage());
            throw new AssertionError("Logged in as message verification failed", e);
        }
    }

    /**
     * Verify 'ACCOUNT DELETED!' message is visible
     * Feature: Then User verifies that 'ACCOUNT DELETED!' is visible
     */
    public void verifyAccountDeletedMsg() {
        try {
            // Wait for the "ACCOUNT DELETED!" message to be visible
            WebElement visibleMsg = waitUtil.waitForVisibility(accountDeletedMsg);

            // Assert that the message is displayed
            assert visibleMsg.isDisplayed() : "Account Deleted message is not visible";

            info("'ACCOUNT DELETED!' message is visible successfully!");
        } catch (Exception e) {
            error("Failed to verify 'ACCOUNT DELETED!' message: " + e.getMessage());
            throw new AssertionError("Account Deleted message verification failed", e);
        }
    }
}