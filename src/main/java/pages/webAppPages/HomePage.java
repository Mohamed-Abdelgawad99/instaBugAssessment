package pages.webAppPages;

import org.openqa.selenium.*;

public class HomePage {
    private WebDriver driver;

    private final By searchBar = By.xpath("//*[@id=\"search\"]");
    private final By homePageIdentifier = By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span");


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean validateUserinHomePage(){
        try {
            return driver.findElement(homePageIdentifier).isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean userFindsSearchBar() {
        try {
            return driver.findElement(searchBar).isDisplayed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void userEntersProductName(String productName) {
        try {
            WebElement searchBarElement = driver.findElement(searchBar);
            searchBarElement.sendKeys(productName);
            searchBarElement.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
