package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private ElementUtil eleUtil;

	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class = 'radio-inline'])[position()=1]/input[@type= 'radio']");
	private By subscribeNo = By.xpath("(//label[@class = 'radio-inline'])[position()=2]/input[@type= 'radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.cssSelector("input.btn.btn-primary");
	private By sucessMsg = By.cssSelector("div#content h1");
	private By country_list = By.xpath("//select[@id = 'input-country']");
	private By zone_list = By.xpath("//select[@id = 'input-zone']");
	private By address1 = By.id("input-address-1");
	private By reg_city = By.id("input-city");
	
	private By postalcode = By.id("input-postcode");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		eleUtil = new ElementUtil(driver);
	}

	public boolean accountRegistartion(String firstName, String lastName, String email, String telephone, String address1, String city, String postal, String country, String zone, String password, String subscribe) {
		
		eleUtil.doSendKeys(this.firstname, firstName);
		eleUtil.doSendKeys(this.lastname, lastName);
		eleUtil.doSendKeys(this.email, email);
		
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.address1, address1);
		eleUtil.doSendKeys(this.reg_city, city);
		eleUtil.doSendKeys(postalcode, postal);
		
		
		
		eleUtil.doDropDownSelectByVisibleText(country_list, country);
		eleUtil.doDropDownSelectByVisibleText(zone_list, zone);
		
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		
		if(subscribe.equals("yes")) {
			eleUtil.doClick(subscribeYes);
		}else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		String mesg = eleUtil.waitForElementToBeVisible(sucessMsg, 5, 1000).getText();
		
		if(mesg.contains(Constants.REGISTER_SUCCESS_MESSG)) {
			
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
