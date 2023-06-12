package com.crm.qa.testcases;

import com.crm.qa.Base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactsPageTest extends TestBase {


    LoginPage loginPage;
    HomePage homePage;
    TestUtil testUtil;
    ContactsPage contactsPage;

    String sheetName = "contacts";


    public ContactsPageTest(){
        super();

    }


    @BeforeMethod
    public void setUp() throws InterruptedException {

        initialization();
        testUtil = new TestUtil();
        contactsPage = new ContactsPage();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        TestUtil.runTimeInfo("error", "login successful");
        testUtil.switchToFrame();  // contact in frame
        contactsPage = homePage.clickOnContactsLink();
    }

    @Test(priority=1)
    public void verifyContactsPageLabel(){
        Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
    }

    @Test(priority=2)
    public void selectSingleContactsTest(){
        contactsPage.selectContactsByName("test2 test2");
    }

    @Test(priority=3)
    public void selectMultipleContactsTest(){
        contactsPage.selectContactsByName("test2 test2");
        contactsPage.selectContactsByName("ui uiii");

    }

    @DataProvider
    public Object[][] getCRMTestData() throws InvalidFormatException {

        // Interview Que Ask : what is return type DataProvider ?
        //Ans : 2D Array --- 2 Dimentional Array
        Object data[][] = TestUtil.getTestData(sheetName);
        return data;
    }


    @Test(priority=4, dataProvider="getCRMTestData")
    public void validateCreateNewContact( String firstName, String lastName, String company) throws InterruptedException {
        homePage.clickOnNewContactLink();
        //contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
        Thread.sleep(1000);
        contactsPage.createNewContact(firstName, lastName, company);

    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

































}
