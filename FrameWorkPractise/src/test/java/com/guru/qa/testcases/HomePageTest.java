package com.guru.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.guru.qa.base.TestBase;
import com.guru.qa.pages.HomePage;
import com.guru.qa.pages.LoginPage;

public class HomePageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;

	HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initilization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		homepage = loginpage.login(prop.getProperty("uname"), prop.getProperty("password"));

	}

	@Test(priority = 1 )
	public void homePageTitleTest() {
		String title = homepage.toValidateHomePage();
		Assert.assertEquals(title, "Guru99 Bank Manager HomePage");
		//System.out.println("sample test");
			}

	@Test(priority = 2)
	public void homePageUserTestt() {
		String title = homepage.toValidateUser();
		Assert.assertEquals(title, "Manger Id : mngr302793", "home page user id  is not matched");
	
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
