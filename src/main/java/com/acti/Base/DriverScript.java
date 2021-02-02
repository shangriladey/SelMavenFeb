package com.acti.Base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class DriverScript {
	public static WebDriver driver;
	public static Properties prop;
	public DriverScript(){
		try{
			File src = new File("./actiConfigFile/config.properties");
			FileInputStream fis = new FileInputStream(src);
		    prop = new Properties();
			prop.load(fis);
		}
		catch(Exception e){
			System.out.println("Unable to upload file"+e.getMessage());
		}
	}
	@Test
	public static void initApp(){
		String url = prop.getProperty("url");
		String browser=prop.getProperty("browser");
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "./actiBrowsers/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "./actiBrowsers/geckodriver.exe");
			driver= new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		
		
	}

}
