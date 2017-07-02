package com.carinsurance.page.quote;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.carinsurance.util.Constants;

public class RecievedQuote {
	public WebDriver driver;
	
	@FindBy(xpath=Constants.PREMIUM_AMOUNT)
	public WebElement premium_amt;
	
	public RecievedQuote(WebDriver dr)
	{
		driver=dr;
	}
	
	public String GetMonthlyPremium()
	{
		String Premium;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Premium=premium_amt.getText();		
		System.out.println("Monthly Premium is "+Premium);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Premium;
	}

}
