package com.annotations;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Annotations2
{

	@BeforeSuite
	public void BS()
	{
		System.out.println("Before Suite");
	}
	
	@AfterSuite
	public void AS()
	{
		System.out.println("After Suite");
	}
	
	@Test 
	
	public void test3()
	{
		System.out.println("Test 3");
	}
	
}
