package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;


public class LoginPageTest extends BaseTest{
		
	@Test(priority = 1)
	public void loginPageTitleTest() {
		
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Page title : "+ actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
		
	}
	
	@Test(priority = 2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());		
	}
	
	@Test(priority = 4)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegistrationLinkExist());
	}
	
	@Test(priority = 5)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String accountPageTitle = accountsPage.getAccountPageTitle();
		Assert.assertEquals(accountPageTitle, Constants.ACCOUNTS_PAGE_TITLE, Errors.ACC_PAGE_TITLE_NOT_FOUND_MESSG);
	}

}
