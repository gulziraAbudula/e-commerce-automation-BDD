package com.automation.ecommerceBDD.utilities.webActionRelated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AutocompleteUtil {

    private WebDriver driver;

    public AutocompleteUtil(WebDriver inputDriver) {

        this.driver = inputDriver;
    }

    public void autoComplete(List<WebElement> listOfInputItems, WebElement inputBox, String inputValue, int pickedItemIndex) {
        try {
            inputBox.sendKeys(inputValue);
            listOfInputItems.get(pickedItemIndex).click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
