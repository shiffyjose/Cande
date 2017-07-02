package com.carinsurance.page.getquote;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.carinsurance.page.base.Page;
import com.carinsurance.page.quote.RecievedQuote;
import com.carinsurance.util.Constants;

public class GotoGetQuotePage{
	
	public WebDriver driver;	
	public static Properties CONFIG=null;
	
	@FindBy(xpath=Constants.MAKE)	
	public WebElement make;
	
	@FindBy(xpath=Constants.YEAR)	
	public WebElement year;
		
	@FindBy(xpath=Constants.DRIVERS_AGE)	
	public WebElement driver_age;
	
	@FindBy(xpath=Constants.DRIVERS_GENDER_MALE)	
	public WebElement gender_male;
	
	@FindBy(xpath=Constants.DRIVERS_GENDER_FEMALE)	
	public WebElement gender_female;
	
	@FindBy(xpath=Constants.STATE)	
	public WebElement state;
	
	@FindBy(xpath=Constants.EMAIL)	
	public WebElement email;
	
	@FindBy(xpath=Constants.GET_QUOTE_BTN)	
	public WebElement get_quote_btn;
	
	@FindBy(xpath=Constants.YEAR_VALIDATION)
	public WebElement year_validation_msg;
	
	@FindBy(xpath=Constants.YEAR_VALIDATION_INVLD)
	public WebElement year_validation_invld;
	
	@FindBy(xpath=Constants.AGE_VALIDATION)
	public WebElement age_validation_msg;
	
	@FindBy(xpath=Constants.AGE_VALIDATION_INVLD)
	public WebElement age_validation_invld;
	
	@FindBy(xpath=Constants.EMAIL_VALIDATION)
	public WebElement email_validation_msg;
	
	@FindBy(xpath=Constants.EMAIL_VALIDATION_MSG2)
	public WebElement email_validation_msg2;
	
	@FindBy(xpath=Constants.BUY_INS_BTN)
	public WebElement buy_ins_btn;
	
	
	public GotoGetQuotePage(WebDriver dr)
	{
		driver=dr;
		//CONFIG load
		CONFIG=new Properties();				
		FileInputStream Fs;
		try {
			Fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\carinsurance\\config\\CONFIG.properties");
		    CONFIG.load(Fs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void SelectMakeFromDropDown(String Element)
	{
		if(!Element.isEmpty())
		{
			Select Sel=new Select(make);
			Sel.selectByIndex(Integer.valueOf(Element));
		}
		else
			System.out.println("Default value has been taken for the MAKE field");
	}
	
	public void SelectStateFromDropDown(String Element)
	{
		if(!Element.isEmpty())
		{
			Select Sel=new Select(state);
			Sel.selectByIndex(Integer.valueOf(Element));
		}
		else
			System.out.println("Default value has been taken for the STATE field");	
	}
	
	public void EnterYear(String Element)
	{
		if(!Element.isEmpty())
			year.sendKeys(Element);
		
	}
	
	public boolean CheckYear(String Element)
	{
		try{		
		if( (Integer.valueOf(CONFIG.getProperty("Start_date"))<=Integer.valueOf(Element)) && (Integer.valueOf(Element)<=Calendar.getInstance().get(Calendar.YEAR)))
	    	  return true;
	      else
	    	  return false;
		}
		catch(NumberFormatException ex){ // handle your exception
			   return false;
			}
	}
	
	public boolean CheckAge(String Element)
	{
		try{
		if(Integer.valueOf(CONFIG.getProperty("min_age"))<=Integer.valueOf(Element) && (Integer.valueOf(Element)<=Integer.valueOf(CONFIG.getProperty("max_age"))))
			return true;
		else
			return false;
		}
		catch(NumberFormatException ex){ // handle your exception
			   return false;
			}
	}
	
	public boolean CheckEmail(String Element)
	{
		Pattern regexPattern;
	    Matcher regMatcher;	    
		regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(Element);
        if(regMatcher.matches())
        	return true;
        else
            return false;          
	}
	
	public void EnterDriversAge(String Element)
	{
		if(!Element.isEmpty())
			driver_age.sendKeys(Element);
	}
	
	public void EnterDriverGender(String Element)
	{
		if(Element.equals("Male"))
			gender_male.click();
		else
			gender_female.click();
	}
	
	public void EnterEmail(String Element)
	{
		if(!Element.isEmpty())
			email.sendKeys(Element);
	}
	
	public  RecievedQuote ClickGetquote() throws InterruptedException
	{			
		Thread.sleep(1000);
		if(get_quote_btn.isEnabled())
		{
			get_quote_btn.click();
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			Thread.sleep(1000);				
			//if(get_quote_btn.isDisplayed())
			//return null;			
			if(IsElementPresent(buy_ins_btn))			
			{
				RecievedQuote Rcv=PageFactory.initElements(driver,RecievedQuote.class);
				Thread.sleep(1000);			
				return Rcv;				
			}
		}
		else
			return null;		
	
		return null;
	}
	
	public String GetErrorMessage_year()
	{			
		String err_msg_year=year_validation_msg.getText();	
		if(err_msg_year.equals(null))
			err_msg_year=year_validation_invld.getText();
		return err_msg_year;		
	}

	public String GetErrorMessage_email(){
		String err_msg_email=email_validation_msg.getText();
		if(err_msg_email.equals(null))
			err_msg_email=email_validation_msg2.getText();
		return err_msg_email;
	}
	public String GetErrorMessage_age(){
		String err_msg_Age=age_validation_msg.getText();
		if(err_msg_Age.equals(null))
			err_msg_Age=age_validation_invld.getText();
		return err_msg_Age;
	}
	
	public boolean IsElementPresent(WebElement Element)
	{
		try{
			boolean flag=Element.isDisplayed();
			return flag;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
		
	}
}
