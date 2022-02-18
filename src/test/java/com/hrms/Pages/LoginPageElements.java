package com.hrms.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.BaseClass.BaseClass;

public class LoginPageElements {
//Page Object Model ----> it is design pattern
	//page object model with page factory
	
	@FindBy(id="txtUsername")
	public WebElement username;
	@FindBy(id="txtPassword")
	public WebElement password;
	@FindBy(xpath="//input[@id='btnLogin']")
	public WebElement loginButton;
	@FindBy(id="spanMessage")
	public WebElement errorMessage;
	
	
	public LoginPageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
	
	
}
