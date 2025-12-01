package io.github.mfaisalkhatri.lambdatestecommerce.stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductSearchSteps {

    private              RemoteWebDriver driver;
    private final        By              searchBox     = By.cssSelector ("#main-header input[name='search']");
    private static final String          GRID_URL      = "@hub.lambdatest.com/wd/hub";
    private static final String          LT_ACCESS_KEY = System.getenv ("LT_ACCESS_KEY");
    private static final String          LT_USERNAME   = System.getenv ("LT_USERNAME");
    private              String          status        = "failed";

    @Before
    public void setup () {
        try {
            this.driver = new RemoteWebDriver (new URL ("https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + GRID_URL),
                getChromeOptions ());
        } catch (final MalformedURLException e) {
            System.out.println ("Could not start the remote session on LambdaTest cloud grid");
            this.driver.manage ()
                .timeouts ()
                .implicitlyWait (Duration.ofSeconds (20));
        }


        driver.manage ()
            .window ()
            .maximize ();
        driver.manage ()
            .timeouts ()
            .implicitlyWait (Duration.ofSeconds (30));
    }

    @Given ("I am on the LambdaTest E-Commerce Playground home page")
    public void i_am_on_the_lambda_test_e_commerce_playground_home_page () {
        driver.get ("https://ecommerce-playground.lambdatest.io/");
        WebElement searchField = driver.findElement (searchBox);
        assertTrue (searchField.isDisplayed ());
    }

    @When ("I type {string} into the search field")
    public void i_type_into_the_search_field (String productName) {
        WebElement searchField = driver.findElement (searchBox);
        searchField.sendKeys (productName);
    }

    @And ("I click the search button")
    public void i_click_the_search_button () {
        WebElement searchButton = driver.findElement (By.cssSelector ("#search button[type='submit']"));
        searchButton.click ();
    }

    @Then ("the search results page should show products related to {string}")
    public void the_search_results_page_should_show_products_related_to (String productName) {
        String headerText = driver.findElement (By.cssSelector ("#product-search h1"))
            .getText ();
        assertEquals (headerText, "Search - " + productName);
        this.status = "passed";
    }

    @After
    public void tearDown () {

        this.driver.executeScript ("lambda-status=" + this.status);
        driver.quit ();
    }

    public ChromeOptions getChromeOptions () {
        final var browserOptions = new ChromeOptions ();
        browserOptions.setPlatformName ("Windows 11");
        browserOptions.setBrowserVersion ("latest");
        final HashMap<String, Object> ltOptions = new HashMap<String, Object> ();
        ltOptions.put ("project", "Cucumber Gherkin Demo");
        ltOptions.put ("build", "LambdaTest E-Commerce Playground Demo");
        ltOptions.put ("name", "Automation tests using Cucumber BDD");
        ltOptions.put ("w3c", true);
        ltOptions.put ("plugin", "java-testNG");
        browserOptions.setCapability ("LT:Options", ltOptions);
        return browserOptions;
    }
}