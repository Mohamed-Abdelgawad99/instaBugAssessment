package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileAppDriverManager {

    private static MobileAppDriverManager instance = null;
    private static AndroidDriver driver = null      ;

    // ✅ Private constructor to prevent direct instantiation
    private MobileAppDriverManager() { }

    // ✅ Public method to provide the singleton instance
    public static MobileAppDriverManager getInstance() {
        if (instance == null) {
            instance = new MobileAppDriverManager();
        }
        return instance;
    }

    // ✅ Initialize the driver only if it's null
    public void launchApp() {
        System.out.println("Inside launch app");
        if (driver == null) {
            try {
                System.out.println("🚀 Launching the Mobile App...");

                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0"); // Adjust as needed
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554"); // Change as per device
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                caps.setCapability("app", "path/to/your/app.apk"); // Path to APK
                caps.setCapability("appWaitActivity", "com.example.MainActivity");

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                System.out.println("✅ Mobile App Launched Successfully.");
            } catch (MalformedURLException e) {
                throw new RuntimeException("❌ ERROR: Invalid Appium URL", e);
            }
        } else {
            System.out.println("✅ Mobile App is already launched. Using existing driver instance.");
        }
    }

    // ✅ Method to retrieve the driver instance
    public AndroidDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("❌ ERROR: Driver is not initialized. Call launchApp() first.");
        }
        return driver;
    }

    // ✅ Method to quit the driver instance
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("✅ AndroidDriver session ended.");
        }
    }
}