package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void accPageTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		System.out.println("Account Page title : "+ actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void accPageHeaderTest() {
		String header = accountsPage.getAccountPageHeader();
		System.out.println("Account Page header : "+ header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
	}
	
	@Test(priority = 3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	@Test(priority = 4)
	public void accPageSectionsTest() {
		List<String> accAccSecList = accountsPage.getAccountSecList();
		Assert.assertEquals(accAccSecList, Constants.getExpectedAccSecList());
	}
	
	@DataProvider
	public Object [][] productData() {
		return new Object[][] {
			{"MacBook Pro"},
			{"Apple"},
			{"samsung"},
		};
	}
	
	@Test(priority = 5, dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultPage.getProductListCount()>0);
	}
	
	@DataProvider
	public Object [][] productSelectData() {
		return new Object[][] {
			{"MacBook Pro", "MacBook Pro"},
			{"iMac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	@Test(priority = 6, dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		searchResultPage = accountsPage.doSearch(productName);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		String actualProduct = productInfoPage.getProductHeader();
		Assert.assertEquals(actualProduct, mainProductName);
	}
	
}
