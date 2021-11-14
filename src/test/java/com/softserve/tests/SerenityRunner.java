package com.softserve.tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/main/resources/features", 
        glue = "net.serenity_bdd.samples")
public class SerenityRunner {
}
