package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtils;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void setupRegistration() {
		registrationPage = loginPage.goToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random randomGenerator = new Random();
		String email = "aprautomation"+ randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
		
	}
	
	
	@DataProvider
	public Object [][] getRegisterData() {
		return ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName,
			String telephone, String address1, String city, String postcode, String password, String subscribe, String country, String zone) {
		
		Assert.assertTrue(registrationPage.accountRegistartion(firstName, lastName, getRandomEmail(),
				telephone, address1, city, postcode, country, zone, password, subscribe));
		
	}
	
}
