package com.automation.ecommerceBDD.ui.pages;

import com.automation.ecommerceBDD.ui.pages.authPages.SignupLoginPage;
import com.automation.ecommerceBDD.ui.stepdefinitions.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.automation.ecommerceBDD.ui.utilities.reportRelated.Log4jManager.error;
import static com.automation.ecommerceBDD.ui.utilities.reportRelated.Log4jManager.info;

public class HomePage extends Base {

    @FindBy(xpath = "//a[@href='/']/img[@alt='Website for automation practice']")
    private WebElement logoElement;

    @FindBy(xpath = "//a[contains(text(), 'Signup / Login')]")
    private WebElement signupLoginBtn;

    @FindBy(xpath = "//a[contains(text(), 'Products')]")
    private WebElement productsBtn;

    @FindBy(xpath = "//footer[@id='footer']")
    private WebElement footerElement;

    // ======================= CONSTRUCTOR =======================
    public HomePage() {
        // Initialize all @FindBy annotated elements
        PageFactory.initElements(driver, this);
    }

    public void verifyHomePageLogo() {
        try {
            // Wait for and verify logo
            waitUtil.waitForVisibility(logoElement);
            assert logoElement.isDisplayed() : "Logo not visible";

            // Wait for and verify signup button
            waitUtil.waitForVisibility(signupLoginBtn);
            assert signupLoginBtn.isDisplayed() : "Signup button not visible";

            // Wait for and verify search box
            waitUtil.waitForVisibility(productsBtn);
            assert productsBtn.isDisplayed() : "Product button not visible";

            info("Homepage loaded successfully with all key elements visible!");
        } catch (Exception e) {
            error("Homepage load verification failed: " + e.getMessage());
            throw new AssertionError("Homepage load verification failed", e);
        }
    }

    public SignupLoginPage clickSignupLoginBtn() {
        try {
            clickUtil.safeClick(signupLoginBtn);
            info("User clicked Signup Login button");
        } catch (Exception e) {
            error("Failed to click Signup Login button: " + e.getMessage());
            throw new RuntimeException("Click Signup Login button failed", e);
        }
        return new SignupLoginPage();
    }

    // ======================= GETTER METHODS =======================

    public WebElement getLogoElement() {
        return logoElement;
    }

    public WebElement getSignupLoginBtn() {
        return signupLoginBtn;
    }

    public WebElement getProductsBtn() {
        return productsBtn;
    }

    public WebElement getFooterElement() {
        return footerElement;
    }
}
