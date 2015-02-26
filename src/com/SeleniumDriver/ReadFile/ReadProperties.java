package com.SeleniumDriver.ReadFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	static Properties prop=null;
	
	public static void readProperty(){
		
		try {
			FileInputStream in= new FileInputStream("config.properties");
			
			prop=new Properties();
			
			prop.load(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}
	
	
	

}
