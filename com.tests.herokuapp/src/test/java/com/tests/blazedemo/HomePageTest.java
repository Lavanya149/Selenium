package com.tests.blazedemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tests.herokuapp.Base;

public class HomePageTest {
	
	@BeforeTest
	public static void InitDriver() throws IOException {
		Base.driver_init();
	}

	@Test
	public static void TC1_Welcome() {
		Base.driver.get("https://blazedemo.com/");
		Boolean con=Base.driver.findElement(By.xpath("//h1[contains(text(),'Welcome to the Simple Travel Agency!')]")).isDisplayed();
		Assert.assertTrue(con);
		
		}
	@Test
	public static void TC2_Dept() {
				
		WebElement ele=Base.driver.findElement(By.xpath("//body/div[3]/form[1]/select[1]"));
		Select dropdown=new Select((ele));
		List<WebElement> lis=dropdown.getOptions();
		List OrgList=new ArrayList();
		for(WebElement options:lis) {
			System.out.println(options.getText());
			OrgList.add(options.getText());
		}
		List templist=new ArrayList(OrgList);
		Collections.sort(templist);
		System.out.println("Templist: "+templist);
		System.out.println("OrginalList"+OrgList);
		
		Assert.assertEquals(OrgList, templist);
	}
	@Test
	public static void Tc3_Destn() {
	//Base.driver.get("https://blazedemo.com/");
	WebElement ele=Base.driver.findElement(By.xpath("//body/div[3]/form[1]/select[2]"));
	ele.click();
	Select dropdown=new Select((ele));
	List<WebElement> lis=dropdown.getOptions();
	
	List OrgList1=new ArrayList();
	
	for(WebElement options:lis) {
		System.out.println(options.getText());
		OrgList1.add(options.getText());
	}
	
	List templist=new ArrayList(OrgList1);
	Collections.sort(templist);
	System.out.println("Templist_Assending: "+templist);
	Collections.reverse(templist);
	System.out.println("Templist_Descending: "+templist);
	System.out.println("OrginalList"+OrgList1);
	
	Assert.assertEquals(OrgList1, templist);

}
	@Test
	public static void findFlights() {
		WebElement findflights=Base.driver.findElement(By.xpath("//body/div[3]/form[1]/div[1]/input[1]"));
		boolean b1=findflights.isEnabled();
		Assert.assertFalse(b1);

	}
}
