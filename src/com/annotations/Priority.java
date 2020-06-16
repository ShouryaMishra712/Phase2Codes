package com.annotations;

import org.testng.annotations.Test;

public class Priority {
	@Test(priority=0)
    public void Login() {
        System.out.println("Test1");
    }
    @Test(priority=1)
    public void AccountEnquiry() {
    System.out.println("Test2");
    }
    
    @Test(priority=2)
    public void FundTransfer() {
        System.out.println("FundTransfer");
    }

}
