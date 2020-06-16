package com.project.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalorieCalculatorTestCase {

	WebDriver driver;
	@BeforeMethod
	public void Launch() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "Resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.calculator.net/calorie-calculator.html");
		driver.manage().window().maximize();
	}
	
	@Test
	public void EnterCalorieDetails() {
		//Identify age Textbox
		//Importing packages Ctrl_Shift+O
		WebElement ageTextbox  = driver.findElement(By.id("cage"));
		ageTextbox.clear();
		ageTextbox.sendKeys("45");
		
		//Identify the gender
		List<WebElement> GenderList = driver.findElements(By.name("csex"));
		for(WebElement Gender : GenderList)
		{
			if(Gender.getAttribute("value").equals("f")) 
			{
				if(!Gender.isSelected()) {
					Gender.click();
					break;
				}
			}
		}
   }
	
	@AfterMethod
	public void CloseBrowser() throws InterruptedException {
		Thread.sleep(4000);
		if(driver!=null) {
			
		}
	}	
}