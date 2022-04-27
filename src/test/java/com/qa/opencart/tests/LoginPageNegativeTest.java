package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] loginWrongData() {
		return new Object[][] {
			{"asingh.403@gmail.com", "test@123"},
			{"test@hotmail.com", "test@3456#"},
			{"asingh_test@yahoo.in", "Test@345$"},
			{"asingh_403@aol.com", "Test@8887*"},
		};
	}
	
	@Test(priority = 1, dataProvider = "loginWrongData")
	public void loginNegativeTest(String username, String password) {
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(username, password));
	}

}
