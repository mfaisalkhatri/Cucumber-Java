package io.github.mfaisalkhatri.lambdatestecommerce.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (
    features = "src/test/resources/features",
    glue = {"io.github.mfaisalkhatri.lambdatestecommerce.stepdefinitions"},
    plugin = {"pretty"}
)
public class TestRunner extends AbstractTestNGCucumberTests {}