package com.w2a.testcases;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.w2a.base.TestBase;

public class RegistredTestcases extends TestBase {

	private static final Logger logger = LogManager.getLogger(TestBase.class);

	public void launchsetup() throws IOException {
		BasicConfigurator.configure();
		logger.info("Starting your Selenium test...");
		TestBase R1 = new TestBase();
		R1.Registration();
		logger.info("The chrome is launching");

		Assert.assertTrue(isElementpresent(By.xpath(OR.getProperty("Registrationtitle"))),
				"The title of the registration is not visible.");

	}
}
