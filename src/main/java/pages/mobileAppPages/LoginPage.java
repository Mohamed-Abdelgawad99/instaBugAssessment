package pages.mobileAppPages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By loginPageIdentifierElement = By.id("com.example:id/login_title");
    private final By loginBtnElement = By.xpath("//android.widget.Button[@text='Login']");
    private final By passwordTextBox = By.xpath("//android.widget.EditText[@text='Enter your password']");
    private final By usernameTextBox = By.xpath("//android.widget.EditText[preceding-sibling::android.widget.EditText[@text='Enter your password']]");

    public void clickOnLogInBTN() {
        WebElement loginBtn = driver.findElement(loginBtnElement);
        loginBtn.click();
    }

    public WebElement getLoginPageIdentifierElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageIdentifierElement));
    }

    public void enterUsername(String userName) {
        WebElement usernameField = driver.findElement(usernameTextBox);
        usernameField.sendKeys(userName);
    }

    public void enterPassword(String userPassword) {
        WebElement passwordField = driver.findElement(passwordTextBox);
        passwordField.sendKeys(userPassword);
    }
}
