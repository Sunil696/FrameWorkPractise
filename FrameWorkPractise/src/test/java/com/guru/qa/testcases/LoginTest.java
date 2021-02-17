package com.guru.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.guru.qa.base.TestBase;
import com.guru.qa.pages.HomePage;
import com.guru.qa.pages.LoginPage;

public class LoginTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initilization();
		loginpage = new LoginPage();
	}

	@Test (priority=1)
	public void loginPageTitleTest() {
		String title = loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "failuretest");
		//Guru99 Bank Home Page
	}
	
	@Test(priority=2)
	public void loginTest()
	{
	homepage=	loginpage.login(prop.getProperty("uname"), prop.getProperty("password"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
