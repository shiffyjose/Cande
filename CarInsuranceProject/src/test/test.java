package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriver driver=null;
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Chrome\\chromedriver.exe");
		driver=new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);	
		driver.get("http://sydneytesters.herokuapp.com/");
		driver.findElement(By.xpath("//*[@id='getcarquote']")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.xpath("//*[@id='year']")).sendKeys("2145");
		driver.findElement(By.xpath("//*[@id='age']")).sendKeys("35");
		Select sel=new Select(driver.findElement(By.xpath("//*[@id='make']")));
		sel.selectByIndex(1);
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("abc@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='getquote']")).click();
		String data=driver.findElement(By.xpath("//*[@id='premiumResultform']/div[1]/dl/dd")).getText();
		System.out.println(data);
		
		//String yr=driver.findElement(By.xpath("//*[@id='quoteform']/div[2]/div/small[2]")).getText();
		//System.out.println(yr+"\n"+"sds");
		/*Pattern regexPattern;
	     Matcher regMatcher;
	     String emailAddress="abc@gmail.com";
		regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(emailAddress);
        if(regMatcher.matches()) {
            //return "Valid Email Address";
        	System.out.println("valid");
        } else {
            //return "Invalid Email Address";
        	System.out.println("invalid");
        }*/
		
		
	}

}
