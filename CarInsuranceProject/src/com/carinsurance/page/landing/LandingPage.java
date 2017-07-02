package com.carinsurance.page.landing;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.carinsurance.page.base.Page;
import com.carinsurance.page.getquote.GotoGetQuotePage;
import com.carinsurance.util.Constants;

public class LandingPage extends Page{
	public WebDriver driver;	
	
	@FindBy(xpath=Constants.GETCARQUOTEBTN)	
	public WebElement getcarquotebtn;
	
	@FindBy(xpath=Constants.LABELSYDNEYTESTER)
	public WebElement labelsydneytester;
	
	public LandingPage(WebDriver dr)
	{
		driver=dr;
	}
	
	public GotoGetQuotePage ClickCarquote()
	{
		getcarquotebtn.click();
		if(labelsydneytester.isDisplayed())
		{
			GotoGetQuotePage getquotepg=PageFactory.initElements(driver, GotoGetQuotePage.class);
			return getquotepg;
		}
		else
			return null;
	}
}
