package com.ust.magetnotest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;

public class BaseTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void openBrowser()
	{
		ChromeOptions opt =new ChromeOptions();
		opt.addArguments("--headless");
		driver =new ChromeDriver(opt);
		wait=new WebDriverWait(driver, 57);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void magentoTest() throws IOException  {

		
		
		driver.get("https://magento.com/");
		
		WebElement myAccEle= driver.findElement(By.xpath("//span[text()='Account']/ancestor::a"));
		myAccEle.click();
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@type='text']")));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@type='text']"))));
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("balajidinakaran1@gmail.com");
		
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Welcome123");
		
		driver.findElement(By.xpath("//input[@type='password']")).submit();
		
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Out')]")));
		
		System.out.println(driver.getTitle());
		
		Assert.assertEquals(driver.getTitle(),"My Account");
		
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File file= screenShot.getScreenshotAs(OutputType.FILE);
		String date = new Date().toString().replace(":", "-");
		FileUtils.copyFile(file, new File("./screenshots/Img"+date+".png"));
		
		driver.findElement(By.xpath("//a[contains(text(),'Out')]")).click();

	}
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
	}

}
