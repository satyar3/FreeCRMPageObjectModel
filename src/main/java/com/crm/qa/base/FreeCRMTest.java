package com.crm.qa.base;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.qa.pages.LoginPage;

public class FreeCRMTest
{
	public LoginPage login;
	
	@BeforeClass
	public void setUp()
	{
		TestBase.initialization();
		login = new LoginPage();
	}
	
	@Test
	public void verifyFreeCRMTitileTest()
	{
		String pagetitile = TestBase.driver.getTitle();
		System.out.println("Page title is : "+pagetitile);
		
		Assert.assertEquals(pagetitile, "CRMPRO");
	}
	
	@Test
	public void logoTest() throws InterruptedException
	{
		Assert.assertTrue(login.validateCRMImage());
	}
	
	@AfterClass
	public void tearDown()
	{
		TestBase.quit();
	}

}
