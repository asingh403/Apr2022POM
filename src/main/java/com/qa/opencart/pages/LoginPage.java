package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;


public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	public LoginPage (WebDriver driver) {
		this.driver = driver;
		eleUtil  = new ElementUtil(driver);
	}
	
	//By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type = 'submit']");
	private By registerLink = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting login page title...")
	public boolean getLoginPageUrl() {
		return eleUtil.waitForUrlToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		//return driver.getCurrentUrl();
	}
	
	@Step("checking the footer link")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("Verifying the Registration links")
	public boolean isRegistrationLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	@Step("Perform the login...")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("login with :: "+ un+" : and "+ pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
		
	}
	@Step("Verify the do login success....")
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("try to login with login with wrong credentials :: "+ un+" : and "+ pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errorMsg = eleUtil.doGetText(loginErrorMsg);
		System.out.println(errorMsg);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MESSGE)) {
			System.out.println("login is not done...");
			return false;
		}
		return true;
	}
	@Step("click on Registartion Page link and perform actions....")
	public RegistrationPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	

}
