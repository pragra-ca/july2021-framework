package io.pragra.framework.testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.pragra.framework.config.Config;
import io.pragra.framework.data.ContactProvider;
import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.listener.ScreenShotListener;
import io.pragra.framework.pages.RequestDemoPage;
import io.pragra.framework.pages.TopNavBar;
import io.pragra.framework.reports.HTMLReports;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(ScreenShotListener.class)
public class TopNavBarTest {

    WebDriver driver;
    TopNavBar topNavBar;
    RequestDemoPage requestDemoPage;

    @BeforeSuite
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(Config.getProperty("app.url"));
        topNavBar = new TopNavBar(driver);
    }

    @Test
    public void testTitle(){
        ExtentTest test = HTMLReports.createTest("Title Test");
        test.log(Status.FAIL, "Test Failed");
        //Assert.assertEquals(driver.getTitle(), "Video Conferencing, Cloud Phone, Webinars, Chat, Virtual Events | Zoom");
    }

    @Test
    public void checkRequestDemoLink(){
        requestDemoPage = topNavBar.clickOnRequestDemo();
        Assert.assertEquals( requestDemoPage.getHeaderText(), "Request a Demo");
        ExtentTest test = HTMLReports.createTest("checkRequestDemoLink");
        test.log(Status.PASS, "TestPassed Successfully");

    }

    @Test( enabled = false, dependsOnMethods ="checkRequestDemoLink", dataProvider = "contactProvider", dataProviderClass = ContactProvider.class)
    public void submitForm(String[] data){
        requestDemoPage.keyInEmail(data[0])
                .keyInCompany(data[1])
                .keyInFirstName(data[2])
                .keyInLastName(data[3]);
    }


    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        DriverManager.closeBrowser();
        HTMLReports.closeTest();
    }
}
