package com.automation.ecommerceBDD.utilities.webActionRelated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class IframeHandlingUtil {

    private WebDriver driver;

    public IframeHandlingUtil(WebDriver InputDriver) {
        this.driver = InputDriver;

    }

    public void iframeHandle(int num) {
        try {
            driver.switchTo().frame(num);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iframeHandle(By by) {
        try {
            WebElement frame = driver.findElement(by);
            driver.switchTo().frame(frame);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
