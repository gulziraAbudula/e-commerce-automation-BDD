package com.automation.ecommerceBDD.ui.utilities.webActionRelated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RadioButtonClickingUtil {

    private WebDriver driver;

    public RadioButtonClickingUtil(WebDriver driver) {
        this.driver = driver;
    }

    public enum RadioSelectType {
        LABEL_TEXT,
        FOR_ATTRIBUTE,
        VALUE_ATTRIBUTE,
        ID_ATTRIBUTE
    }

    public void clickRadioButton(
            List<WebElement> elements,
            String expectedValue,
            RadioSelectType selectType) {

        for (WebElement element : elements) {
            switch (selectType) {

                case LABEL_TEXT:
                    if (element.getText().equalsIgnoreCase(expectedValue)) {
                        element.click();
                        return;
                    }
                    break;

                case FOR_ATTRIBUTE:
                    if (element.getText().equalsIgnoreCase(expectedValue)) {
                        driver.findElement(
                                By.id(element.getAttribute("for"))
                        ).click();
                        return;
                    }
                    break;

                case VALUE_ATTRIBUTE:
                    if (expectedValue.equalsIgnoreCase(
                            element.getAttribute("value"))) {
                        element.click();
                        return;
                    }
                    break;

                case ID_ATTRIBUTE:
                    if (expectedValue.equalsIgnoreCase(
                            element.getAttribute("id"))) {
                        element.click();
                        return;
                    }
                    break;
            }
        }
        throw new RuntimeException("Radio button not found: " + expectedValue);
    }
}
