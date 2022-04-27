package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	/*********** Config.properties *******/
	public static final String PROD_CONFIG_FILE_PATH = "./src/test/resources/config/config.properties";
	public static final String QA_ENV_PATH = "./src/test/resources/config/qa.config.properties";
	public static final String UAT_ENV_PATH = "./src/test/resources/config/uat.config.properties";
	public static final String STAGE_ENV_PATH = "./src/test/resources/config/stage.config.properties";
	public static final String DEV_ENV_PATH = "./src/test/resources/config/dev.config.properties";
	
	/******* WAIT TIME ********/
	public static final int DEFAULT_TIME_OUT = 5;
	
	
	/*********** Login Page *******/
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	//public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login1"; //making fail
	public static final String LOGIN_ERROR_MESSGE = "Warning: No match for E-Mail Address and/or Password.";
	
	
	/*********** Account Page *******/
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String MY_ACCOUNT = "Your Store";
	//public static final String ACCOUNT_PAGE_HEADER = "Your Store11"; // making fail

	/*********** ProductInfo Page ***********/ 
	public static final int IMAC_IMAGE_COUNT = 3;
	public static final int MAC_BOOK_PRO_IMAGE_COUNT = 3;
	public static final int MAC_BOOK_AIR_IMAGE_COUNT = 4;
	
	public static final String SEARCH_NAME_MACBOOK = "MacBook";
	public static final String SEARCH_NAME_MACBOOK_PRO = "MacBook Pro";
	public static final String PRODUCT_BRAND = "Apple";
	public static final String PRODUCT_PRICE = "$2,000.00";
	

	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";

		public static List<String> getExpectedAccSecList() {
		List<String> expSecList = new ArrayList<>();
		expSecList.add("My Account");
		expSecList.add("My Orders");
		//expSecList.add("My Affiliate Account"); //in http://opencart.antropy.co.uk/ showing 3 items
		expSecList.add("Newsletter");
		
		return expSecList;
		
	}
	
	/*********** TestData Sheet Name ***********/ 
	public static final String REGISTER_SHEET_NAME = "registration";
		

}