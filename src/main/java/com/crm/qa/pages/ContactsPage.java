package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactLabel;
	
	//@FindBy(xpath="//td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']/input")
	//WebElement clikOnContact;
	
	@FindBy(id="first_name")
	WebElement firstname;
	
	@FindBy(id="surname")
	WebElement surname;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath="//input[@name='addmore']//preceding-sibling::input[@type='submit']")
	WebElement save_btn;
	
	//initializing objects
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLable()
	{
		return contactLabel.isDisplayed();
	}
	
	public void selectContacts(String contactname)
	{
		driver.findElement(By.xpath("//a[text()='"+contactname+"']"
				+ "//parent::td[@class='datalistrow']//"
				+ "preceding-sibling::td[@class='datalistrow']/input")).click();
	}
	public void createNewContact(String title, String ftname, String lname, String comp)
	{
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		
		firstname.sendKeys(ftname);
		surname.sendKeys(lname);
		company.sendKeys(comp);
		save_btn.click();
	}

}
