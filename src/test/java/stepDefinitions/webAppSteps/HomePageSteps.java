package stepDefinitions.webAppSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.webAppPages.HomePage;
import utils.WebDriverFactory;

public class HomePageSteps {

    private final WebDriver driver = WebDriverFactory.getDriver();
    private final HomePage homePage = new HomePage(driver);


    @Then("user is redirected to homepage")
    public void userIsRedirectedToHomepage() {
        Assert.assertTrue(homePage.validateUserinHomePage());
    }


    @When("user search for {string} product")
    public void userSearchForProduct(String productName) {
        Assert.assertTrue(homePage.userFindsSearchBar());
        homePage.userEntersProductName(productName);
    }
}
