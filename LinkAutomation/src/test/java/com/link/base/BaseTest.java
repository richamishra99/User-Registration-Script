package com.link.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nglc.utilities.Screenshot;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	//The design structure follows Page object Model : contains BaseTest, LoginLcd Module and a TestPage/LoginPage
	//Design config and locators file seperately for easy maintainability
	//Attached Extent reporting and Screenshot on failure

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties loc = new Properties();

	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;

	@BeforeTest
	public void StartTest() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Automation-Test-Report-" +timeStamp+ ".html";
		String reportPath = System.getProperty("user.dir") + "/src/test/resources/reports/" + reportName;
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			spark = new ExtentSparkReporter(reportPath);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Automation Report");
			spark.config().setReportName("Automation Test Report");
			extent.attachReporter(spark);

		}

	}

	@BeforeMethod
	public void launchBrowser() throws IOException {


		FileInputStream fis_config = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/properties/config.properties");
		config.load(fis_config);


		FileInputStream fis_locator = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/properties/locators.properties");
		loc.load(fis_locator);


		if (config.getProperty("browser").equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (config.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get(config.getProperty("linkurl"));
		driver.manage().window().maximize();

	}




	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			// To add it in the extent report
			String pathScreenshot = Screenshot.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(pathScreenshot);
			test.fail("Test Method Failed is "+result.getMethod());
			test.fail("Test Case Failed is "+result.getThrowable());
			
						
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Case Failed is "+result.getName());
		}

		driver.close(); 
		test.info("Browser Closed");
	}

	@AfterTest
	public static void endTest() {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}

	}

}
