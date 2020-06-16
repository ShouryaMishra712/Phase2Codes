package com.project.testcases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.utilities.ExcelReader;

public class CalorieCalculatorTestCaseExcel {

	WebDriver driver;
	@BeforeClass
	public void Launch() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "Resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.calculator.net/calorie-calculator.html");
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="getCalorieData")
	public void EnterCalorieDetails(Hashtable<String,String> data) throws InterruptedException {
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
		
		WebElement heightTextbox = driver.findElement(By.id("cheightmeter"));
		heightTextbox.clear();
		heightTextbox.sendKeys(data.get("height"));
		
		WebElement weightTextbox = driver.findElement(By.id("ckg"));
		weightTextbox.clear();
		weightTextbox.sendKeys(data.get("weight"));
		
		WebElement activity = driver.findElement(By.id("cactivity"));
		Select drpActivity = new Select(activity);
		drpActivity.selectByValue(data.get("activity"));
		Thread.sleep(4000);
		
   }
	
	@DataProvider
	public Object[][] getCalorieData() throws IOException {
		
		String FilePath = System.getProperty("user.dir")+"/src/com/project/testdata";
		String FileName = "CalorieTestData.xlsx";
		String Sheetname = "CalorieTestData";
		return ExcelReader.ReadExcelDataToObjectArr(FilePath, FileName, Sheetname);
/*		Object[][] data = new Object[2][1];
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
*/	}
	@AfterClass
	public void CloseBrowser() throws InterruptedException {
		Thread.sleep(4000);
		if(driver!=null) {
			driver.quit();
		}
	}	
}