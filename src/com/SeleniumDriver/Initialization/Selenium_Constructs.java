package com.SeleniumDriver.Initialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.SeleniumDriver.ReadFile.ReadProperties;

public class Selenium_Constructs {
	
	
	static WebDriver driver;
	static Properties prop=null;
	
	public static WebDriver startup_Driver(){
		
		FileInputStream in;
		try {
			in = new FileInputStream("config.properties");
			
			prop=new Properties();
			
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.setProperty(prop.getProperty("driver"), prop.getProperty("driverpath"));
		
		
		driver = new ChromeDriver();
		
		driver.get(prop.getProperty("baseurl"));
		
		driver.manage().window().maximize();
		
		return driver;
		
	}

}
