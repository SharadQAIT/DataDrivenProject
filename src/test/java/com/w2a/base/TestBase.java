package com.w2a.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.excel.utility.Xls_Reader;

public class TestBase {

	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	// private static final Logger logger = LogManager.getLogger(TestBase.class);

	public static WebDriver driver;

	@SuppressWarnings({ "resource", "deprecation" })
	public void Login() throws IOException

	{

		String filepath = "./src/test/resources/Properties/Config.properties";

		FileInputStream fis = new FileInputStream(filepath);
		fis = new FileInputStream(filepath);
		Config.load(fis);
		Properties OR = new Properties();
		String filepath1 = "./src/test/resources/Properties/OR.properties";
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
			System.out.println("Chrome Browser Successfully launch..");
			// logger.info("Starting your Selenium test...");
			driver.get(Config.getProperty("testsiteurl"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			System.out.println("Omani Staging URL successfully luanch..");
			// logger.info("The chrome is launching");

			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			WebElement userName = driver.findElement(By.id(OR.getProperty("Email_id")));
			WebElement pwd = driver.findElement(By.id(OR.getProperty("Pwd_id")));

			Xls_Reader reader = new Xls_Reader("D:\\Eclipse Workspace\\Selenium_Tutorials\\DataDrivenFramework2\\src\\test\\resources\\excel\\SampleExcel.xlsx");
			String sheetName = "login";

			int rowCount = reader.getRowCount(sheetName);

			for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
				String loginId = reader.getCellData(sheetName, "Email_id", rowNum);
				String passsword = reader.getCellData(sheetName, "Pwd_id", rowNum);

				System.out.println(loginId + " " + passsword);

				userName.clear();
				userName.sendKeys(loginId);

				pwd.clear();
				pwd.sendKeys(passsword);
			}

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

	/*
	 * 
	 * public boolean presenceOfElementLocated(By by) {
	 * 
	 * try { driver.findElements(by); System.out.println("The element is presence");
	 * return true;
	 * 
	 * } catch (NoSuchElementException e) { return false; } }
	 * 
	 *
	 */

}
