package com.project.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.project.ObjectMap.ObjectRepos;

public class CaloriePage {
	
	//By @Find Annotiation
	
	//@FindBy(id="cage")
	@FindBy(id=ObjectRepos.CaloriePage.cp_age_id)
	public WebElement ageTextbox;
	
	//@FindBy(name="csex")
	@FindBy(name=ObjectRepos.CaloriePage.cp_sex_name)
	public List <WebElement> GenderList;

	WebDriver driver;
	public CaloriePage(WebDriver driver) {	
		this.driver = driver;
		//initialize the Page Elements 
		PageFactory.initElements(driver, this);
	}


	public void EnterCalorieDetails(String age, String sex) {
		ageTextbox.clear();
		ageTextbox.sendKeys(age);
		for(WebElement Gender : GenderList)
		{
			if(Gender.getAttribute("value").equals(sex)) {
				if(!Gender.isSelected()) {
					Gender.click();
					break;
				}
			}
		}

	}
	
	

}
