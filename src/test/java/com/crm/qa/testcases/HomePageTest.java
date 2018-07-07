package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage login;
	HomePage homepage;
	SoftAssert softassert = new SoftAssert();
	TestUtil testutil;
	ContactsPage contactspage;
	
	public HomePageTest()
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
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle()
	{
		//String homepagetitle = homepage.verifyHomePagetitle();
		softassert.assertEquals(homepage.verifyHomePagetitle(), "CRMPRO");
		softassert.assertAll();
	}
	
	@Test(priority=2)
	public void verifyUserNameLable()
	{
		testutil.switchToFrame();
		boolean namelabel = homepage.verifyCorrectUserName();
		Assert.assertTrue(namelabel);
	}	
	
	@Test(priority=3)
	public void verifyContactsLink()
	{
		testutil.switchToFrame();
		contactspage = homepage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
