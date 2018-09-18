package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	protected static WebDriver driver = null;
	protected static Properties prop;
	protected static EventFiringWebDriver e_driver;
	protected static WebEventListener eventlistener; 
	
	protected static JavascriptExecutor js;
	
	public TestBase() 
	 {
		FileInputStream fs;
		try 
		{	
			prop = new Properties();
			fs = new FileInputStream("C:\\Back Up\\Project Work\\Learning Stuffs\\Selenium Training By Jitendra\\Self Study\\Programs\\FreeCRMTestAutomationUsingPageObjectModel\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(fs);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	 }
	 
	 public static void initialization()
	 {
		 
		 //Singleton pattern
		 if(driver == null)
		{
			String browserName = prop.getProperty("browser");
			if (browserName.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Back Up\\Project Work\\Learning Stuffs\\Selenium Training By Jitendra\\Self Study\\Programs\\FreeCRMTestAutomationUsingPageObjectModel\\src\\main\\java\\com\\crm\\qa\\exe\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				driver = new ChromeDriver(options);

			}
			else if (browserName.equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", "C:\\Back Up\\Project Work\\Learning Stuffs\\Selenium Training By Jitendra\\Self Study\\Programs\\FreeCRMTestAutomationUsingPageObjectModel\\src\\main\\java\\com\\crm\\qa\\exe\\geckodriver.exe");
				// ChromeOptions options = new ChromeOptions();
				// options.setExperimentalOption("useAutomationExtension", false);
				driver = new FirefoxDriver();
			}

			// To generate logs
			e_driver = new EventFiringWebDriver(driver);
			eventlistener = new WebEventListener();
			e_driver.register(eventlistener);
			driver = e_driver;

			js = (JavascriptExecutor) driver;

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.page_load_timeout, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

			driver.get(prop.getProperty("url"));
		}
	 }
	 
	 public static void close()
	 {
		 System.out.println("Quitting the browser");
		 driver.close();
		 driver = null;
	 }
	 
	 public static void quit()
	 {
		 System.out.println("Quitting the browser");
		 driver.quit();
		 driver = null;
	 }
}
