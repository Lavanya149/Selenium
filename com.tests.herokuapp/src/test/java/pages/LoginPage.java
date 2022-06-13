package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
	}

	By loginPageUsName=By.id("username");
	By loginPagePwd=By.id("password");
	By click=By.xpath("//i[contains(text(),'Login')]");
	By loginErrorMsg=By.id("flash");
		
	public WebElement getUsername() {
		return driver.findElement(loginPageUsName);
	}
	
	public WebElement getPassword() {
		return driver.findElement(loginPagePwd);
	}
	public WebElement clickLogin() {
		return driver.findElement(click);
	}
	public WebElement getErrMsg() {
		return driver.findElement(loginErrorMsg);
	}
}
