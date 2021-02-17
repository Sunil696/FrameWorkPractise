package com.guru.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.guru.qa.util.TestUtil;
import com.guru.qa.util.WebEventListner;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListner eventlistener;

	// Constructor of the Base class to read the property file.
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream( 
					"C:\\SeleniumPractise\\FrameWorkPractise\\src\\main\\java\\com\\guru\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// To initise the values in the propery file
	public static void initilization() {
		String browsername = prop.getProperty("browser");
		WebDriverManager.chromedriver().setup();
		if (browsername.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
			e_driver=		new EventFiringWebDriver(driver);
			eventlistener=	new WebEventListner();
			e_driver.register(eventlistener);
			driver=e_driver;
			
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_TimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Wait, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

}
