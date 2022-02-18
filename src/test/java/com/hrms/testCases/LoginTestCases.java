package com.hrms.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hrms.BaseClass.BaseClass;
import com.hrms.Pages.LoginPageElements;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigReader;
import com.hrms.utils.ExcelUtility;

public class LoginTestCases extends BaseClass {

	@Test(groups= {"smoke"})
	public void validCerdentails() {
		LoginPageElements loginpage = new LoginPageElements();
		CommonMethods.SendData(loginpage.username, ConfigReader.getProperty("Username"));
		CommonMethods.SendData(loginpage.password, ConfigReader.getProperty("Password"));
		CommonMethods.clickAction(loginpage.loginButton);
		String actualTitle = BaseClass.driver.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, "OrangeHRM", "titles are not matching");
	 
	
	}
	
	@Test(groups= {"sanity"})
	public void inValidUsername() {
		LoginPageElements loginpage = new LoginPageElements();
		CommonMethods.SendData(loginpage.username, "Admin2345");
		CommonMethods.SendData(loginpage.password, ConfigReader.getProperty("Password"));
		CommonMethods.clickAction(loginpage.loginButton);
		String actualText = loginpage.errorMessage.getText();
		Assert.assertEquals(actualText, "Invalid credentials", "error message did not matched");
	}
	@Test(groups= {"sanity"})
	public void inValidPassword() {
		LoginPageElements loginpage = new LoginPageElements();
        CommonMethods.SendData(loginpage.username, ConfigReader.getProperty("Username"));
		CommonMethods.SendData(loginpage.password, "Java1234");
		CommonMethods.clickAction(loginpage.loginButton);
		String actualText = loginpage.errorMessage.getText();
		Assert.assertEquals(actualText, "Invalid credentials", "error message did not matched");
	}

	
	@Test(dataProvider="InvalidLoginData",groups= {"regression"})
	public void dataDrivenTest(String Username,String Password,String ErrorMessage) {
		LoginPageElements loginpage = new LoginPageElements();
        CommonMethods.SendData(loginpage.username, Username);
		CommonMethods.SendData(loginpage.password, Password);
		CommonMethods.clickAction(loginpage.loginButton);
		String actualText = loginpage.errorMessage.getText();
		Assert.assertEquals(actualText, ErrorMessage, "error message did not matched");
	}

	
	@DataProvider(name="InvalidLoginData")
	public Object[][] getData(){
		return ExcelUtility.excelIntoArray("C:\\Users\\Pranitha Regalla\\Desktop\\TestData.xlsx", "Sheet2");
	}
}
