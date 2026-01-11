package com.automation.ecommerceBDD.ui.utilities.webActionRelated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickSpinnerUtil {

    private WebDriver driver;

    public ClickSpinnerUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSpinner(int inputNumTimes, WebElement upOrDownButton) {

        try {
            for (int i = 0; i < inputNumTimes; i++) {
                upOrDownButton.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
