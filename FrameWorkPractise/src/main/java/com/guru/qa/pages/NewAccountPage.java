package com.guru.qa.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.guru.qa.base.TestBase;
import com.guru.qa.util.TestUtil;

public class NewAccountPage extends TestBase {

	@FindBy(xpath = "//input[@name='cusid']")
	WebElement custid;

	@FindBy(xpath = "//select[@name='selaccount']")
	WebElement actType;

	@FindBy(xpath = "//input[@name='inideposit']")
	WebElement initial_Deposit;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement submit;

	@FindBy(xpath = "//*[text()='Account Generated Successfully!!!']")
	WebElement confirmationMessage;

	@FindBy(xpath = "//table[@id='account']/tbody/tr[4]/td[2]")
	WebElement acctId;

	// input[@type='submit']
	public NewAccountPage() {
		PageFactory.initElements(driver, this);
	}

	public void toValidateNewAccountPagTitle() {
		TestUtil.explicitWaitforTitle(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Guru99 bank add new account", "not navigated account page");
	}

	public String  newAccount(String lcustid, String lacctType, String lamount)  {
		custid.sendKeys(lcustid);
		toSelectAcctType(lacctType);
		initial_Deposit.sendKeys(lamount);
		 submit.click();
		 TestUtil.explicitWaitforElement(confirmationMessage);
		 String  acctNo = acctId.getText();
		 System.out.println("Account Number is:" + acctNo);
		 return  acctNo;

	}

	public void toSelectAcctType1(String depositType) {
		Select select = new Select(actType);
		List<WebElement> el = select.getOptions();
		System.out.println("Total  number of selections: " +el.size());
		Iterator<WebElement> ele = el.iterator();
		while (ele.hasNext()) {
			WebElement element = ele.next();
			String lvalue = element.getAttribute("value");
			if (lvalue.contentEquals(depositType)) {
				select.selectByValue("Savings");
				System.out.println("Saving is selected");
				break;
			} else {
				select.selectByValue("Current");
				System.out.println("current is selected");
				break;
			}
		}

	}
	
	
	public void toSelectAcctType(String depositType) {
		Select select = new Select(actType);
		List<WebElement> el = select.getOptions();
		System.out.println("Total  number of selections: " +el.size());
		for(WebElement options :el) {
			String lvalue = options.getAttribute("value");
			if (lvalue.contentEquals(depositType)) {
				select.selectByValue("Savings");
				System.out.println("Saving is selected");
				break;
			} else {
				select.selectByValue("Current");
				System.out.println("current is selected");
				break;
			}
		}

	}

}
