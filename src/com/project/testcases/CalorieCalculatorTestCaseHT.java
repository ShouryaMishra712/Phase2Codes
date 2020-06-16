package com.project.testcases;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalorieCalculatorTestCaseHT {

	WebDriver driver;
	@BeforeClass
	public void Launch() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "./Resources/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://www.calculator.net/calorie-calculator.html");
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="getCalorieData")
	public void EnterCalorieDetails(Hashtable<String,String> data) {
		//Identify age Textbox
		//Importing packages Ctrl_Shift+O
		WebElement ageTextbox  = driver.findElement(By.id("cage"));
		ageTextbox.clear();
		ageTextbox.sendKeys(data.get("age"));
		
		//Identify the gender
		List<WebElement> GenderList = driver.findElements(By.name("csex"));
		for(WebElement Gender : GenderList)
		{
			if(Gender.getAttribute("value").equals(data.get("sex"))) {
				if(!Gender.isSelected()) {
					Gender.click();
					break;
				}
			}
		}
   }
	
	@DataProvider
	public Object[][] getCalorieData() {
		Object[][] data = new Object[2][1];
		//first record
		Hashtable<String,String> record1  = new Hashtable<String,String>();
		record1.put("age", "45");
		record1.put("sex", "f");
		//first record
		Hashtable<String,String> record2  = new Hashtable<String,String>();
		record2.put("age", "56");
		record2.put("sex", "m");
		data[0][0] = record1;
		data[1][0] = record2;
		return data;
	}
	@AfterClass
	public void CloseBrowser() throws InterruptedException {
		Thread.sleep(4000);
		if(driver!=null) {
			driver.quit();
		}
	}	
}