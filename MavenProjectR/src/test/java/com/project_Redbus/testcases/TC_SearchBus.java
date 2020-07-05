package com.project_Redbus.testcases;

import com.project_Redbus.utilities.PropertyReader;
import com.project_Redbus.utilities.ExcelReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.project_Redbus.pages.jDetailPage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_SearchBus
{
 WebDriver driver;	
 public ExtentHtmlReporter htmlReporter = null;
 public ExtentReports extent = null;
 public ExtentTest logger = null;

  @BeforeTest
  public void startReport()
  {
      htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport.html");
      extent = new ExtentReports();
      extent.attachReporter(htmlReporter);
      extent.setSystemInfo("Host Name", "SoftwareTesting");
      extent.setSystemInfo("Environment", "Automation Testing");
      extent.setSystemInfo("User Name", "TestEngineer");
 	 htmlReporter.config().setDocumentTitle("Testing Report");
 	 htmlReporter.config().setReportName("RedBus find TestSuite");
 	 htmlReporter.config().setTheme(Theme.STANDARD);		
  }
  
	
 @BeforeMethod
 public void launchBrowser() throws IOException
 {
	 
	 if(PropertyReader.ReadProperty("browser").equals("chrome"))
	 {
		System.out.println("Starting test on Chrome Browser");
		System.setProperty("webdriver.chrome.driver", PropertyReader.ReadProperty("chromedriverpath"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	 }
	 else if(PropertyReader.ReadProperty("browser").equals("firefox"))
	 {
		System.out.println("Starting test on Fiirefox Browser");			
		System.setProperty("webdriver.gecko.driver", PropertyReader.ReadProperty("geckodriverpath"));
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	 }
	
		 
 }
 
@Test(dataProvider="JourneyData")
 public void getBuses1(Hashtable<String,String> data) throws InterruptedException, IOException
 {
	driver.get(PropertyReader.ReadProperty("appurl")); 
	logger = extent.createTest("Verify the Bus Search");
	jDetailPage jd = new jDetailPage(driver);
     jd.EnterDetails(data.get("fromS"),data.get("rFrom"),data.get("toS"),data.get("rTo"),data.get("rDate"));
 }


@DataProvider
public Object [][] JourneyData() throws IOException
{
	   String FilePath = PropertyReader.ReadProperty("testdatapath");
    String FileName = PropertyReader.ReadProperty("td_filename");
    String Sheetname = PropertyReader.ReadProperty("td_sheetname");
    return ExcelReader.ReadExcelDataToObjectArr(FilePath, FileName, Sheetname);
}

@AfterMethod
public void CloseBrowser(ITestResult result) throws IOException
{
	if(result.getStatus() == ITestResult.FAILURE){
		//log the results
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed ", ExtentColor.RED));
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed ", ExtentColor.RED));
		logger.fail("Test Case Failed Snapshot is below - " + logger.addScreenCaptureFromPath(getScreenshot(driver,result.getName())));
	}
	else if(result.getStatus() == ITestResult.SKIP){
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped ", ExtentColor.ORANGE));
	}
	else if(result.getStatus() == ITestResult.SUCCESS){
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed ", ExtentColor.GREEN));
	}
	driver.quit();
	
}

public String getScreenshot(WebDriver driver, String ScreenshotName) throws IOException
{
	//for that we need to reference another jar files 
	//one for the extent report and another is for snapshot
	//please go to google driver and unzip common-io jars and reference it in the project
	TakesScreenshot ts = (TakesScreenshot)driver;
	File source= ts.getScreenshotAs(OutputType.FILE);
	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	String destination = System.getProperty("user.dir")+"/Screenshots/" + ScreenshotName + dateName + ".png"; 
	//added common-io jars
	FileUtils.copyFile(source, new File(destination));
	return destination;
}

@AfterTest
public void stopTest()
{
	extent.flush(); //at the end of all tests - it will append the test results to the htmlreport when flushed
}

}
