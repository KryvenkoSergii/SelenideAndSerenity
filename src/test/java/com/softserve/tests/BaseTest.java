package com.softserve.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

public class BaseTest {

    @BeforeClass
    @Parameters(value = { "browser", "headless" })
    public void setUp(String browser, String headless) {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = false;
        //
        if (headless.equals("true")) {
            Configuration.headless = true;
        } else {
            Configuration.headless = false;
        }
        //
        switch (browser) {
        case "chrome":
            Configuration.browser = Browsers.CHROME;
            break;
        case "firefox":
            Configuration.browser = Browsers.FIREFOX;
            break;
        default:
            Configuration.browser = Browsers.CHROME;
            break;
        }
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().close();
        }
    }

    protected static WebDriver getDriver() {
        return WebDriverRunner.getSelenideDriver().getWebDriver();
    }
}
