package com.project.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.project.ObjectMap.ObjectRepos1;

public class CaloriePage1 
{
	    //@FindBy(xpath="cage")
	    @FindBy(xpath=ObjectRepos1.CaloriePage1.cp_metric_xpath)
	    public WebElement metricUnit;
			
	
	    //@FindBy(id="cage")
		@FindBy(id=ObjectRepos1.CaloriePage1.cp_age_id)
		public WebElement ageTextbox;
		
		//@FindBy(name="csex")
		@FindBy(name=ObjectRepos1.CaloriePage1.cp_sex_name)
		public List <WebElement> GenderList;

		//@FindBy(id="cheightmeter")
		@FindBy(id=ObjectRepos1.CaloriePage1.cp_height_id)
	    public WebElement heightTextbox;

		//@FindBy(id="ckg")
		@FindBy(id=ObjectRepos1.CaloriePage1.cp_weight_id)
	    public WebElement weightTextbox;

		//@FindBy(id="cactivity")
		@FindBy(id=ObjectRepos1.CaloriePage1.cp_activity_id)
	    public WebElement activityDrp;

		WebDriver driver;
		public CaloriePage1(WebDriver driver) 
		{	
		 this.driver = driver;
		 //initialize the Page Elements 
		 PageFactory.initElements(driver, this);
        }
        
		public void EnterDetails(String age, String sex , String height , String weight , String activity) throws InterruptedException
        {
		    metricUnit.click();
			
		    Thread.sleep(3000);
		    
			ageTextbox.clear();
			ageTextbox.sendKeys(age);
			
			for(WebElement Gender : GenderList)
			{
				if(Gender.getAttribute("value").equals(sex)) 
				{
					if(!Gender.isSelected()) {
						Gender.click();
						break;
					}
				}
			}
			
			heightTextbox.clear();
			heightTextbox.sendKeys(height);
			
			weightTextbox.clear();
			weightTextbox.sendKeys(weight);

			Select Activity = new Select(activityDrp);
			Activity.selectByValue(activity);

        }
}