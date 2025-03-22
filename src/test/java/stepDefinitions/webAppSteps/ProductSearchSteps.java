package stepDefinitions.webAppSteps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.webAppPages.SearchResultsPage;
import utils.WebDriverFactory;

public class ProductSearchSteps {
    private final WebDriver driver = WebDriverFactory.getDriver();
    private final SearchResultsPage searchResultsPage = new SearchResultsPage(driver);


    @Then("search results page loads")
    public void searchResultsPageLoads() {
        Assert.assertTrue(searchResultsPage.loadingSearchResultsPage());
    }

    @And("check search result page has at least {int} results")
    public void searchResultPageHasResults(int numberOfSearchResults) {
        int actualSearchResults = searchResultsPage.validateNumberOfSearchResults();
        boolean searchResultsComparison = actualSearchResults >= numberOfSearchResults;
        Assert.assertTrue("Found "+ actualSearchResults + " results and required at least " +
                        numberOfSearchResults, searchResultsComparison);

    }

}
