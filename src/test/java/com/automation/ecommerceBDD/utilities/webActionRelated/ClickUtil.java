package com.automation.ecommerceBDD.utilities.webActionRelated;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static com.automation.ecommerceBDD.utilities.reportRelated.Log4jManager.error;
import static com.automation.ecommerceBDD.utilities.reportRelated.Log4jManager.info;

/**
 * ClickUtil - Safe click utility with multiple fallback strategies
 * Handles common click issues: overlays, ads, iframes, element not clickable
 */
public class ClickUtil {

    private final WebDriver driver;
    private final WaitUtil waitUtil;
    private final JavascriptExecutor js;
    private final Actions actions;
    private final ScrollUtil scrollUtil;

    public ClickUtil(WebDriver driver, WaitUtil waitUtil, ScrollUtil scrollUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        this.scrollUtil = scrollUtil;
    }

    // =======================
    // BASIC SAFE CLICK METHODS
    // =======================

    /**
     * Safe click with automatic ad removal and retry logic
     */
    public void safeClick(WebElement element) {
        safeClick(element, 2); // Default 2 retries
    }

    /**
     * Safe click with custom retry count
     */
    public void safeClick(WebElement element, int maxRetries) {
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                if (attempt > 0) {
                    info("Retry attempt " + (attempt + 1) + " for clicking element");
                }

                performSafeClick(element);
                return; // Success, exit method

            } catch (Exception e) {
                if (attempt == maxRetries - 1) {
                    error("All " + maxRetries + " click attempts failed");
                    throw new RuntimeException("Click failed after " + maxRetries + " attempts", e);
                }
                waitUtil.sleepSeconds(1); // Wait before retry
            }
        }
    }

    /**
     * Safe click by locator
     */
    public void safeClick(By locator) {
        WebElement element = waitUtil.waitForClickable(locator);
        safeClick(element);
    }

    // =======================
    // CLICK WITH SPECIFIC STRATEGIES
    // =======================

    /**
     * JavaScript click (bypasses overlays)
     */
    public void clickWithJS(WebElement element) {
        try {
            removeAdsIfPresent();
            scrollUtil.scrollElementToCenter(element);
            waitUtil.sleepSeconds(0.3);

            js.executeScript("arguments[0].click();", element);
            info("Clicked element using JavaScript");

        } catch (Exception e) {
            error("JavaScript click failed: " + e.getMessage());
            throw new RuntimeException("JavaScript click failed", e);
        }
    }

    /**
     * Click using Actions class
     */
    public void clickWithActions(WebElement element) {
        try {
            removeAdsIfPresent();
            scrollUtil.scrollElementToCenter(element);
            waitUtil.sleepSeconds(0.3);

            actions.moveToElement(element).click().perform();
            info("Clicked element using Actions");

        } catch (Exception e) {
            error("Actions click failed: " + e.getMessage());
            throw new RuntimeException("Actions click failed", e);
        }
    }

    /**
     * Click using coordinates (bypasses element intercept)
     */
    public void clickWithCoordinates(WebElement element) {
        try {
            removeAdsIfPresent();
            scrollUtil.scrollElementToCenter(element);
            waitUtil.sleepSeconds(0.3);

            // Get element center
            Point location = element.getLocation();
            Dimension size = element.getSize();
            int x = location.getX() + (size.getWidth() / 2);
            int y = location.getY() + (size.getHeight() / 2);

            // Click at coordinates
            actions.moveByOffset(x, y).click().perform();
            actions.moveByOffset(-x, -y).perform(); // Reset offset

            info("Clicked element using coordinates");

        } catch (Exception e) {
            error("Coordinate click failed: " + e.getMessage());
            throw new RuntimeException("Coordinate click failed", e);
        }
    }

    // =======================
    // AD-HANDLING METHODS
    // =======================

    /**
     * Remove common ad iframes and containers
     */
    public void removeAdsIfPresent() {
        try {
            String removeAdsScript =
                    "// Remove Google Ad iframes" +
                            "var frames = document.querySelectorAll('iframe');" +
                            "frames.forEach(frame => {" +
                            "    var id = frame.id || '';" +
                            "    var src = frame.src || '';" +
                            "    if (id.includes('aswift') || id.includes('google_ads') || " +
                            "        src.includes('doubleclick') || src.includes('googleadservices')) {" +
                            "        frame.remove();" +
                            "    }" +
                            "});" +
                            "" +
                            "// Hide ad containers" +
                            "var adContainers = document.querySelectorAll(" +
                            "    '[id*=\"ad\"], [class*=\"ad\"], [id*=\"Ad\"], [class*=\"Ad\"], " +
                            "    '.adsbygoogle', '.ad-container', '.ad-unit''" +
                            ");" +
                            "adContainers.forEach(container => {" +
                            "    container.style.display = 'none';" +
                            "});";

            js.executeScript(removeAdsScript);

        } catch (Exception e) {
            // Silent fail - ads might not be present
        }
    }

    /**
     * Remove specific iframe by ID
     */
    public void removeIframeById(String iframeId) {
        try {
            String script = String.format(
                    "var frame = document.getElementById('%s');" +
                            "if (frame) { frame.remove(); }",
                    iframeId
            );
            js.executeScript(script);
            info("Removed iframe: " + iframeId);

        } catch (Exception e) {
            // Ignore
        }
    }

    // =======================
    // ELEMENT STATE CHECKERS
    // =======================

    /**
     * Check if element is clickable (visible, enabled, not obstructed)
     */
    public boolean isElementClickable(WebElement element) {
        try {
            return element.isDisplayed() &&
                    element.isEnabled() &&
                    isElementInViewport(element);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if element is in viewport
     */
    private boolean isElementInViewport(WebElement element) {
        try {
            String script =
                    "var elem = arguments[0], box = elem.getBoundingClientRect();" +
                            "return box.top >= 0 && box.left >= 0 && " +
                            "       box.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                            "       box.right <= (window.innerWidth || document.documentElement.clientWidth);";

            return (Boolean) js.executeScript(script, element);
        } catch (Exception e) {
            return false;
        }
    }

    // =======================
    // PRIVATE HELPER METHODS
    // =======================

    /**
     * Main click logic with multiple strategies
     */
    private void performSafeClick(WebElement element) {
        // Strategy 1: Remove ads
        removeAdsIfPresent();

        // Strategy 2: Ensure element is in viewport
        if (!isElementInViewport(element)) {
            scrollUtil.scrollElementToCenter(element);
            waitUtil.sleepSeconds(0.5);
        }

        // Strategy 3: Try normal click first
        try {
            waitUtil.waitForClickable(element).click();
            info("Clicked element using normal click");
            return;

        } catch (ElementClickInterceptedException e) {
            // Strategy 4: If intercepted, use JavaScript
            info("Click intercepted, trying JavaScript click");
            clickWithJS(element);

        } catch (StaleElementReferenceException e) {
            // Strategy 5: If stale, refresh element and retry
            throw e; // Let retry logic handle it

        } catch (Exception e) {
            // Strategy 6: Try Actions as fallback
            info("Normal click failed, trying Actions");
            clickWithActions(element);
        }
    }

    // =======================
    // SPECIALIZED CLICK METHODS
    // =======================

    /**
     * Click checkbox with verification
     */
    public void clickCheckbox(WebElement checkbox, String checkboxName) {
        try {
            safeClick(checkbox);

            // Verify checkbox state
            waitUtil.sleepSeconds(0.3);
            if (!checkbox.isSelected()) {
                // If not selected, try JavaScript to set checked property
                js.executeScript("arguments[0].checked = true;", checkbox);
                js.executeScript("arguments[0].dispatchEvent(new Event('change'));", checkbox);
            }

            info("Successfully clicked checkbox: " + checkboxName);

        } catch (Exception e) {
            error("Failed to click checkbox '" + checkboxName + "': " + e.getMessage());
            throw new RuntimeException("Checkbox click failed", e);
        }
    }

    /**
     * Click radio button
     */
    public void clickRadioButton(WebElement radioButton, String optionName) {
        try {
            safeClick(radioButton);
            info("Selected radio option: " + optionName);

        } catch (Exception e) {
            error("Failed to select radio option '" + optionName + "': " + e.getMessage());
            throw new RuntimeException("Radio button click failed", e);
        }
    }

    /**
     * Double click element
     */
    public void doubleClick(WebElement element) {
        try {
            removeAdsIfPresent();
            scrollUtil.scrollElementToCenter(element);

            actions.doubleClick(element).perform();
            info("Double-clicked element");

        } catch (Exception e) {
            error("Double click failed: " + e.getMessage());
            throw new RuntimeException("Double click failed", e);
        }
    }

    /**
     * Right click (context click) element
     */
    public void rightClick(WebElement element) {
        try {
            removeAdsIfPresent();
            scrollUtil.scrollElementToCenter(element);

            actions.contextClick(element).perform();
            info("Right-clicked element");

        } catch (Exception e) {
            error("Right click failed: " + e.getMessage());
            throw new RuntimeException("Right click failed", e);
        }
    }
}