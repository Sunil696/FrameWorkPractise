package com.guru.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.guru.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//td[@style='color: green']")
	WebElement manager;

	@FindBy(linkText = "New Customer")
	WebElement newCustomer;

	@FindBy(linkText = "Edit Customer")
	WebElement editCustomer;

	@FindBy(linkText = "Delete Customer")
	WebElement deleteCustomer;

	@FindBy(linkText = "New Account")
	WebElement newAccount;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String toValidateHomePage() {
		return driver.getTitle();
	}

	public String toValidateUser() {
		return manager.getText();
	}

	public NewCustomerPage clickOnNewCustomer() {
		newCustomer.click();
		return new NewCustomerPage();
	}

	public EditCustomerPage clickOnEditCustomer() {
		editCustomer.click();
		return new EditCustomerPage();
	}

	public NewAccountPage clickOnNewAccount() {
		newAccount.click();
		return new NewAccountPage();
	}

}
