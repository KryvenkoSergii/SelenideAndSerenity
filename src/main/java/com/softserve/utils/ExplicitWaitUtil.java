package com.softserve.utils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.awaitility.core.ConditionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.awaitility.Awaitility.await;

public class ExplicitWaitUtil {
    
    private static final int PAGE_LOAD_TIMEOUT = 60;
    private static final int ELEMENT_PRESENT_TIMEOUT = 30;
    private static final int ELEMENT_CONDITION_TIMEOUT = 45;
    private static final int POLLING_INTERVAL = 2;
    private static final int SCRIPT_TIMEOUT = 90;
    private static final int CONDITION_MET_TIMEOUT = 30;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ReadProjectProperties readProjectProperties = new ReadProjectProperties();
    
    private WebDriver driver;
    private WebDriverWait wait;
    private FluentWait fluentWait;
    private JavascriptExecutor jsExecutor;
    private ConditionFactory await;

    public ExplicitWaitUtil(WebDriver driver) {
        this.driver = driver;
//        this.wait = new WebDriverWait(driver, readProjectProperties.geExplicitWaitDelay());
        this.wait = new WebDriverWait(driver, 15);
    }
    
 // Implicit wait - all elements
    public void setImplicitWait(int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public void setPageLoad(int timeout) {
        driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
    }

    // Explicit wait - specific elements
    public void waitFor(ExpectedCondition condition) {
        setImplicitWait(0);
        wait.until(condition);
        setImplicitWait(ELEMENT_PRESENT_TIMEOUT);
    }

    // Fluent wait
    public void setFluentWait() {
        fluentWait = wait
                .withTimeout(Duration.of(ELEMENT_CONDITION_TIMEOUT, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(POLLING_INTERVAL, ChronoUnit.SECONDS))
                .ignoring(WebDriverException.class);
    }

    // Explicit fluent wait - specific elements
    public void waitForFluently(ExpectedCondition condition) {
        setImplicitWait(0);
        setFluentWait();
        fluentWait.until(condition);
        setImplicitWait(ELEMENT_PRESENT_TIMEOUT);
    }

    public void setJsExecutor() {
        jsExecutor = (JavascriptExecutor) driver;
    }

    // Script wait
    public void setScriptTimeout(int timeout) {
        driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
    }

    public void setAwait() {
        await = await().atMost(CONDITION_MET_TIMEOUT, TimeUnit.SECONDS)
                .pollInterval(POLLING_INTERVAL, TimeUnit.SECONDS);
    }

    public void awaitFor(Callable<Boolean> condition) {
        await.until(condition);
    }
    
    public void elementToBeClickable(WebElement webElement) {
        logger.trace("wait until an element is clickable");
        setImplicitWait(0);
        wait.until(ExpectedConditions
                .elementToBeClickable(webElement));
        setImplicitWait(5);
    }
    
    public void visibilityOfWebElement(WebElement webElement) {
        logger.trace("wait until a webElement is visible");
        setImplicitWait(0);
        wait.until(ExpectedConditions
                .visibilityOf(webElement));
        setImplicitWait(5);
    }
    
    public void invisibilityOfWebElement(WebElement webElement) {
        logger.trace("wait until a webElement is visible");
        setImplicitWait(0);
        wait.until(ExpectedConditions
                .invisibilityOf(webElement));
        setImplicitWait(5);
    }
    
    public void presenceOfElementLocated(By locator) {
        logger.trace("wait until an element is present");
        setImplicitWait(0);
        wait.until(ExpectedConditions
                .presenceOfElementLocated(locator));
        setImplicitWait(5);
    }
    
    public void absenceOfElementLocated(By locator) {
        logger.trace("wait until an element is absence");
        setImplicitWait(0);
        wait.until(ExpectedConditions.not(ExpectedConditions
                .presenceOfElementLocated(locator)));
        setImplicitWait(5);
    }
    
    public void elementToBeSelected(WebElement webElement) {
        logger.trace("wait until an element is visible");
        setImplicitWait(0);
        wait.until(ExpectedConditions
                .elementToBeSelected(webElement));
        setImplicitWait(5);
    }
    
    public void elementHasStyleDisplayNone(String selectorXPath) {
        logger.trace("wait until an element has CSS Style");
        setImplicitWait(0);
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(selectorXPath + "[contains(@style, 'display: none')]")));
        setImplicitWait(5);
    }
    
    public void elementIsStalenessOf(WebElement webElement) {
        logger.trace("wait until an element is no longer attached to the DOM");
        setImplicitWait(0);
        wait.until(ExpectedConditions
                .stalenessOf(webElement));
        setImplicitWait(5);
    }
    
    public void waitForPageLoadComplete(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForAjaxToComplete(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return window.jQuery != undefined && jQuery.active == 0;"));
    }
    
    public void waitForAjaxToCompletePdp(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return window.jQuery != undefined && jQuery.active <=2;"));
    }
}
