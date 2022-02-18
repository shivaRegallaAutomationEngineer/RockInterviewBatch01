package com.hrms.BaseClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hrms.utils.ConfigReader;
import com.hrms.utils.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReport;
	public static ExtentReports report;
	public static ExtentTest test;
  @BeforeTest(alwaysRun = true)
	public void generateReport() { 
	  ConfigReader.readProperties(Constants.CONFIGS_FILEPATH);

		htmlReport = new ExtentHtmlReporter(Constants.REPORT_FILEPATH);
		htmlReport.config().setDocumentTitle(ConfigReader.getProperty("reportTitle"));
		htmlReport.config().setReportName(ConfigReader.getProperty("reportName"));
		htmlReport.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(htmlReport);
	}
@AfterTest(alwaysRun = true)
	public void writeReport() {
		report.flush();
	}

	@BeforeMethod(alwaysRun = true)
	public static void openBrowser() {
		ConfigReader.readProperties(Constants.CONFIGS_FILEPATH);
		
		switch (ConfigReader.getProperty("browserName")) {
		case "chrome":
            WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "Edge":
			WebDriverManager.chromedriver().setup();
			break;
		default:
			throw new RuntimeException("Invalid browser name");
		}

		driver.get(ConfigReader.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	
	  @AfterMethod(alwaysRun=true)
	  public void tearDown() {
		  driver.quit();
	  }
}
