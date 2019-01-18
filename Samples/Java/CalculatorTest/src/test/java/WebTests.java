import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebTests {

    @Test
    public void webtest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");


        RemoteWebDriver driver = new RemoteWebDriver(new URL("https://<URL-OF-SELENIUMBOX>"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 5000);

        driver.get("https://www.bmw.de/de/index.html");
        driver.findElement(By.linkText("Alle BMW Modelle")).click();
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ds2-model-page > div.row > div > h1"))));
        Assert.assertEquals("Alle BMW Modelle: Übersicht | BMW.de", driver.getTitle());
        driver.quit();
    }
}
