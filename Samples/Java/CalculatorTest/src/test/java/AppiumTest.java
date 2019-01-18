


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
        import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
        import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class AppiumTest {


    @Test
    public void mobileWebTest() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
        caps.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        caps.setCapability(CapabilityType.VERSION, "70");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S8");
        caps.setCapability("e34:token", "19705d15-03b8-4f");

        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://vm-106.element34.net/wd/hub"), caps);

        driver.get("https://www.bmw.de/de/index.html");
        driver.quit();
    }



    @Test(invocationCount = 1, threadPoolSize = 10)
    public void downloadApkFromUrlAndRunNativeTest() throws IOException, InterruptedException {
        String appLocation = "http://static.element34.net/mobile/demo_apks/ApiDemos-debug.apk";
        mobileNativeTest(appLocation);
    }

    private void mobileNativeTest(String appLocation) throws MalformedURLException, InterruptedException {
        String threadInfo = String.format("%s - %s", Thread.currentThread().getId(), Thread.currentThread().getName());
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
        caps.setCapability("e34:token", "19705d15-03b8-4f");

        caps.setCapability("e34:app", appLocation);
        caps.setCapability("e34:video", true);
        caps.setCapability("e34:l_testName", "nativeApp " + threadInfo);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5X");

        AndroidDriver driver = new AndroidDriver(new URL("http://vm-106.element34.net/wd/hub"), caps);
        Wait<AndroidDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .ignoring(NotFoundException.class)
                .ignoring(WebDriverException.class);
        try {
            // For demos, change cycles and sleep so the test takes longer and more containers can be seen in the live view.
            int cycles = 1;
            long sleep = 0;
            for (int i = 0; i < cycles; i++) {
                MobileElement accessibility = (MobileElement) wait.until(androidDriver -> driver.findElementByAccessibilityId("Accessibility"));
                Thread.sleep(sleep);
                accessibility.click();
                Thread.sleep(sleep);
                MobileElement accessibilityService = (MobileElement) wait.until(androidDriver -> driver.findElementByAccessibilityId("Accessibility Service"));
                Thread.sleep(sleep);
                accessibilityService.click();
                Thread.sleep(sleep);
                wait.until(androidDriver -> driver.findElementById("io.appium.android.apis:id/button"));
                Thread.sleep(sleep);
                driver.navigate().back();
                Thread.sleep(sleep);
                wait.until(androidDriver -> driver.findElementByAccessibilityId("Accessibility Service"));
                Thread.sleep(sleep);
                driver.navigate().back();
                Thread.sleep(sleep);
                MobileElement app = (MobileElement) wait.until(androidDriver -> driver.findElementByAccessibilityId("App"));
                Thread.sleep(sleep);
                app.click();
                Thread.sleep(sleep);
                MobileElement alertDialogs = (MobileElement) wait.until(androidDriver -> driver.findElementByAccessibilityId("Alert Dialogs"));
                Thread.sleep(sleep);
                alertDialogs.click();
                Thread.sleep(sleep);
                wait.until(androidDriver -> driver.findElementByAccessibilityId("List dialog"));
                Thread.sleep(sleep);
                driver.navigate().back();
                Thread.sleep(sleep);
                wait.until(androidDriver -> driver.findElementByAccessibilityId("Alert Dialogs"));
                Thread.sleep(sleep);
                driver.navigate().back();
                Thread.sleep(sleep);
            }
        } finally {
            driver.quit();
        }
    }
}
