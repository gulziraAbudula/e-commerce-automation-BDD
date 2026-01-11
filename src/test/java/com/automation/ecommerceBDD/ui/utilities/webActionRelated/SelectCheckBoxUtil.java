package com.automation.ecommerceBDD.ui.utilities.webActionRelated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SelectCheckBoxUtil {

    private WebDriver driver;

    public SelectCheckBoxUtil(WebDriver inputDriver) {
        this.driver = inputDriver;
    }

    public void selectCheckBox(WebElement checkBoxElem) {

        try {
            checkBoxElem.click();
            // Check whether the Check box is toggled on
            if (checkBoxElem.isSelected()) {
                System.out.println("Checkbox is Toggled On");

            } else {
                System.out.println("Checkbox is Toggled Off");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
