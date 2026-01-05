package com.automation.ecommerceBDD.stepdefinitions;

import com.automation.ecommerceBDD.utilities.dataRelated.XMLReaderUtil;
import com.automation.ecommerceBDD.utilities.webActionRelated.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class Base {

    public static WebDriver driver;

    protected static WaitUtil waitUtil;
    protected static AssertUtil assertUtil;
    protected static SelectDropDownUtil selectDropdownUtil;
    protected static XMLReaderUtil xmlReader;
    protected static ScrollUtil scrollUtil;
    protected static ClickUtil clickUtil;


    @Before
    public void setUp(Scenario scenario) {

        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Starting - " + scenario.getName());
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");

        //String browser = System.getProperty("browser");
        String browser = "chrome";
        driver = BrowserUtil.startBrowser(browser);
        //driver = BrowserUtil.startDockerContainerBrowser(browser);
        driver.get("https://automationexercise.com/");

        waitUtil = new WaitUtil(driver, 3);
        assertUtil = new AssertUtil(driver);
        selectDropdownUtil = new SelectDropDownUtil(driver);
        xmlReader = new XMLReaderUtil();
        scrollUtil = new ScrollUtil(driver);
        clickUtil = new ClickUtil(driver, waitUtil, scrollUtil);

    }

    @After
    public void tearDown(Scenario scenario) {

        if (driver != null) {
            driver.close();
            driver.quit();
        }

        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Ending - " + scenario.getName() + " Status - " + scenario.getStatus());
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------");

    }

    // Add getter methods for static utilities
    public static WaitUtil getWaitUtil() {
        return waitUtil;
    }

    public static AssertUtil getAssertUtil() {
        return assertUtil;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
