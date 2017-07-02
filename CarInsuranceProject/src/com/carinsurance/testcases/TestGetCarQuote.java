package com.carinsurance.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.carinsurance.page.base.Page;
import com.carinsurance.page.getquote.GotoGetQuotePage;
import com.carinsurance.page.landing.LandingPage;
import com.carinsurance.page.quote.RecievedQuote;
import com.carinsurance.util.TestUtil;

public class TestGetCarQuote extends Page{

	

@Test(dataProvider ="getTestData")
  public void GetQuote(Hashtable<String, String> data) throws InterruptedException{
	  LandingPage landingpg=PageFactory.initElements(driver, LandingPage.class);
	  GotoGetQuotePage Quotepg=landingpg.ClickCarquote();
	  JavascriptExecutor jse = (JavascriptExecutor)driver;
	  jse.executeScript("window.scrollBy(0,250)", "");
	 	 	
	 //Entering values to the page
	 Quotepg.SelectMakeFromDropDown(data.get("Make"));
	 Quotepg.EnterYear(data.get("Year"));
	 Quotepg.EnterDriversAge(data.get("Drivers Age"));
	 Quotepg.EnterDriverGender(data.get("Gender"));
	 Quotepg.SelectStateFromDropDown(data.get("State"));
	 Quotepg.EnterEmail(data.get("Email"));
	 boolean AgeFlag=Quotepg.CheckAge(data.get("Drivers Age"));
	 boolean EmailFlag=Quotepg.CheckEmail(data.get("Email"));
	 boolean YearFlag=Quotepg.CheckYear(data.get("Year"));
	 RecievedQuote rcv_quote=Quotepg.ClickGetquote();
	 if(rcv_quote==null)
	 {
		 //FOR AGE
		if(!AgeFlag && data.get("Drivers Age")==""){
			if(Quotepg.GetErrorMessage_age().equals(null))
				Assert.fail("Validation Message is not displayed in the screen. "+"The input value for age field is empty");
			else			
			{
				Assert.assertEquals(Quotepg.GetErrorMessage_age(),"The age is required. Numeric, bigger than zero.","The input value for age field is empty");
				Reporter.log("The valid error message is displayed.The test passed");
			  }
			}
		
		else if(!AgeFlag && data.get("Drivers Age")!=null){		
			if(Quotepg.GetErrorMessage_age().equals(null))
				Assert.fail("Validation Message is not displayed in the screen. "+" Age Entered is "+data.get("Drivers Age"));
			else				
			{
				Assert.assertEquals(Quotepg.GetErrorMessage_age(),"Valid age is required. Numeric, bigger than zero."," Age Entered is "+data.get("Drivers Age"));
			    Reporter.log("The valid error message is displayed.The test passed");
			  }
		    }	
		
		//FOR YEAR
		if(!YearFlag && data.get("Year")==""){
			if(Quotepg.GetErrorMessage_year().equals(null))
				Assert.fail("Validation Message is not displayed in the screen. "+"The year field is empty");
			else
			{				
				Assert.assertEquals( Quotepg.GetErrorMessage_year(),"The year is required.","The year field is empty");
				Reporter.log("The valid error message is displayed.The test passed");
			}
		}
		
		else if(!YearFlag && data.get("Year")!=null){
			if(Quotepg.GetErrorMessage_year().equals(null))
				Assert.fail("Validation Message is not displayed in the screen. "+"Year Entered is "+data.get("Year"));
			else				
			{
				Assert.assertEquals( Quotepg.GetErrorMessage_year(),"Valid year is required.","Year Entered is "+data.get("Year"));
				Reporter.log("The valid error message is displayed.The test passed");
				}
		}
		
		//FOR EMAIL
		if(!EmailFlag && data.get("Email")==""){
			if(Quotepg.GetErrorMessage_email().equals(null))
				Assert.fail("Validation Message is not displayed in the screen."+"The email input value is empty");				
			else
			{
				Assert.assertEquals( Quotepg.GetErrorMessage_email(),"The email is required");
			    Reporter.log("The valid error message is displayed.The test passed");
			  }
		}
		
		else if(!EmailFlag && data.get("Email")!=null){
			if(Quotepg.GetErrorMessage_email().equals(null))
				Assert.fail("Validation Message is not displayed in the screen. "+"Email entered is "+data.get("Email"));
			else
			{
				Assert.assertEquals( Quotepg.GetErrorMessage_email(),"Valid email is required","Email entered is "+data.get("Email") );
				Reporter.log("The valid error message is displayed.The test passed");
			}
		}
				
	 }
	 else
	 {	
		if(YearFlag && AgeFlag && EmailFlag)
		  Reporter.log("Test Passed.The monthly premium is "+rcv_quote.GetMonthlyPremium());
		driver.navigate().back();	 
	 	System.out.println("Year:"+YearFlag+" Age:"+AgeFlag+" Email:"+EmailFlag);	 	
	 	Assert.assertTrue(YearFlag, "The application proceded inspite of invalid entry in the YEAR field. "+"The entered year is "+data.get("Year")+"\n");
	 	Assert.assertTrue(AgeFlag, "The application proceded inspite of invalid entry in the AGE field. "+"The entered age is "+data.get("Drivers Age")+"\n");
	 	Assert.assertTrue(EmailFlag, "The application proceded inspite of invalid entry in the EMAIL field. "+"The entered email is "+data.get("Email")+"\n");
	 }
	 
	driver.navigate().back();	 
  }
 
 @DataProvider
  public Object[][] getTestData(){
	 return TestUtil.getData("TC_101", xls);
  }
 
 @AfterTest
	public void Teardown()
	{
	 	
	}
 
}
