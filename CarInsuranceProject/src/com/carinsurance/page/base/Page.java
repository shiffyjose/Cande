package com.carinsurance.page.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.carinsurance.util.Xls_Reader;

public class Page {
	
	public static WebDriver driver=null;
	public static Xls_Reader xls=null;
	
		
	public Page()
	{
		if(driver==null)
		{			
			xls=new com.carinsurance.util.Xls_Reader(System.getProperty("user.dir")+"\\Resource\\TestData.xlsx");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Chrome\\chromedriver.exe");
			driver=new ChromeDriver();		}				
		
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);		
		driver.get("http://sydneytesters.herokuapp.com/");			
	}
	
	public void quit()
	{
		driver.close();
	}
	
}
