package com.annotations;

import org.testng.annotations.*;
import org.testng.annotations.DataProvider;

public class DataProvider1 {
    @Test(dataProvider="getLoginData")
    public void LoginTestCase(String username,String password) {
        System.out.println("Login Test Case - " + username + " - " + password);
        //Login to Any website 
        //Identify the email or user name and sendkeys - pas this variable instead of hard coded values
    }
    
    //Login test case we want to execute with multiple sets of data
    //I will place the data in the dataprovider method
    //I have pass data to the login test case
    @DataProvider
    public Object[][] getLoginData(){
      Object[][] logindata = new Object[2][2];
      logindata[0][0] = "user1";
      logindata[0][1] = "pass1";
      logindata[1][0] = "user2";
      logindata[1][1] = "pass2";
      return logindata;
    }
}

