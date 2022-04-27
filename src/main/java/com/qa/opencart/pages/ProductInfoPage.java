package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private ElementUtil eleUtil;
	
	private By productHeader  = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	
	private Map<String, String> productInMap;
	
	
	public ProductInfoPage(WebDriver driver) {
		eleUtil = new ElementUtil(driver);
	}	
	
	public String getProductHeader() {
		String productHeaderText = eleUtil.doGetText(productHeader);
		System.out.println("Product header is :: "+ productHeaderText);
		return productHeaderText;
	}
	
	public int getProductImagesCounts() {
		return eleUtil.waitForElementsToBeVisible(productImages, 10).size();
	}
	
	public Map<String, String> getProductInfo() {
		productInMap = new LinkedHashMap<String, String>();
		productInMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productInMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: Out Of Stock
		
		for(WebElement e:metaDataList) {
			String text = e.getText();
			String [] meta = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			
			productInMap.put(metaKey, metaValue);
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
//		$2,000.00
//		Ex Tax: $2,000.00
		
		String price = metaPriceList.get(0).getText().trim();
		String exprice = metaPriceList.get(1).getText().trim();
		productInMap.put("price", price);
		productInMap.put("ExTaxPrice", exprice);
		
	}

}
