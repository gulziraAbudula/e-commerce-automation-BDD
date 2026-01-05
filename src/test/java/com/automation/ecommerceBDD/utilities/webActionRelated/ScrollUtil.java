package com.automation.ecommerceBDD.utilities.webActionRelated;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.automation.ecommerceBDD.utilities.reportRelated.Log4jManager.error;
import static com.automation.ecommerceBDD.utilities.reportRelated.Log4jManager.info;

/**
 * Enhanced Scroll Utility with multiple scrolling strategies, smooth scrolling,
 * and better error handling. Replaces ScrollDownUpUtil with more features.
 */
public class ScrollUtil {

    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final Actions actions;

    public ScrollUtil(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    // =======================
    // BASIC SCROLL METHODS
    // =======================

    /**
     * Scroll window by specific pixel values
     * @param x Horizontal scroll (positive = right, negative = left)
     * @param y Vertical scroll (positive = down, negative = up)
     */
    public void scrollWindow(int x, int y) {
        try {
            String script = String.format("window.scrollBy(%d, %d)", x, y);
            js.executeScript(script);
            info("Scrolled window by X:" + x + ", Y:" + y);
        } catch (Exception e) {
            error("Failed to scroll window: " + e.getMessage());
            throw new RuntimeException("Scroll window failed", e);
        }
    }

    /**
     * Scroll to make element visible (basic)
     */
    public void scrollToElement(WebElement element) {
        scrollToElement(element, "center", "smooth");
    }

    /**
     * Scroll to element with customization options
     * @param element WebElement to scroll to
     * @param blockVertical "start", "center", "end", or "nearest"
     * @param behavior "auto", "smooth"
     */
    public void scrollToElement(WebElement element, String blockVertical, String behavior) {
        try {
            String script = String.format(
                    "arguments[0].scrollIntoView({block: '%s', inline: 'nearest', behavior: '%s'});",
                    blockVertical, behavior
            );
            js.executeScript(script, element);
            info("Scrolled to element with block:" + blockVertical + ", behavior:" + behavior);
        } catch (Exception e) {
            error("Failed to scroll to element: " + e.getMessage());
            throw new RuntimeException("Scroll to element failed", e);
        }
    }

    // =======================
    // ADVANCED SCROLL METHODS
    // =======================

    /**
     * Scroll to element with pixel offset from top
     * Useful when elements are under fixed headers
     */
    public void scrollToElementWithOffset(WebElement element, int offsetFromTop) {
        try {
            String script = "window.scrollTo({"
                    + "top: arguments[0].getBoundingClientRect().top + window.pageYOffset - " + offsetFromTop + ","
                    + "behavior: 'smooth'"
                    + "});";
            js.executeScript(script, element);
            info("Scrolled to element with offset: " + offsetFromTop + "px from top");
        } catch (Exception e) {
            error("Failed to scroll to element with offset: " + e.getMessage());
            throw new RuntimeException("Scroll to element with offset failed", e);
        }
    }

    /**
     * Scroll to specific coordinates on page
     */
    public void scrollToPosition(int x, int y) {
        try {
            String script = String.format("window.scrollTo(%d, %d);", x, y);
            js.executeScript(script);
            info("Scrolled to position X:" + x + ", Y:" + y);
        } catch (Exception e) {
            error("Failed to scroll to position: " + e.getMessage());
            throw new RuntimeException("Scroll to position failed", e);
        }
    }

    /**
     * Scroll to bottom of page
     */
    public void scrollToBottom() {
        try {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            info("Scrolled to bottom of page");
        } catch (Exception e) {
            error("Failed to scroll to bottom: " + e.getMessage());
            throw new RuntimeException("Scroll to bottom failed", e);
        }
    }

    /**
     * Scroll to top of page
     */
    public void scrollToTop() {
        try {
            js.executeScript("window.scrollTo(0, 0);");
            info("Scrolled to top of page");
        } catch (Exception e) {
            error("Failed to scroll to top: " + e.getMessage());
            throw new RuntimeException("Scroll to top failed", e);
        }
    }

    /**
     * Scroll horizontally to element (for horizontal scroll containers)
     */
    public void scrollHorizontallyToElement(WebElement container, WebElement element) {
        try {
            String script = "arguments[0].scrollLeft = arguments[1].offsetLeft;";
            js.executeScript(script, container, element);
            info("Horizontally scrolled to element");
        } catch (Exception e) {
            error("Failed to scroll horizontally to element: " + e.getMessage());
            throw new RuntimeException("Horizontal scroll to element failed", e);
        }
    }

    // =======================
    // UTILITY METHODS
    // =======================

    /**
     * Scroll element into center of viewport (best for clicking/interacting)
     */
    public void scrollElementToCenter(WebElement element) {
        scrollToElement(element, "center", "smooth");
    }

    /**
     * Scroll element to top of viewport (good for reading content)
     */
    public void scrollElementToTop(WebElement element) {
        scrollToElement(element, "start", "smooth");
    }

    /**
     * Smooth scroll by pixels (better alternative to scrollWindow)
     */
    public void smoothScrollBy(int x, int y) {
        try {
            String script = String.format(
                    "window.scrollBy({top: %d, left: %d, behavior: 'smooth'});",
                    y, x
            );
            js.executeScript(script);
            info("Smooth scrolled by X:" + x + ", Y:" + y);
        } catch (Exception e) {
            error("Failed to smooth scroll: " + e.getMessage());
            throw new RuntimeException("Smooth scroll failed", e);
        }
    }

    /**
     * Check if element is in viewport
     */
    public boolean isElementInViewport(WebElement element) {
        try {
            String script = "var elem = arguments[0];"
                    + "var bounding = elem.getBoundingClientRect();"
                    + "return ("
                    + "bounding.top >= 0 &&"
                    + "bounding.left >= 0 &&"
                    + "bounding.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&"
                    + "bounding.right <= (window.innerWidth || document.documentElement.clientWidth)"
                    + ");";
            return (Boolean) js.executeScript(script, element);
        } catch (Exception e) {
            error("Failed to check if element is in viewport: " + e.getMessage());
            return false;
        }
    }

    /**
     * Scroll using Actions class (alternative method)
     */
    public void scrollUsingActions(int xOffset, int yOffset) {
        try {
            actions.scrollByAmount(xOffset, yOffset).perform();
            info("Scrolled using Actions by X:" + xOffset + ", Y:" + yOffset);
        } catch (Exception e) {
            error("Failed to scroll using Actions: " + e.getMessage());
            throw new RuntimeException("Scroll using Actions failed", e);
        }
    }

    /**
     * Scroll to element and wait for it to be clickable (most useful for interactions)
     */
    public void scrollToElementAndMakeClickable(WebElement element, WaitUtil waitUtil) {
        try {
            // First scroll to element
            scrollElementToCenter(element);

            // Wait a moment for scroll to complete
            Thread.sleep(500);

            // Ensure element is clickable
            waitUtil.waitForClickable(element);

            info("Scrolled to element and made it clickable");
        } catch (Exception e) {
            error("Failed to scroll to element and make clickable: " + e.getMessage());
            throw new RuntimeException("Scroll to element and make clickable failed", e);
        }
    }

    // =======================
    // CONVENIENCE METHODS
    // =======================

    /**
     * Scroll down by one page
     */
    public void scrollDownOnePage() {
        scrollWindow(0, 800); // Typical viewport height
    }

    /**
     * Scroll up by one page
     */
    public void scrollUpOnePage() {
        scrollWindow(0, -800);
    }

    /**
     * Scroll right by one screen
     */
    public void scrollRightOneScreen() {
        scrollWindow(1000, 0);
    }

    /**
     * Scroll left by one screen
     */
    public void scrollLeftOneScreen() {
        scrollWindow(-1000, 0);
    }

    /**
     * Get current scroll position
     * @return Array [scrollX, scrollY]
     */
    public long[] getScrollPosition() {
        try {
            long scrollX = (long) js.executeScript("return window.pageXOffset;");
            long scrollY = (long) js.executeScript("return window.pageYOffset;");
            return new long[]{scrollX, scrollY};
        } catch (Exception e) {
            error("Failed to get scroll position: " + e.getMessage());
            return new long[]{0, 0};
        }
    }
}