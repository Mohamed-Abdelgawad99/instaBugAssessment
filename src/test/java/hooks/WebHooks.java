package hooks;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import utils.WebDriverFactory;

import java.util.Objects;

public class WebHooks {
    @After
    public void tearDown() {
            WebDriver driver = WebDriverFactory.getDriver();
            if (driver != null) {
                System.out.println("🔻 Closing browser after scenario...");
                driver.quit();
            }

    }
}

