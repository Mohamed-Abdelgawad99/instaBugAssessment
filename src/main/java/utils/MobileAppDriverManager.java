package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class MobileAppDriverManager {

    private static MobileAppDriverManager instance = null;
    private static AndroidDriver driver;

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
        System.out.println("📱 Starting mobile app driver...");

        if (driver != null) {
            System.out.println("✅ Driver already initialized. Reusing existing driver.");
            return;
        }

        try {
            // Validate APK path
            File appFile = new File("apk-files/selendroid-test-app.apk");
            if (!appFile.exists()) {
                throw new RuntimeException("❌ APK file not found at: " + appFile.getAbsolutePath());
            }

            System.out.println("📦 APK located at: " + appFile.getAbsolutePath());

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("deviceName", "emulator-5554"); // or your device
            caps.setCapability("platformVersion", "11.0");
            caps.setCapability("app", new File("apk-files/selendroid-test-app.apk").getAbsolutePath());
            caps.setCapability("appWaitActivity", "*");

            // Optional: reset app on each run
            caps.setCapability("fullReset", true);
            caps.setCapability("noReset", false);

            driver = new AndroidDriver(new URL("http://192.168.1.13:4723/"), caps);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Optional: confirm app is launched
            System.out.println("✅ Driver initialized.");
            System.out.println("🔧 Package: " + driver.getCurrentPackage());
            System.out.println("🔧 Activity: " + driver.currentActivity());

        } catch (MalformedURLException e) {
            throw new RuntimeException("❌ Invalid Appium server URL.", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to initialize mobile driver: " + e.getMessage());
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