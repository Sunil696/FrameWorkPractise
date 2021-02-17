package com.guru.qa.testcases;



import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guru.qa.base.TestBase;
import com.guru.qa.pages.HomePage;
import com.guru.qa.pages.LoginPage;
import com.guru.qa.pages.NewAccountPage;
import com.guru.qa.util.TestUtil;
import com.guru.qa.util.Xls_Reader;

public class NewAccountTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	NewAccountPage newAccountPage;
	Xls_Reader reader;
	int rowNum=2;

	public NewAccountTest() {
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
		initilization();
		loginpage = new LoginPage();
		homepage = new HomePage();
		loginpage.login(prop.getProperty("uname"), prop.getProperty("password"));
		reader.addColumn("Accounts","AcctId");
	}

	
	@DataProvider()
	public Iterator<Object[]> getTestDataforAccount() {
		ArrayList<Object[]> actData = TestUtil.toCreateNewAccount();
		return actData.iterator();
	
	}

	@Test(dataProvider="getTestDataforAccount")
	public void createNewAccount(String custid, String actType, String deposit) {
		newAccountPage = homepage.clickOnNewAccount();
		newAccountPage.toValidateNewAccountPagTitle();
		String actId=newAccountPage.newAccount(custid ,actType, deposit);
		reader.setCellData("Accounts", "AcctId", rowNum, actId);
		rowNum++;
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
