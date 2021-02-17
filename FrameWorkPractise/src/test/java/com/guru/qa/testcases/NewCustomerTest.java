package com.guru.qa.testcases;

import java.util.ArrayList;
import java.util.Iterator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.guru.qa.base.TestBase;
import com.guru.qa.pages.HomePage;
import com.guru.qa.pages.LoginPage;
import com.guru.qa.pages.NewCustomerPage;
import com.guru.qa.util.TestUtil;
import com.guru.qa.util.Xls_Reader;

public class NewCustomerTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	NewCustomerPage newCustomerPage;
	Xls_Reader reader;
	int cellNumber = 2;

	public NewCustomerTest() {
		super();
	}

	@BeforeClass
	public void toReadData() {
		try {
			reader = new Xls_Reader(
					"C:\\SeleniumPractise\\FrameWorkPractise\\src\\main\\java\\com\\guru\\qa\\testdata\\testData.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		reader.addColumn("Customers", "CustomerId");
		initilization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		loginpage.login(prop.getProperty("uname"), prop.getProperty("password"));
	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = TestUtil.toCreateNewCustomer();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void newCustomer(String cName, String gender, String dob, String addr, String city, String state, String pin,
			String mNo, String email, String password) throws InterruptedException {
		newCustomerPage = homepage.clickOnNewCustomer();
		newCustomerPage.toValidateNewCustomePagTitle();
		newCustomerPage.toCreateNewCustomer(cName, gender, dob, addr, city, state, pin, mNo, email, password);
		newCustomerPage.verifyConfirmationTitle();
		String newCustId = newCustomerPage.captureNewCustid();
		System.out.println("row Number is :" + cellNumber);
		reader.setCellData("Customers", "CustomerId", cellNumber, newCustId);
		System.out.println("Customer id is inserted to the excel");
		cellNumber = cellNumber + 1;

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
