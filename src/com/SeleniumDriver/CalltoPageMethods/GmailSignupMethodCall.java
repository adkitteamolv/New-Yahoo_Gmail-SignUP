package com.SeleniumDriver.CalltoPageMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.SeleniumDriver.Model.MainForm;
import com.SeleniumDriver.Pages.GmailSignUp;
import com.SeleniumDriver.ReadFile.FileReader;

public class GmailSignupMethodCall {
	
	WebDriver driver= null;
	WebElement element=null;
	
	public GmailSignupMethodCall(WebDriver driver){
		this.driver=driver;
		
	}
	
	public void callGmailSignupMethod(){
		
		Properties prop=null;
		
		GmailSignUp gsignup= new GmailSignUp(driver);
		gsignup.clickGmailLink();
		gsignup.createAnAccount();
		
		FileInputStream in;
		try {
			in = new FileInputStream("config.properties");
			
			 prop = new Properties();
			
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// Sign up data entry
		List<MainForm> mainForms = FileReader
				.readCsvData(prop.getProperty("csvpath"));
		for(MainForm form : mainForms){
				
		gsignup.enterFirstName(form.getFirstName());
		gsignup.enterLastName(form.getLastName());
		gsignup.enterEmailAddress(form.getUserName());
		gsignup.enterPassword(form.getPassword());
		gsignup.enterConfirmPassword(form.getConfirmPassword());
		
		// Select Month Value
		element=gsignup.enterBirthMonth();
		element.click();
		
		List<WebElement> allOptions = element.findElements(By.xpath(".//div[@class='goog-menuitem']"));
		
		List<WebElement> li= element.findElements(By.tagName("div"));
		Iterator<WebElement> i = allOptions.iterator();
		System.out.println(allOptions.size());
		
		while (i.hasNext()) {
			WebElement el = (WebElement) i.next();
			
			System.out.println(el.getText());
			
			String str1= form.getMonth().toString();
			
			if (el.getText().equals(str1)){
				
				el.click();
			}
			
		}
		
		
		gsignup.enterDay(form.getDay());
		gsignup.enterYear(form.getYear());
		
		
		//Select Gender Value
		
		element=gsignup.selectGenderValue();
		element.click();
		
		String str = form.getGender().toLowerCase().toString();
		System.out.println(str);
		List<WebElement> gen = element.findElements(By.xpath(".//div[@class='goog-menuitem']"));
		
		//List<WebElement> lg= element.findElements(By.tagName("div"));
		Iterator<WebElement> ig = gen.iterator();
		System.out.println(gen.size());
		
		while (ig.hasNext()) {
			WebElement el = (WebElement) ig.next();
			
			System.out.println(el.getText());
			
			String str1= form.getGender().toString();
			
			if (el.getText().equals(str1)){
				
				el.click();
			}
			
		}
	
		
		
		gsignup.selectCountry();
		gsignup.enterPhoneNumber(form.getMobile());
		gsignup.enterRecoveryEmail(form.getCurrentEmail());
		gsignup.clickSkipCaptcha();
		gsignup.clickTermsOfService();
		
		// Click on next Step
		/*gsignup.clickNextStepButton();
		
		gsignup.clickOnContionueButton();*/
		
		}
		
		driver.quit();
	}

}
