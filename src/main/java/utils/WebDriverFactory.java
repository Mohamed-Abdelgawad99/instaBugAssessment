package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebDriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver==null){
            System.setProperty("webdriver.chrome.driver", "E:\\Instabug Assesement\\instabug_model_1_assesment\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get("https://magento.softwaretestingboard.com/");
        }
        return driver;
    }

    public static void quiteDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
