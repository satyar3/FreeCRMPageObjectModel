package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	ContactsPage contactspage;
	LoginPage login;
	HomePage homepage;
	TestUtil testutil;
	String sheetName = "Contacts";
	
	public ContactsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		initialization();
		login = new LoginPage();
		homepage = login.login(prop.getProperty("username"), prop.getProperty("password"));
		testutil = new TestUtil();
		contactspage = new ContactsPage();
		testutil.switchToFrame();
		contactspage = homepage.clickOnContactsLink();
	}
	
	@DataProvider
	public Object[][] getTestData()
	{
		Object ob[][] = TestUtil.getTestData(sheetName);
		return ob;
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel()
	{
		Assert.assertTrue(contactspage.verifyContactsLable(),"Contact label is missing");
	}
	
	@Test(priority=2)
	public void selectContactTest()
	{
		contactspage.selectContacts("a a");
	}
	
	@Test(priority=3,dataProvider="getTestData")
	public void validateCreateNewContact(String title, String ftname, String lname, String comp) throws InterruptedException
	{
		homepage.clickOnNewContactLink();
		contactspage.createNewContact(title, ftname, lname, comp);
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
}
