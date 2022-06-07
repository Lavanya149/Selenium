package com.tests.herokuapp;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Base {
	
public static WebDriver driver;


	public static void driver_init() throws IOException {
		Base b=new Base();
		
		if(b.readProperty("browser").equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		if(b.readProperty("browser").equalsIgnoreCase("Edge")) {
			
			System.setProperty("webdriver.edge.driver","src\\main\\resources\\chromedriver.exe");
			driver=new EdgeDriver();
		}
		
		if(b.readProperty("browser").equalsIgnoreCase("Firefox")) {
			
			System.setProperty("webdriver.gecko.driver","src\\main\\resources\\chromedriver.exe");
			driver=new FirefoxDriver();
		}
		
	}

	public String readProperty(String key) throws IOException{
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\configs\\data.properties");
		Properties pros=new Properties();
		pros.load(fis);
		System.out.println("reading from file"+pros.getProperty(key));
		return pros.getProperty(key);
	}
	
	public List<String> readExcel(String module) throws IOException {
		List<String> data_container = new ArrayList<String>();
		String path=System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Exceldriven.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		System.out.println("number of sheets is " + workbook.getNumberOfSheets());
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {

			XSSFSheet sheet = workbook.getSheetAt(i);
			String sheetName = sheet.getSheetName();
			if (sheetName.equalsIgnoreCase("System1")) {
				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("Modules")) {
						coloumn = k;
						System.out.println("The modules present in " + k + "th column");

					}

					k++;
				}
				
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(module)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {

							Cell c = cv.next();
							CellType type = c.getCellType();

							switch (type) {

							case NUMERIC: // numeric value in excel

								data_container.add(Double.toString(c.getNumericCellValue()));
								System.out.println(Double.toString(c.getNumericCellValue()));
								break;

							case STRING: // String Value in Excel
								data_container.add(c.getStringCellValue());
								System.out.println(c.getStringCellValue());
								break;
							default:
								System.out.println("EMpty cell ");
							}

						}
					}

				}

			}
		}
		return data_container;
	}
				

}
