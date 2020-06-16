package com.annotations;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

public class TestAnnotations
{
	WebDriver driverC;
	WebDriver driverF;
	
  @BeforeTest(groups = {"firefox","chrome"})
  public void BT()
  {
	System.setProperty("webdriver.chrome.driver", "Resources//chromedriver.exe");
	System.setProperty("webdriver.gecko.driver", "Resources/geckodriver.exe");
  }

  @BeforeMethod(groups = "firefox")
  public void browserFirefox()
  {
	  driverF = new FirefoxDriver(); 
  }
  
  @BeforeMethod(groups = "chrome")
  public void browserChrome()
  {
	  driverC = new ChromeDriver();
  }
  
  @Test(groups = "firefox")
  public void facebook()
  {
	driverF.get("https://www.facebook.com");
	String title1 = driverF.getTitle();
	System.out.println("TC01 :"+title1+" opened");
  }

  @Test(groups = "chrome")
  public void amazon()
  {
	driverC.get("https://www.amazon.com");
    String title2 = driverC.getTitle();
	System.out.println("TC02 :"+title2+" opened");
  }
  
  @AfterMethod(groups = "firefox")
  public void closeFirefox()
  { 
	  driverF.close();
  }
  
  @AfterMethod(groups = "chrome")
  public void closeChrome()
  { 
	  driverC.close();
  }
  
  @AfterTest(groups = {"firefox","chrome"})
  public void AT()
  {
	  System.out.println("Test Executed Succesfully");
  }
}
