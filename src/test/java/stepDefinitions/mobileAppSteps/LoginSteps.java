package stepDefinitions.mobileAppSteps;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.mobileAppPages.HomePage;
import pages.mobileAppPages.LoginPage;
import utils.MobileAppDriverManager;

public class LoginSteps {

    private final LoginPage loginPage;
    private final HomePage homePage;

    private LoginSteps(){
        this.loginPage = new LoginPage(MobileAppDriverManager.getInstance().getDriver());
        this.homePage = new HomePage(MobileAppDriverManager.getInstance().getDriver());
    }



    @Then("User is redirected to {string} page")
    public void userIsRedirectedTo(String pageName) {
        switch (pageName.toLowerCase().strip()){
            case "login":
                WebElement loginPageIdentifier = loginPage.getLoginPageIdentifierElement();
                Assert.assertTrue(loginPageIdentifier.isDisplayed());
                break;
            case "home":
                WebElement homePageIdentifier = homePage.getHomePageIdentifierElement();
                Assert.assertTrue(homePageIdentifier.isDisplayed());
                break;
        }
    }

    @When("User enters {string} as username")
    public void userEntersAsUsername(String userName) {
        loginPage.enterUsername(userName);
    }

    @And("User enter {string} as password")
    public void userEnterAsPassword(String userPassword) {
        loginPage.enterPassword(userPassword);
    }

    @And("User clicks on {string} button")
    public void userClicksOnButton(String btnName) {
        switch (btnName.toLowerCase()){
            case "login":
                loginPage.clickOnLogInBTN();
                break;
            case "forget password":
                break;
        }
    }
}
