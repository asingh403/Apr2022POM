package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By productsResults = By.cssSelector("div.caption a");
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getProductListCount() {
		int resultCount = eleUtil.waitForElementsToBeVisible(productsResults, 10, 2000).size();
		System.out.println("The search product count :: "+ resultCount);
		return resultCount;
	}
	
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("main product name is :: "+ mainProductName);
		List<WebElement>  searchList = eleUtil.waitForElementsToBeVisible(productsResults, 10, 2000);
		
		for(WebElement e: searchList) {
			String text = e.getText();
			if(text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
