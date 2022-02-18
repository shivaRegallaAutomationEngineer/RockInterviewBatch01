package com.hrms.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hrms.Pages.LoginPageElements;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigReader;

public class AddCustomerTestCases {
	@Test(groups = { "sanity" })
	public void addCustomer() {
		LoginPageElements loginpage = new LoginPageElements();
		CommonMethods.SendData(loginpage.username, "Admin2345");
		CommonMethods.SendData(loginpage.password, ConfigReader.getProperty("Password"));
		CommonMethods.clickAction(loginpage.loginButton);
		String actualText = loginpage.errorMessage.getText();
		Assert.assertEquals(actualText, "Invalid credentials", "error message did not matched");
	}

}
