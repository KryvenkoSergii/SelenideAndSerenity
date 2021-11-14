package com.softserve.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class CabinetLoginPage extends BasePage {

    public SelenideElement loginField = $("input#loginform-login");
    public SelenideElement passwordField = $("input#loginform-password");
    public SelenideElement signInButton = $("button.auth-submit");
    public SelenideElement loginButton = $("button.btn-enter");

    public CabinetLoginPage() {
        super(readProjectProperties.getCabinetPlanetaKinoUrl());
        logger.info("open Cabinet Login Page");
        wait.awaitFor(() -> driver.getCurrentUrl().equalsIgnoreCase("https://cabinet.planetakino.ua/auth"));
    }

    public void login() {
        logger.info("login to cabinet");
        loginButton.click();
        loginField.setValue(System.getenv("USER_EMAIL"));
        passwordField.val(System.getenv("PLANETA_KINO_PASSWORD")).pressEnter();
        passwordField.submit();
        wait.awaitFor(() -> driver.getCurrentUrl().equalsIgnoreCase("https://cabinet.planetakino.ua/")); // Waiting with awaitillity
    }

}
