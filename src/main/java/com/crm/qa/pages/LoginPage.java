package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	//Page Factory or Object repository
	
	@FindBy(name="username") //find by name not by Xpath
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submitbutton;
	
	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
	WebElement signupbutton;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmlogo;
	
	//Initialing the page objects
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
		//PageFactory.initElements(driver, LoginPage.class);//also possible
	}
	
	//Actions
	public String validateLoginPagetitle()
	{
		return driver.getTitle();
	}
	public boolean validateCRMImage() throws InterruptedException
	{
		Thread.sleep(2000);
		return crmlogo.isDisplayed();
	}
	public HomePage login(String un, String pwd) throws InterruptedException 
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		
		Thread.sleep(2000);
		submitbutton.click();
		
		return new HomePage();
	}
	
}
