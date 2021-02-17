package com.guru.qa.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.guru.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long Page_Load_TimeOut = 30;
	public static long Implicit_Wait = 20;

	public static Xls_Reader reader;

	// Constructor to call the parent class constructor
	public TestUtil() {
		super();

	}

	// To fetch the cell data of a sheet
	public static ArrayList<Object[]> toCreateNewCustomer() {
		ArrayList<Object[]> newCustomer = new ArrayList<Object[]>();

		try {
			reader = new Xls_Reader(
					"C:\\SeleniumPractise\\FrameWorkPractise\\src\\main\\java\\com\\guru\\qa\\testdata\\testData.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!reader.isSheetExist("Accounts")) {
			reader.addSheet("Accounts");
			reader.addColumn("Accounts", "Custids");
		}

		int rowCount = reader.getRowCount("Customers");

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String cName = reader.getCellData("Customers", "cName", rowNum);
			String gender = reader.getCellData("Customers", "gender", rowNum);
			String dob = reader.getCellData("Customers", "dob", rowNum);
			String addr = reader.getCellData("Customers", "addr", rowNum);
			String city = reader.getCellData("Customers", "city", rowNum);
			String state = reader.getCellData("Customers", "state", rowNum);
			String pin = reader.getCellData("Customers", "pin", rowNum);
			String mNo = reader.getCellData("Customers", "mNo", rowNum);
			String email = reader.getCellData("Customers", "email", rowNum);
			String password = reader.getCellData("Customers", "password", rowNum);

			Object obj[] = { cName, gender, dob, addr, city, state, pin, mNo, email, password };
			newCustomer.add(obj);
		}
		return newCustomer;

	}

	public static ArrayList<Object[]> toCreateNewAccount() {
		ArrayList<Object[]> newAcct = new ArrayList<Object[]>();

		try {
			reader = new Xls_Reader(
					"C:\\SeleniumPractise\\FrameWorkPractise\\src\\main\\java\\com\\guru\\qa\\testdata\\testData.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int rowCount = reader.getRowCount("Accounts");

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String cusid = reader.getCellData("Accounts", "Custids", rowNum);
			String actTpe = reader.getCellData("Accounts", "AcctType", rowNum);
			String desit = reader.getCellData("Accounts", "Deposit", rowNum);

			Object obj[] = { cusid, actTpe, desit };
			newAcct.add(obj);
		}
		return newAcct;

	}

	public static void handleAlert() {
		Alert alt = driver.switchTo().alert();
		String altText = alt.getText();
		System.out.println(altText);
		alt.dismiss();
	}

	public static void explicitWaitforElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void explicitWaitforTitle(String ltitle) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(ltitle));

	}

	public static void takeScreenshotAtEndOfTest(String testMethodName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile,
				new File(currentDir + "/screenshots/" + testMethodName + "-" + System.currentTimeMillis() + ".png"));
		// FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" +
		// System.currentTimeMillis() + ".png"));
	}

	public void selectDropDown(WebElement element, String type, String value) {
		Select select = new Select(element);
		switch (type) {
		case " index":
			select.selectByIndex(Integer.parseInt(value));
			break;

		case " visibletext":
			select.selectByValue(value);
			break;

		case " value":
			select.selectByValue(value);
			break;

		default:
			System.out.println("Please pass the correct selection criteria");
			break;

		}
	}

	public static void doSelectVisibleText(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public static void doSelectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public static void doSelectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

}
