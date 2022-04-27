package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void productHeaderTest() {
		searchResultPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}
	
	@Test(priority = 2)
	public void productImagesCountTest() {
		searchResultPage = accountsPage.doSearch("iMac");
		productInfoPage = searchResultPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCounts(), Constants.IMAC_IMAGE_COUNT);
		
	}
	
	@Test(priority = 3)
	public void productInfoTest() {
		searchResultPage = accountsPage.doSearch(Constants.SEARCH_NAME_MACBOOK);
		productInfoPage = searchResultPage.selectProduct(Constants.SEARCH_NAME_MACBOOK_PRO);
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k+ " : "+v));
		softAssert.assertEquals(actProductInfoMap.get("name"), Constants.SEARCH_NAME_MACBOOK_PRO);
		softAssert.assertEquals(actProductInfoMap.get("Brand"), Constants.PRODUCT_BRAND);
		softAssert.assertEquals(actProductInfoMap.get("price"), Constants.PRODUCT_PRICE);
		softAssert.assertAll();
		
	}
	
}
