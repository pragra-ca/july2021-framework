package io.pragra.framework.drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.pragra.framework.config.Config;
import io.pragra.framework.utils.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
    Singleton Pattern
    - We only allow to create 1 instance of class
    driver = new ChromeDriver();
 */
public final class DriverManager {
    private static DriverManager driverManager;
    private WebDriver driver;
    private DriverManager() {
        if(Config.getProperty("browser").equals(Constants.FIREFOX) ){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(Config.getProperty("browser").equals(Constants.SAFARI) ){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else {
            // Default is chrome
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }
    public synchronized static WebDriver getDriver() {
        if(driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager.driver;
    }

    public synchronized static void  closeBrowser(){
        driverManager.driver.quit();
        driverManager = null;
    }
}
