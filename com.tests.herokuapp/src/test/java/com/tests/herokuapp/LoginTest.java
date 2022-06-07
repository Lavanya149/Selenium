package com.tests.herokuapp;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends Base{

	@BeforeTest
	public static void Driver_initi() throws IOException {
		driver_init();
	}
	
	@Test(priority=1)
	public static void InvalidUn() {
		
		driver.get("https://the-internet.herokuapp.com/login");
		//Invalid username and valid password
		WebElement username=driver.findElement(By.id("username"));
		username.sendKeys("omsmith");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		
		WebElement login=driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
		login.click();
		
		String expectedValue="Your username is invalid!";
		
		WebElement errormessage=driver.findElement(By.xpath("//div[@id='flash']"));
		String Actual= errormessage.getText();
		
		Assert.assertTrue(Actual.contains(expectedValue));
		
	}
	
	@Test(priority=2)
	public static void InValidPwd() {
		
		driver.get("https://the-internet.herokuapp.com/login");
		//Invalid username and valid password
		WebElement username=driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("ecretPassword!");
		
		WebElement login=driver.findElement(By.className("radius"));
		login.click();
		
		String expectedValue="Your password is invalid!";
		
		WebElement errormessage=driver.findElement(By.xpath("//div[@id='flash']"));
		String Actual= errormessage.getText().trim();
		
		//Assert.assertEquals(Actual,expectedValue);
		Assert.assertTrue(Actual.contains(expectedValue));
		
		
		
	}
	
	@Test(priority=3)
	
	public static void ValidCredentials() {
		driver.get("https://the-internet.herokuapp.com/login");
		//Invalid username and valid password
		WebElement username=driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		
		WebElement login=driver.findElement(By.className("radius"));
		login.click();
		
		WebElement successMessage=driver.findElement(By.xpath("//h4[contains(text(),'Welcome to the Secure Area. When you are done click logout below')]"));
		boolean Display= successMessage.isDisplayed();
		
		Assert.assertTrue(Display);
		
		}
	
@Test(priority=4)
	
	public static void BackroundClr() {
		driver.get("https://the-internet.herokuapp.com/login");
		//Invalid username and valid password
		WebElement username=driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		
		WebElement login=driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
		login.click();
		
		WebElement successMes=driver.findElement(By.className("flash"));
		
		String bckcolr=successMes.getCssValue("background-color");
				
		Assert.assertEquals(bckcolr,"rgba(93, 164, 35, 1)");
		
		
		
		}
	
}