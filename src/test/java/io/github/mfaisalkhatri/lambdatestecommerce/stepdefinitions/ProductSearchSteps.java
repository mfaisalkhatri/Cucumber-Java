package io.github.mfaisalkhatri.lambdatestecommerce.stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductSearchSteps {

    private WebDriver driver;
    private By        searchfield = By.cssSelector ("#main-header input[name='search']");

    @Before
    public void setup () {
        driver = new ChromeDriver ();
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
        WebElement searchField = driver.findElement (searchfield);
        assertTrue (searchField.isDisplayed ());
    }

    @When ("I type {string} into the search field")
    public void i_type_into_the_search_field (String productName) {
        WebElement searchField = driver.findElement (searchfield);
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
    }

    @After
    public void tearDown () {
        driver.quit ();
    }
}