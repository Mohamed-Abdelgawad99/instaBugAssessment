package stepDefinitions.mobileAppSteps;

import io.cucumber.java.en.Given;
import utils.MobileAppDriverManager;

public class CommonSteps {

    private final MobileAppDriverManager appDriverInstance = MobileAppDriverManager.getInstance();

    @Given("user opens mobile application")
    public void launchMobileApp(){
        System.out.println("diver initialization......");
        appDriverInstance.launchApp();
    }
}
