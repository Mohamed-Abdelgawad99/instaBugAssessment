package stepDefinitions.webAppSteps;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import utils.WebDriverFactory;

public class CommonSteps {

    public static WebDriver driver;

    @Given("user opens the E-commerce web application")
    public void openWebApplication(){
        driver = WebDriverFactory.getDriver();
    }


}
