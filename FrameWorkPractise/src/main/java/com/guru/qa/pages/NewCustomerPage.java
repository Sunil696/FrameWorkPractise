package com.guru.qa.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.guru.qa.base.TestBase;
import com.guru.qa.util.TestUtil;
import com.guru.qa.util.Xls_Reader;

public class NewCustomerPage extends TestBase {

	@FindBy(name = "name")
	WebElement custName;
	@FindBy(xpath = "//table/tbody/tr[5]/td/input[1]")
	@CacheLookup
	WebElement male;
	@FindBy(name = "//input[@type='radio' and @value='f']")
	WebElement female;
	@FindBy(name = "dob")
	WebElement dateOfBirth;
	@FindBy(name = "addr")
	WebElement address;
	@FindBy(name = "city")
	WebElement city;
	@FindBy(name = "state")
	WebElement state;
	@FindBy(name = "pinno")
	WebElement pinNo;
	@FindBy(name = "telephoneno")
	WebElement mobileNo;
	@FindBy(name = "emailid")
	WebElement mailId;
	@FindBy(name = "password")
	WebElement password;
	@FindBy(name = "sub")
	WebElement submit;
	@FindBy(name = "res")
	WebElement reset;

	@FindBy(xpath = "//p[contains(text(),'Customer ')]")
	WebElement confirmationMessage;

	@FindBy(xpath = "//*[@id='customer']/tbody/tr[4]/td[2]")
	WebElement custId;

	public NewCustomerPage() {
		PageFactory.initElements(driver, this);
	}

	public String toValidateNewCustomePagTitle() {
		TestUtil.explicitWaitforTitle(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Guru99 Bank New Customer Entry Page", "not a vaid login");
		return driver.getTitle();
	}

	public void toCreateNewCustomer(String lName, String lgender, String ldob, String laddr, String lcity, String lstate, String lpinNo,
			String lmobileNo, String lmailId, String lpassword) {
		try {
			custName.sendKeys(lName);
			selectGender(lgender);
			dateOfBirth.sendKeys(ldob);
			address.sendKeys(laddr);
			city.sendKeys(lcity);
			state.sendKeys(lstate);
			pinNo.sendKeys(lpinNo);
			mobileNo.sendKeys(lmobileNo);
			mailId.sendKeys(lmailId);
			password.sendKeys(lpassword);
			 submit.click();
			 TestUtil.explicitWaitforElement(confirmationMessage);
		} catch (UnhandledAlertException e) {
			e.printStackTrace();
			System.out.println("Alert while creating the customer  id:" + e);
		}
	}

	public void verifyConfirmationTitle() {
		String msg = confirmationMessage.getText();
		if (msg.contentEquals("Customer Registered Successfully!!!")) {
			System.out.println("Customer id is created ");
		} else
			System.out.println("Customer is not created Successfully !!!!!");
	}

	public String captureNewCustid() {
		String cusid = custId.getText();
		System.out.println("Customer id is:" + cusid);
		return cusid;
	}

	public void selectGender(String gender) {
		List<WebElement> radioButtons = driver.findElements(By.name("rad1"));
		Iterator<WebElement> ele = radioButtons.iterator();
		while (ele.hasNext()) {
			WebElement element = ele.next();
			String genderValue = element.getAttribute("value");
			if (genderValue.equalsIgnoreCase(gender)) {
				element.click();
				break;
			}
		}
	}
}
