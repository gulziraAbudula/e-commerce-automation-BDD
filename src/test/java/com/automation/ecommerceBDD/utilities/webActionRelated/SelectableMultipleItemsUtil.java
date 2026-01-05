package com.automation.ecommerceBDD.utilities.webActionRelated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SelectableMultipleItemsUtil {

    private WebDriver driver;

    public SelectableMultipleItemsUtil(WebDriver inputDriver) {
        this.driver = inputDriver;
    }

    public void select(List<WebElement> selectableItems, int startingIndex, int endingIndex) {
        try {
            Actions toSelect = new Actions(driver);
            toSelect.clickAndHold(selectableItems.get(startingIndex))
                    .clickAndHold(selectableItems.get(endingIndex))
                    .click();

            Action selectItems = toSelect.build();
            selectItems.perform();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
