package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.qa.extentreportlistener.ExtentReporterNG;


@Listeners(ExtentReporterNG.class)
public class LoginPageTest extends TestBase{
	
	LoginPage loginpage;
	HomePage homepage;

	//public ExtentReports extent;
	//public ExtentTest logger;
	
	
	public LoginPageTest() 
	{
		super();
	}
	
	/*@BeforeTest
	public void setExtent()
	{
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);//true means, replace the old file
		
		//Adding parameters which are available in extent report file
		extent.addSystemInfo("Host Name", "Satya Windows");
		extent.addSystemInfo("User Name", "Satya Automation");
		extent.addSystemInfo("Environment", "QA");
		
	}*/
	
	/*@AfterTest
	public void endReport()
	{
		extent.flush();
		extent.close();
	}*/
	
	/*public static String getScreenShot(WebDriver driver, String ScreenShotName) throws IOException
	{
		String dateName = new SimpleDateFormat("YYYYMMddhhmmss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir") + "/FailedTestScreenShots/" + ScreenShotName + dateName +".png";
				
		File finaldestination = new File(destination);
		FileUtils.copyFile(src, finaldestination);
		
		return destination;
		
		//File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(src, new File("C:\\Back Up\\Google.png"));
	}*/
	
	@BeforeTest
	public void setUp() 
	{
		initialization();
		loginpage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitletest() throws IOException
	{
		//logger = extent.startTest("loginPageTitletest");
		String title = loginpage.validateLoginPagetitle();
		Assert.assertEquals(title, "Free CRM software in the cloud powers sales and customer service1");
		//TestUtil.takeScreenshotAtEndOfTest();
	}
	
	@Test(priority=2)
	public void crmLogoImageTest() throws InterruptedException, IOException
	{
		//logger = extent.startTest("crmLogoImageTest");
		boolean image = loginpage.validateCRMImage();
		Assert.assertTrue(image);
		//Assert.fail();
		//TestUtil.takeScreenshotAtEndOfTest();
	}
	
	@Test(priority=3)
	public void loginTest() throws IOException, InterruptedException 
	{
		//logger = extent.startTest("loginTest");
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homepage.driver.getTitle(),"CRMPRO1");
		//TestUtil.takeScreenshotAtEndOfTest();
	}
	
	@AfterTest
	public void tearDown()
	{
		
		driver.quit();
	}
	
	/*@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, "Test Case Failed is "+ result.getName());
			logger.log(LogStatus.FAIL, "Error is "+ result.getThrowable());
			
			String screenshotpath = LoginPageTest.getScreenShot(driver,result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotpath));
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			logger.log(LogStatus.FAIL, "Test Case Skipped is "+ result.getName());			
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, "Test Case Passed is "+ result.getName());			
		}
		extent.endTest(logger); //ending test and end current step and prepare html report
		driver.quit();
	}*/
}
