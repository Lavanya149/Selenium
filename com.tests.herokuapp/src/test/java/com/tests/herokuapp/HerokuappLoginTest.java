package com.tests.herokuapp;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;

public class HerokuappLoginTest extends Base{

	@BeforeTest
	public static void Driver_initi() throws IOException {
		Base.driver_init();
	}
	
	@Test(priority=1)
	public void InvalidUn() throws IOException {
		LoginPage lp=new LoginPage(driver);
		
		List<String> login_data=readExcel("Login");
		
		driver.get("https://the-internet.herokuapp.com/login");
		//Invalid username and valid password
		/*WebElement username=driver.findElement(By.id("username"));
		username.sendKeys("omsmith");*/
		WebElement username=lp.getUsername();
		username.sendKeys(login_data.get(2));
				
		/*WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");*/
		WebElement password=lp.getPassword();
		password.sendKeys(login_data.get(3));
		
		/*WebElement login=driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
		login.click();*/
		
		WebElement login=lp.clickLogin();
		login.click();
		
		String expectedErrMsg="Your username is invalid!";
		
		/*WebElement errorMessage=driver.findElement(By.xpath("//div[@id='flash']"));
		String actualMsg= errorMessage.getText();*/
		
		WebElement errMsg=lp.getErrMsg();
		String actualMsg=errMsg.getText();
		
		Assert.assertTrue(actualMsg.contains(expectedErrMsg));
		
	}
	//Invalid username and valid password
	@Test(priority=2)
	public static void InValidPwd() {
		LoginPage lp=new LoginPage(driver);
		driver.get("https://the-internet.herokuapp.com/login");				
		/*WebElement username=driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");*/
		WebElement username= lp.getUsername();
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