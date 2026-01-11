package com.automation.ecommerceBDD.ui.utilities.webActionRelated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DragAndSortUtil {

    private WebDriver driver;

    public DragAndSortUtil(WebDriver inputDriver) {
        this.driver = inputDriver;
    }

    public void sort(List<WebElement> sortElems, int dragElemIndex, int destinationElemIndex) {
        try {
            Actions builder = new Actions(driver);
            WebElement first = sortElems.get(dragElemIndex);
            WebElement end = sortElems.get(destinationElemIndex);
            builder.clickAndHold(first).moveToElement(end).release();
            Action selectItems = builder.build();
            selectItems.perform();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
