package com.automation.ecommerceBDD.ui.utilities.webActionRelated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectDropDownUtil {

    private WebDriver driver;

    public SelectDropDownUtil(WebDriver driver) {
        this.driver = driver;
    }

    // =======================
    // NATIVE <select> METHODS
    // =======================

    public void selectByVisibleText(WebElement dropdown, String visibleText) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    public void selectByValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public void selectByIndex(WebElement dropdown, int index) {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    // =======================
    // CUSTOM DROPDOWN METHODS
    // =======================

    public void selectFromCustomDropdown(
            WebElement dropdownTrigger,
            List<WebElement> options,
            String expectedText) {

        dropdownTrigger.click();

        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(expectedText)) {
                option.click();
                return;
            }
        }

        throw new RuntimeException(
                "Dropdown option not found: " + expectedText);
    }

    public void selectFromCustomDropdownByIndex(
            WebElement dropdownTrigger,
            List<WebElement> options,
            int index) {

        dropdownTrigger.click();

        if (index >= 0 && index < options.size()) {
            options.get(index).click();
        } else {
            throw new RuntimeException(
                    "Invalid dropdown index: " + index);
        }
    }
}

