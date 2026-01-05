package com.automation.ecommerceBDD.utilities.webActionRelated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class WebElementUtil {

    private WebDriver driver;

    public WebElementUtil(WebDriver InputDriver) {
        this.driver = InputDriver;

    }

    public WebElement elemAsWebElem(By by) {

        WebElement elem = driver.findElement(by);
        return elem;

    }
    public void click(By by){

        try {
            WebElement elem = driver.findElement(by);
            elem.click();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void type(By by, String targetText){
        try {
            WebElement elem = driver.findElement(by);
            elem.clear();
            elem.sendKeys(targetText);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
