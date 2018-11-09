package com.ust.magetnotest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class BaseTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void openBrowser()
	{
		driver =new InternetExplorerDriver();
		wait=new WebDriverWait(driver, 57);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void magentoTest() {

		
		
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
		
		driver.findElement(By.xpath("//a[contains(text(),'Out')]")).click();

	}
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
	}

}
