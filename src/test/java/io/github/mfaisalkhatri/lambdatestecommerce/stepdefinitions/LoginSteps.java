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

public class LoginSteps {

    private WebDriver driver;

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

    @And ("click on the login button")
    public void clickOnTheLoginButton () {
        WebElement loginButton = driver.findElement (By.cssSelector ("input[value=Login]"));
        loginButton.click ();
    }

    @Given ("I am on the login page")
    public void iAmOnTheLoginPage () {
        driver.get ("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        String headerText = driver.findElement (By.cssSelector ("#content div:nth-child(2) h2"))
            .getText ();
        assertEquals (headerText, "Returning Customer");
    }

    @When ("I enter a valid username and password")
    public void iEnterAValidUsernameAndPassword () {
        WebElement emailIdField = driver.findElement (By.id ("input-email"));
        emailIdField.sendKeys ("david.thomson@gmail.com");
        WebElement passwordField = driver.findElement (By.id ("input-password"));
        passwordField.sendKeys ("Secret@123");
    }

    @Then ("I should be taken to My Account page")
    public void iShouldBeTakenToMyAccountPage () {
        String myAccountHeaderText = driver.findElement (By.cssSelector ("#content h2"))
            .getText ();
        assertEquals (myAccountHeaderText, "My Account");
    }

    @And ("the logout button should be displayed")
    public void theLogoutButtonShouldBeDisplayed () {
        WebElement logoutLink = driver.findElement (By.linkText ("Logout"));
        assertTrue (logoutLink.isDisplayed ());
    }

    @After
    public void tearDown () {
        driver.quit ();
    }
}