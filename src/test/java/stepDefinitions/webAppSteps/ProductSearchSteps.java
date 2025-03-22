package stepDefinitions.webAppSteps;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.webAppPages.SearchResultsPage;
import utils.WebDriverFactory;

public class ProductSearchSteps {
    private final WebDriver driver = WebDriverFactory.getDriver();
    private final SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
    private Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Then("search results page loads")
    public void searchResultsPageLoads() {
        scenario.log("Checking if search results page is loaded..");
        Assert.assertTrue(searchResultsPage.loadingSearchResultsPage());
        scenario.log("Search results page loaded successfully");
    }

    @And("check search result page has at least {int} results")
    public void searchResultPageHasResults(int numberOfSearchResults) {
        scenario.log("Validating number of search results");
        int actualSearchResults = searchResultsPage.validateNumberOfSearchResults();
        boolean searchResultsComparison = actualSearchResults >= numberOfSearchResults;
        Assert.assertTrue("Found "+ actualSearchResults + " results and required at least " +
                        numberOfSearchResults, searchResultsComparison);
        scenario.log();

    }

}
