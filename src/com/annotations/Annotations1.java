package com.annotations;

import org.testng.annotations.*;

public class Annotations1 
{
	@BeforeTest
	public void BT()
	{
		System.out.println("Before Test");
	}
	
	@BeforeClass
	public void BC()
	{
		System.out.println("Before Class");
	}
	
	@BeforeMethod
	public void BM()
	{
		System.out.println("Before Method");
	}
     
	@Test
	public void T1()
	{
	 System.out.println("First Test");
	}

	@Test
	public void T2()
	{
	 System.out.println("Second Test");
	}
	
	@AfterMethod
	public void AM()
	{
		System.out.println("After Method");
	}
	
	@AfterClass
	public void AC()
	{
		System.out.println("After Class");
	}
	
	@AfterTest
	public void AT()
	{
		System.out.println("After Test");
	}
	   
}
