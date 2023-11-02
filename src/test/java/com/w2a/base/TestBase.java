package com.w2a.base;

import org.apache.logging.log4j.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class TestBase {

	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	// public static Logger log = LogManager.getLogger(TestBase.class.getName());

	WebDriver driver;

	@SuppressWarnings("resource")
	@Test
	public void Registration() throws IOException {

		String filepath = "./src/test/resources/properties/Config.properties";

		FileInputStream fis = new FileInputStream(filepath);
		fis = new FileInputStream(filepath);
		Config.load(fis);
		Properties OR = new Properties();
		String filepath1 = "./src/test/resources/properties/OR.properties";
		fis = new FileInputStream(filepath1);
		OR.load(fis);

		if (Config.getProperty("browser").equals("chrome")) {

			// WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			// options.addArguments("--disable notifications");
			DesiredCapabilities cp = new DesiredCapabilities();
			cp.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(cp);
			System.setProperty("Webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get(Config.getProperty("testsiteurl"));

		} else if (Config.getProperty("browser").equals("firefox")) {
			// WebDriverManager.firefoxdriver().setup();
			System.setProperty("Webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(Config.getProperty("testsiteurl"));

		} else if (Config.getProperty("browser").equals("edge")) {
			// WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			DesiredCapabilities cp = new DesiredCapabilities();
			cp.setCapability(EdgeOptions.CAPABILITY, options);
			options.merge(cp);
			System.setProperty("Webdriver.edge.driver", "msedgedriver.exe");
			driver = new EdgeDriver(options);
			driver.manage().window().maximize();
			driver.get(Config.getProperty("testsiteurl"));
		}

	}

	public boolean isElementpresent(By by) {

		try {
			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
