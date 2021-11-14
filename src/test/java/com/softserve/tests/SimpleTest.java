package com.softserve.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class SimpleTest {
    
    @BeforeClass
    public void setUp() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = false;
        Configuration.headless = false;
        Configuration.browser = Browsers.CHROME;

    }

    @AfterClass
    public void tearDown() {
        currentDriver().close();
    }
    
    @Test
    public void simpleSelenideTest() {
        Selenide.open("https://www.google.com/");
        System.out.println(currentDriver().getCurrentUrl());
    }
    
    public static WebDriver currentDriver() {
        return WebDriverRunner.getSelenideDriver().getWebDriver();
    }
}
