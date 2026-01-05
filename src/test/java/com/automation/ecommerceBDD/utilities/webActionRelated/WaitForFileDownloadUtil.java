package com.automation.ecommerceBDD.utilities.webActionRelated;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class WaitForFileDownloadUtil {

    private WebDriver driver;

    public WaitForFileDownloadUtil(WebDriver inputDriver) {
        this.driver = inputDriver;
    }

    public void waitForFileDownload(int totalTimeoutInSecond, WebElement expectedFileName, WebElement clickDownloadLink) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(totalTimeoutInSecond))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        clickDownloadLink = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return expectedFileName;
            }
        });

        clickDownloadLink.click();
    }
}
