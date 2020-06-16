package com.project.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.project.pages.CaloriePage1;
import com.project.utilities.ExcelReader;
import com.project.utilities.PropertyReader;

public class CalorieCalculatorEmbeddedTC 
{
   WebDriver driver;
   @BeforeTest
   public void launch() throws IOException
   {
	  if(PropertyReader.ReadProperty("browser").equals("chrome"))
	  {
		  System.setProperty("webdriver.chrome.driver", PropertyReader.ReadProperty("chromedriverpath"));
		  driver = new ChromeDriver();
		  driver.get(PropertyReader.ReadProperty("appurl"));
		  driver.manage().window().maximize();		  
	  }
	  
	  else if (PropertyReader.ReadProperty("browser").equals("firefox"))
	  {
		  System.setProperty("webdriver.gecko.driver", PropertyReader.ReadProperty("geckodriverpath"));
		  driver = new FirefoxDriver();
		  driver.get(PropertyReader.ReadProperty("appurl"));
		  driver.manage().window().maximize();		  		  
	  }
   }
   
   @Test(dataProvider = "CalorieData")
   public void EnterCalorieDetails(Hashtable<String,String> data) throws InterruptedException
   {
	   CaloriePage1 cp = new CaloriePage1(driver);
       cp.EnterDetails(data.get("age"),data.get("sex"),data.get("height"),data.get("weight"),data.get("activity"));
       Thread.sleep(2000);
   }
   
   @DataProvider
   public Object [][] CalorieData() throws IOException
   {
	   String FilePath = PropertyReader.ReadProperty("testdatapath");
       String FileName = PropertyReader.ReadProperty("filename");
       String Sheetname = PropertyReader.ReadProperty("sheetname");
       return ExcelReader.ReadExcelDataToObjectArr(FilePath, FileName, Sheetname);
   }
   
   @AfterTest
   public void Close()
   {
	   driver.quit();
   }
}
