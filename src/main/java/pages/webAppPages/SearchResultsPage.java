package pages.webAppPages;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    private final WebDriver driver;
    private final By searchPageHeader = By.tagName("h1");
    private final By numberOfSearchResults = By.cssSelector("li.item.product.product-item");


    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public boolean loadingSearchResultsPage(Scenario scenario) {
        try {
            scenario.log("Checking search results page appeared");
            String header = driver.findElement(searchPageHeader).getText();
            return header.toLowerCase().contains("search results");
        } catch (TimeoutException e) {
            scenario.log("❌ Timed out waiting for search results header.");
            return false;
        } catch (Exception e) {
            scenario.log("❌ Unexpected error checking search results page: " + e.getMessage());
            return false;

        }
    }

    public int validateNumberOfSearchResults(Scenario scenario) {
        try {
            scenario.log("finding out number of products in the search results");
            List<WebElement> searchItems = driver.findElements(numberOfSearchResults);
            return searchItems.size();
        } catch (TimeoutException e) {
            scenario.log("❌ Timed out waiting for search results to appear.");
            return 0;
        } catch (Exception e) {
            scenario.log("❌ Error retrieving search results: " + e.getMessage());
            return 0;
        }
    }
}
