package io.pragra.framework.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.pragra.framework.config.Config;
import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.listener.ScreenShotListener;
import io.pragra.framework.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(ScreenShotListener.class)
public class ConfigTest {
    WebDriver driver;

    @BeforeSuite
    public void setUp() {
       driver = DriverManager.getDriver();
       driver.get(Config.getProperty("app.url"));
    }

    @Test
    public void passingTest() {
        Assert.assertEquals(Config.getProperty("browser"), "chrome");
    }

    @Test
    public void failingTest() {
        driver.findElement(By.linkText("Contact Sales")).click();
        Assert.assertEquals(Config.getProperty("browser"), "firefox");
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        DriverManager.closeBrowser();
    }
}
