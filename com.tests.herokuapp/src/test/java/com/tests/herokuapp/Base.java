package com.tests.herokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

public class Base {
	
public static WebDriver driver;

	public static void driver_init() {
		System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
		driver=new ChromeDriver();
	}

}
