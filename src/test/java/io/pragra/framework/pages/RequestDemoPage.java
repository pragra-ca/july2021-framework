package io.pragra.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestDemoPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@id='support_contact']/div/div/h2")
    private WebElement header;
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "first_name")
    private WebElement firstNameInput;
    @FindBy(id = "last_name")
    private WebElement lastNameInput;



    public RequestDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getHeaderText(){
        return this.header.getText();
    }

    public RequestDemoPage keyInEmail(String email){
        this.emailInput.sendKeys(email);
        return  this;
    }

    public RequestDemoPage keyInCompany(String company){
        this.companyInput.sendKeys(company);
        return  this;
    }

    public RequestDemoPage keyInFirstName(String firstName){
        this.firstNameInput.sendKeys(firstName);
        return  this;
    }

    public RequestDemoPage keyInLastName(String lastName){
        this.lastNameInput.sendKeys(lastName);
        return  this;
    }


}
