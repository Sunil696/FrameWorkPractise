package com.guru.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guru.qa.base.TestBase;
import com.guru.qa.util.TestUtil;

public class LoginPage extends TestBase {

		//Page Factory 
	@FindBy(name = "uid")
	WebElement userId;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "btnLogin")
	WebElement loginButton;

	//Initilizating the page object
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

		//Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
			// To login to application
		public HomePage  login(String  uname, String pwd) {
			userId.sendKeys(uname);
			password.sendKeys(pwd);
			loginButton.click();
			return new HomePage();
			
		}

}
