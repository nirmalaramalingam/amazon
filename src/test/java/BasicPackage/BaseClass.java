package BasicPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseClass 
{
	public WebDriver driver;
	//Assert assert = new Assert();;

	public static String fwPath  = System.getProperty("user.dir");
	public static String reportPath = fwPath + File.separator + "Reports\\";
	public static String testDataPath = fwPath + File.separator + "TestData\\";
	public static String browserDriverPath = fwPath + File.separator + "BrowserDriver\\";

	public void captureScreenshot() throws IOException
	{
		System.out.println(reportPath);
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		String str=df.format(date)+".png";		
		
		
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(reportPath + str));
		

	}
	@Test(priority =1)
	public void LaunchAmazon() throws IOException
		{
			System.out.println(browserDriverPath);
			//System.setProperty("webdriver.chrome.driver", "D:\\Mobile easylearn\\NewWorkspaceMaven\\com.flipkart\\BrowserDriver\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", browserDriverPath + "\\chromedriver.exe");
			driver = new ChromeDriver();
			captureScreenshot();
			driver.get("http://www.amazon.com");
			captureScreenshot();
		}
	@Test(priority =2)
	public void getTitleAmazon() throws IOException
		{
			
			String actTitle = driver.getTitle();
			System.out.println("Actual title is " + actTitle);
			String expTitle = "www.amazon.com";
			Assert.assertEquals(actTitle, expTitle);
		}
	@Test(priority = 3)
	public void getCurrentUrlAmazon() throws IOException
		{
			String actUrl = driver.getCurrentUrl();
			System.out.println("Actual URL is " + actUrl);
			String expURL = "https://www.amazon.com/";
			Assert.assertEquals(actUrl, expURL);
		}

	}
