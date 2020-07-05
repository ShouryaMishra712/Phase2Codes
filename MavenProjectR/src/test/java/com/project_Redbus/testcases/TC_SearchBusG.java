 package com.project_Redbus.testcases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.project_Redbus.pages.jDetailPage;
import com.project_Redbus.utilities.ExcelReader;
import com.project_Redbus.utilities.PropertyReader;

public class TC_SearchBusG 
{
	  
	WebDriver driver = null;
	DesiredCapabilities caps = null;
	
	@Test(dataProvider="JourneyData")
	 public void getBuses1(Hashtable<String,String> data) throws InterruptedException, IOException
	 {
		String browser = data.get("browser"); 
		if(browser.equals("chrome"))
			{
			caps = DesiredCapabilities.chrome();
			caps.setBrowserName("chrome");
			//caps.setPlatform(Platform.ANY);
			//caps.setVersion("");
			}
			else if(browser.equals("firefox")) 
			{
			caps = DesiredCapabilities.firefox();
			caps.setBrowserName("chrome");
			
			}
	
			driver = new RemoteWebDriver(new URL("http://192.168.43.170:4444/wd/hub"),caps);
			
			driver.get(PropertyReader.ReadProperty("appurl")); 
		jDetailPage jd = new jDetailPage(driver);
	     jd.EnterDetails(data.get("fromS"),data.get("rFrom"),data.get("toS"),data.get("rTo"),data.get("rDate"));
	    driver.close();
	 }


	@DataProvider
	public Object [][] JourneyData() throws IOException
	{
		   String FilePath = PropertyReader.ReadProperty("testdatapath");
	    String FileName = PropertyReader.ReadProperty("td_filename");
	    String Sheetname = PropertyReader.ReadProperty("td_sheetname");
	    return ExcelReader.ReadExcelDataToObjectArr(FilePath, FileName, Sheetname);
	}
	

	 
	
}
