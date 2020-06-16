package com.project.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.project.pages.CaloriePage;
import com.project.utilities.PropertyReader;

public class CalorieCalculatorPageTestCase {
	
	WebDriver driver;
	@Test
	public void CalorieCalculatorPageTestCase() throws IOException {
		System.setProperty("webdriver.gecko.driver", PropertyReader.ReadProperty("geckodriverpath"));
		driver = new FirefoxDriver();
   
	//	driver.get("https://www.calculator.net/calorie-calculator.html");
		driver.get(PropertyReader.ReadProperty("appurl"));
		driver.manage().window().maximize();
		
		CaloriePage cp = new CaloriePage(driver);		
		cp.EnterCalorieDetails("45", "f");
	
	}

}
