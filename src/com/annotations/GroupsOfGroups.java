package com.annotations;

import org.testng.annotations.*;

@Test
public class GroupsOfGroups {
  @Test(groups = { "windows.checkintest" })
  public void testWindowsOnly() {
	  System.out.println("windows checkin present");
  }
 
  @Test(groups = {"linux.checkintest"} )
  public void testLinuxOnly() {
	  System.out.println("linux checkin present");
  }
 
  @Test(groups = {"linux.funtest"} )
  public void testLinuxToo() {
	  System.out.println("linux function present");
  }
  
  @Test(groups = { "windows.funtest" })
  public void testWindowsToo() {
	  System.out.println("windows function present");
  }
}


