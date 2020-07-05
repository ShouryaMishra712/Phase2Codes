package com.project_Redbus.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project_Redbus.ObjectMap.ObjectRepos1;

public class jDetailPage 
{
  @FindBy(id = ObjectRepos1.jDetailPage.fromL_id)
  public WebElement from;
  
  @FindBy(xpath = ObjectRepos1.jDetailPage.fromL_xpath)
  public List<WebElement> fromL;
  
  @FindBy(id = ObjectRepos1.jDetailPage.toL_id)
  public WebElement to;
  
  @FindBy(xpath = ObjectRepos1.jDetailPage.toL_xpath)
  public List<WebElement> toL;
  
  @FindBy(id = ObjectRepos1.jDetailPage.jDate_id)
  public WebElement date;
  
  @FindBy(xpath = ObjectRepos1.jDetailPage.jDateMonth_xpath)
  public WebElement dateM;
  
  @FindBy(xpath = ObjectRepos1.jDetailPage.jDateNext_xpath)
  public WebElement dateN;
  
  @FindBy(xpath = ObjectRepos1.jDetailPage.jDateE_xpath)
  public List<WebElement> dateS;
  
  @FindBy(id = ObjectRepos1.jDetailPage.searchB_id)
  public WebElement searchB;
  
  
  WebDriver driver;
  public jDetailPage(WebDriver driver)
  {
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
  
  
  public void EnterDetails(String fromS, String rFrom, String toS , String rTo , String rDate) throws InterruptedException
  {
//	  wait.until(ExpectedConditions.elementToBeClickable(from));
	  
	  from.click();
	  from.sendKeys(fromS);
	  Thread.sleep(1000);
	  for (WebElement FromL : fromL) 
	  {
		  System.out.println(FromL.getText());
		if(FromL.getText().equals(rFrom))
		{
			  System.out.println("found "+FromL.getText());
				
			FromL.click();
			break;
		}
	  }
	  
	  Thread.sleep(2000);   
	  to.click();
    	    to.sendKeys(toS);
    	    Thread.sleep(1000);
    	    for (WebElement ToL : toL) 
    	    {
    	    	System.out.println(ToL.getText());
    	    	if(ToL.getText().equals(rTo))
    	    	{
    	    		System.out.println("found "+ToL.getText());
    	    		ToL.click();
    	    		break;
    	    	}
    	    }
    	
      String Sdate[] = rDate.split("\\s", 2);
      
      for (String string : Sdate) 
      {
		System.out.println(string);
      }
      
      
      Thread.sleep(3000);
      
      date.click();
      
      Thread.sleep(1000);
    
    	  while(!dateM.getText().equals(Sdate[1])) 
          {
    		  System.out.println(dateM.getText());
    		  dateN.click();
    	  }
    	
    	  Thread.sleep(1000);
    	  
    	  for (WebElement DateS : dateS) 
          {
    		  System.out.println(DateS.getText());
            	  
       		  if(DateS.getText().equals(Sdate[0]))
       		  {
       			System.out.println("found "+Sdate[0]);
           	 
       			DateS.click();
       		    break;
       		  }
          }
    	  
    	 Thread.sleep(2000);
         searchB.click();
      
         Thread.sleep(5000);
  }
   
}