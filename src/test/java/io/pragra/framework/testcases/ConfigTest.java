package io.pragra.framework.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.pragra.framework.config.Config;
import io.pragra.framework.data.ExcelReader;
import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.listener.ScreenShotListener;
import io.pragra.framework.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;


public class ConfigTest {

    @Test
    public void passingTest() {
        List<Object[]> emailData = ExcelReader.getDataFromSheet("Contact", false);

        emailData.forEach(
                s-> System.out.println(Arrays.toString(s))
        );

    }


}
