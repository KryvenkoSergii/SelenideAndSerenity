package com.softserve.pages;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.softserve.utils.ExplicitWaitUtil;
import com.softserve.utils.ReadProjectProperties;

public abstract class BasePage {

    protected WebDriver driver;
    protected ExplicitWaitUtil wait;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static ReadProjectProperties readProjectProperties = new ReadProjectProperties();
    //
    private SelenideElement showtimesMenuLink = $("ul.h-m__desktop>li:first-child>a");
    private SelenideElement moviesMenuLink = $("ul.h-m__desktop>li:nth-child(2)>a");

    public BasePage(String pageUrl) {
        open(pageUrl);
        this.driver = WebDriverRunner.getSelenideDriver().getWebDriver();
        this.wait = new ExplicitWaitUtil(driver);
        this.wait.setAwait();
    }

    protected void open(String pageUrl) {
//        String url = Trim.rtrim(readProjectProperties.getPlanetaKinoUrl(), "/") + "/" + Trim.ltrim(pageUrl, "/");
        Selenide.open(pageUrl);
    }

//    @Step(value = "get current URL address")
    public String getCurrentUrl() {
        String currentURL = driver.getCurrentUrl();
        logger.info("current URL = " + currentURL);
        return currentURL;
    }
    
    public ShowtimesPage openShowtimesPage() {
//        showtimesMenuLink.click();
        return new ShowtimesPage();
    }
    
    public MoviesPage openMoviesPage() {
//        moviesMenuLink.click();
        return new MoviesPage();
    }
    
    public FavoriteMoviesPage openFavoriteMoviesPage() {
        return new FavoriteMoviesPage();
    }
}