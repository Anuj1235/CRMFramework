package com.crm.qa.pages;

import com.crm.qa.Base.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {


    //Page Factory - OR:(Object Repository )
    @FindBy(name="username")
    WebElement username;   //variable  --> WebElement username

    @FindBy(name="password")
    WebElement password;

    @FindBy(xpath="//input[@type='submit']")
    WebElement loginBtn;

    @FindBy(xpath="//button[contains(text(),'Sign Up')]")
    WebElement signUpBtn;

    @FindBy(xpath="//img[contains(@class,'img-responsive')]")
    WebElement crmLogo;

    //Initializing the Page Objects:
    public LoginPage(){
         // constructor is define LoginPage

        //'this' is used initilizes current class object

        //driver is Parent class TestBase

        PageFactory.initElements(driver, this);   //initElements method is define  & this means pointing to the current class object
    }

    //Actions:
    public String validateLoginPageTitle(){
        return driver.getTitle();
    }

    public boolean validateCRMImage(){
        return crmLogo.isDisplayed();
    }

    public HomePage login(String un, String pwd){
        username.sendKeys(un);
        password.sendKeys(pwd);
        //loginBtn.click();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", loginBtn);

        return new HomePage();
    }
























}
