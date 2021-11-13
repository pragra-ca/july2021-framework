package io.pragra.framework.testcases;

import io.pragra.framework.config.Config;
import io.pragra.framework.data.ContactProvider;
import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.listener.ScreenShotListener;
import io.pragra.framework.pages.RequestDemoPage;
import io.pragra.framework.pages.TopNavBar;
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
        //Assert.assertEquals(driver.getTitle(), "Video Conferencing, Cloud Phone, Webinars, Chat, Virtual Events | Zoom");
    }

    @Test
    public void checkRequestDemoLink(){
        requestDemoPage = topNavBar.clickOnRequestDemo();
        Assert.assertEquals( requestDemoPage.getHeaderText(), "Request a Demo");
    }

    @Test(dependsOnMethods ="checkRequestDemoLink", dataProvider = "contactProvider", dataProviderClass = ContactProvider.class)
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
    }
}
