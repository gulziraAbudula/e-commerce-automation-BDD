package com.automation.ecommerceBDD.utilities.webActionRelated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class ResizableUtil {

    private WebDriver driver;

    public ResizableUtil(WebDriver inputDriver) {
        this.driver = inputDriver;
    }

    public void resize(By by, int offsetX, int offsetY){
        try {
            WebElement element = driver.findElement(by);
            Actions action = new Actions(driver);
            action.dragAndDropBy(element, 0, 0).build().perform();
            Thread.sleep(3000);
            action.clickAndHold(element).moveByOffset(offsetX, offsetY).release().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
