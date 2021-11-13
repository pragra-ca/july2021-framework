package io.pragra.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopNavBar {
    private WebDriver driver;
    // #black-topbar ul>li:nth-child(2)>a

    @FindBy(xpath = "//*[@id='black-topbar']//ul/li[1]/a")
    private WebElement requestDemoLink;

    @FindBy(css = "#black-topbar ul>li:nth-child(2)>a")
    private WebElement one888Link;

    @FindBy(css = "#black-topbar ul>li:nth-child(3)>a")
    private WebElement resourceDropDown;

    @FindBy(css = "#black-topbar ul>li:nth-child(4)>a")
    private WebElement support;

    @FindBy(xpath = "//ul[@id='resourcesDropdown']/li[1]/a")
    private WebElement resourceDownLoad;

    @FindBy(xpath = "//ul[@id='resourcesDropdown']/li[3]/a")
    private WebElement resourceVideo;

    @FindBy(xpath = "//ul[@id='resourcesDropdown']/li[5]/a")
    private WebElement resourceLiveTraining;

    public TopNavBar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public RequestDemoPage clickOnRequestDemo(){
        this.requestDemoLink.click();
        return new RequestDemoPage(driver);
    }



}
