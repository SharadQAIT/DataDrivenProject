package com.w2a.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	public static void main(String[] args) throws IOException  
	   {
		   
		   String filepath= "./src/test/resources/properties/Config.properties"; 
		   String filepath1= "./src/test/resources/properties/OR.properties"; 

		   //System.out.println(System.getProperty("user.dir")); // It's project path
		   Properties Config=new Properties();
	   	   FileInputStream fis=new FileInputStream(filepath);
	   	   Config.load(fis);    // Config file loaded
		   System.out.println(Config.getProperty("browser"));
		
		   Properties OR=new Properties();
	   	   fis=new FileInputStream(filepath1);
	   	   OR.load(fis);    // Config file loaded
		   System.out.println(OR.getProperty("RFNtxtbox"));
		   
	   }
}
