package pages.webAppPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;
    private final By searchPageHeader = By.tagName("h1");
    private final By numberOfSearchResults = By.cssSelector("li.item.product.product-item");


    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public boolean loadingSearchResultsPage() {
        String header = driver.findElement(searchPageHeader).getText();
        return header.toLowerCase().contains("search results");
    }

    public int validateNumberOfSearchResults() {
        try {
            List<WebElement> searchItems = driver.findElements(numberOfSearchResults);
            return searchItems.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
