import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Tests {

    @Test
    public void notepadTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElementByName("Text Editor").sendKeys("Hello World");

        Thread.sleep(1000);
        driver.findElementByName("File").click();
        driver.findElementByName("Save As...").click();
        Thread.sleep(1000);
        driver.findElementByName("File name:").clear();
        Thread.sleep(1000);
        driver.findElementByAccessibilityId("FileNameControlHost").sendKeys("hello.txt");
        Thread.sleep(1000);
        driver.findElementByName("Save").click();


        Thread.sleep(3000);
        driver.quit();

    }

    @Test
    public void calculatorTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElementByName("One").click();
        driver.findElementByName("Plus").click();
        driver.findElementByName("Seven").click();
        driver.findElementByName("Equals").click();

        System.out.println(driver.findElementByAccessibilityId("CalculatorResults").getText().replace("Display is ", ""));



        driver.quit();

    }

}
