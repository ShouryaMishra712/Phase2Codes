package com.project.testcases;

import java.io.IOException;
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

import com.project.utilities.ExcelReader;
import com.project.utilities.PropertyReader;

public class CalorieCalculatorTestCaseEx {

	WebDriver driver;
	@BeforeClass
	public void Launch() throws InterruptedException, IOException {
	   if(PropertyReader.ReadProperty("browser").equals("firefox")) {
			//System.setProperty("webdriver.gecko.driver", "./Resources/geckodriver");
			System.setProperty("webdriver.gecko.driver", PropertyReader.ReadProperty("geckodriverpath"));
			driver = new FirefoxDriver();
	   
		//	driver.get("https://www.calculator.net/calorie-calculator.html");
			driver.get(PropertyReader.ReadProperty("appurl"));
			driver.manage().window().maximize();
	   }
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
		Thread.sleep(2000);
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