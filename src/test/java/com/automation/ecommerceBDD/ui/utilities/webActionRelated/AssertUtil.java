package com.automation.ecommerceBDD.ui.utilities.webActionRelated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertUtil {

    private WebDriver driver;

    public AssertUtil(WebDriver inputDriver) {
        this.driver = inputDriver;
    }

    // ---------- TEXT ASSERTIONS ----------

    // Assert text using By locator
    public void verifyText(By by, String expectedValue) {
        WebElement element = driver.findElement(by);
        String actualText = element.getText();
        assertEquals(
                expectedValue,
                actualText,
                () -> "Text verification failed. Expected: '" + expectedValue +
                        "', Actual: '" + actualText + "'"
        );
    }

    // Assert text using WebElement
    public void verifyText(WebElement element, String expectedValue) {
        String actualText = element.getText().trim();
        assertEquals(
                expectedValue,
                actualText,
                () -> "Text verification failed. Expected: '" + expectedValue +
                        "', Actual: '" + actualText + "'"
        );
    }

    // ---------- INPUT VALUE ASSERTIONS ----------

    // Assert input value using By locator
    public void verifyInputValue(By by, String expectedValue) {
        WebElement element = driver.findElement(by);
        String actualText = element.getAttribute("value");
        assertEquals(
                expectedValue,
                actualText,
                () -> "Text verification failed. Expected: '" + expectedValue +
                        "', Actual: '" + actualText + "'"
        );
    }

    // Assert input value using WebElement
    public void verifyInputValue(WebElement element, String expectedValue) {
        String actualValue = element.getAttribute("value");
        assertEquals(
                expectedValue,
                actualValue,
                () -> "Input value verification failed. Expected: '" + expectedValue +
                        "', Actual: '" + actualValue + "'"
        );
    }
}

