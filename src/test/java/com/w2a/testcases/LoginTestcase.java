package com.w2a.testcases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class LoginTestcase extends TestBase {

	@Test(priority = 0)
	public void launchbrowser() throws IOException, InterruptedException {
		TestBase R1 = new TestBase();
		R1.Login();

	}
}

