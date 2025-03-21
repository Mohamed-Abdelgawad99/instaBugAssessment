package pages.mobileAppPages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By homePageIdentifierElement = By.id("com.example:id/home_page_title");


    public WebElement getHomePageIdentifierElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(homePageIdentifierElement));
    }
}
