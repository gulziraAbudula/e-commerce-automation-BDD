package com.automation.ecommerceBDD.ui.utilities.webActionRelated;

import com.automation.ecommerceBDD.ui.stepdefinitions.Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserUtil extends Base {

    public static WebDriver startBrowser(String browserName) {
        try {
            if (browserName.equalsIgnoreCase("chrome"))
                return startChromeBrowser();
            else if (browserName.equalsIgnoreCase("firefox"))
                return startFirefoxBrowser();
            else
                throw new Exception("ERROR!! You choose '" + browserName + "'. Currently supporting browsers are 'Chrome' and 'Firefox'");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static WebDriver startBrowserInPrivateMode(String browserName) {
        try {
            if (browserName.equalsIgnoreCase("chrome"))
                return startChromeBrowserInPrivateMode();
            else if (browserName.equalsIgnoreCase("firefox"))
                return startFirefoxBrowserInPrivateMode();
            else
                throw new Exception("ERROR!! You choose '" + browserName + "'. Currently supporting browsers are 'Chrome' and 'Firefox'");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static WebDriver startDockerContainerBrowser(String browserName) {
        try {
            if (browserName.equalsIgnoreCase("chrome"))
                return startDockerContainerChromeBrowser();
            else if (browserName.equalsIgnoreCase("firefox"))
                return startDockerContainerFirefoxBrowser();
            else
                throw new Exception("ERROR!! You choose '" + browserName + "'. Currently supporting browsers are 'Chrome' and 'Firefox'");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static WebDriver startChromeBrowser() {
        System.out.println("Initiating Chrome Driver");
        try {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // ONLY THE ESSENTIALS
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");

            // Block Google Ads domains
            options.addArguments("--host-resolver-rules=MAP *.doubleclick.net 127.0.0.1");

            driver = new ChromeDriver(options);
            configureDriver();

            // Simple ad removal
            removeGoogleAds(driver);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    private static WebDriver startFirefoxBrowser() {
        System.out.println("Initiating Firefox Driver");
        try {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            configureDriver();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    private static WebDriver startChromeBrowserInPrivateMode() {
        System.out.println("Initiating Chrome Driver in Incognito Mode");
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
            configureDriver();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    private static WebDriver startFirefoxBrowserInPrivateMode() {
        System.out.println("Initiating Firefox Driver in Private Mode");
        try {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-private");
            driver = new FirefoxDriver(options);
            configureDriver();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    private static WebDriver startDockerContainerChromeBrowser() {
        System.out.println("Initiating Chrome Driver in Docker Container");
        try {
            String huburl = "http://localhost:4444";

            ChromeOptions options = new ChromeOptions();

            driver = new RemoteWebDriver(new URL(huburl), options);
            configureDriver();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    private static WebDriver startDockerContainerFirefoxBrowser() {
        System.out.println("Initiating Firefox Driver in Docker Container");
        try {
            String huburl = "http://localhost:4444";

            FirefoxOptions options = new FirefoxOptions();

            driver = new RemoteWebDriver(new URL(huburl), options);
            configureDriver();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    private static void removeGoogleAds(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Remove specific problematic iframes
            js.executeScript(
                    "// Remove aswift iframes" +
                            "document.querySelectorAll('iframe[id^=\"aswift\"]').forEach(f => f.remove());" +
                            "" +
                            "// Remove doubleclick iframes" +
                            "document.querySelectorAll('iframe[src*=\"doubleclick\"]').forEach(f => f.remove());" +
                            "" +
                            "// Hide ad containers" +
                            "document.querySelectorAll('[id*=\"google\"], .adsbygoogle').forEach(el => {" +
                            "    el.style.display = 'none';" +
                            "});"
            );

        } catch (Exception e) {
            // Ignore errors
        }
    }

    private static void configureDriver() {
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}