package com.annotations;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class Assertions {
	SoftAssert soft = new SoftAssert();
	WebDriver driver;
	@Test
	public void Launch() {
		System.setProperty("webdriver.gecko.driver", "Resources//geckodriver.exe");
		driver = new FirefoxDriver();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "Launch" })
	public void Facebook() {
		driver.get("https://www.facebook.com");
		soft.assertEquals("Facebook – log in or sign up", driver.getTitle());	
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods = { "Facebook" })
	public void Login() {
		driver.findElement(By.id("email")).sendKeys("shourya.mishra.712");
		driver.findElement(By.id("pass")).sendKeys("Shourya@71298");
		driver.findElement(By.id("u_0_b")).click();
		soft.assertTrue(driver.getTitle().equals("Facebook"));
        soft.assertAll();
		try 
		{
			Thread.sleep(3000);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		driver.close();       
	}
	
}

