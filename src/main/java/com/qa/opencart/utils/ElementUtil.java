package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public By getBy(String locatorType, String locatorValue) {

		By locator = null;

		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;

		default:
			System.out.println("Please pass the right locator type and value .....");
			break;
		}

		return locator;

	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;

	}

	public WebElement getElement(By locator, int timeout) {
		return doPresenceOfElementLocated(locator, timeout);

	}

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);

	}

	public WebElement getElement(String locatorType, String locatorValue) {

		return driver.findElement(getBy(locatorType, locatorValue));

	}
	
	public void doClear(By locator) {
		getElement(locator).clear();
	}

	public void doSendKeys(By locators, String value) {
		doClear(locators);
		getElement(locators).sendKeys(value);

	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {

		getElement(locatorType, locatorValue).sendKeys(value);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}

	public void doSendKeys(By locator, String value, int timeout) {
		doPresenceOfElementLocated(locator, 10).sendKeys(value);
	}

//	public  WebElement getElement(By locator) {
//		return driver.findElement(locator);
//	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean isElementExist(By locator) {
		int elementCount = getElements(locator).size();
		// int elementCount = driver.findElements(locator).size();
		System.out.println("total element Count " + elementCount);

		if (elementCount >= 1) {
			System.out.println("element is found ... " + locator);
			return true;
		} else {
			System.out.println("element is NOT found ... " + locator);
			return false;
		}

	}

	// getElement already created

//	public  WebElement getElement(By locator) {
//		return driver.findElement(locator);
//	}

	public void doDropDownSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));

		select.selectByIndex(index);
	}

	public void doDropDownSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));

		select.selectByVisibleText(text);
	}

	public void doDropDownSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));

		select.selectByVisibleText(value);
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);

			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void dropDownValueWithoutSelect(By locators, String value) {
		List<WebElement> countryList = driver.findElements(locators);

		System.out.println("Total number of Countries :: " + countryList.size());

		for (WebElement e : countryList) {
			System.out.println(e.getText());

			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	/***************** Link Utils ****************/

	public List<String> getLinksText(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> linksTextList = new ArrayList<>();

		for (WebElement e : eleList) {
			String text = e.getText().trim();
			linksTextList.add(text);
		}
		return linksTextList;
	}

	public void clickOnElementFromSection(By locator, String linkText) {
		List<WebElement> langList = getElements(locator);

		for (WebElement e : langList) {
			String text = e.getText().trim();
			System.out.println(text);
			if (text.contains(linkText)) {
				e.click();
				break;
			}
		}
	}

	/***************** WebTable Utils ****************/

	public void printTable(By rowLocators, By colLocators, String beforeXpath, String afterXpath) {
		int rowCount = getElements(rowLocators).size();
		int colCount = getElements(colLocators).size();
		for (int row = 2; row <= rowCount; row++) {
			for (int col = 1; col <= colCount; col++) {
				String xpath = beforeXpath + row + afterXpath + col + "]";
				// System.out.println(xpath);

				String eleText = driver.findElement(By.xpath(xpath)).getText(); // need to maintain using "doGetText"
																				// method
				System.out.print(eleText + " ");
			}
		}

	}

	/******** wait utils handling *********/

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 */

	public WebElement doPresenceOfElementLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement doPresenceOfElementLocated(By locator, int timeout, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	
	
	public WebElement waitForElementToBeVisible(By locator, int timeout, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	public  List<WebElement> waitForElementsToBeVisible(By locator, int timeout, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	
	public WebElement waitForElementToBeVisibleWithWebElement(By locator, int timeout, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));

	}
	
	
	public List<String> getElementsTextListWithWait(By locator, int timeout) {
		
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeout);
		List<String> eleTextList = new ArrayList<>();
		for(WebElement e:eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		
		return eleTextList;
		
	}
	
	/********** Wait on Non-WebElement ************/
	public Boolean waitForUrlToContain(String fractions, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlContains(fractions));
		
	}
	
	public Boolean waitForUrlToBe(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlToBe(url));
		
	}
	
	
	/********** Wait on title ************/
	public String doGetTitleWithFraction(String titleFraction, int timeout) {
		if(waitForTitleContains(titleFraction, timeout)) {
			return driver.getTitle();
		}
		return null;
	}
	
	public String doGetTitle(String title, int timeout) {
		if(waitForTitleToBe(title, timeout)) {
			return driver.getTitle();
		}
		return null;
	}
	
	public boolean waitForTitleContains(String titleFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.titleContains(titleFraction));
		
	}
	
	public boolean waitForTitleToBe(String titleFraction, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.titleIs(titleFraction));
		
	}

	
	/********** Wait on Frame/s ************/
	
	
	public void waitForFrameByNameOrId(String nameOrID, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));
	}
	
	public void waitForFrameByIndex(int frameIndex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public void waitForFrameByLocator(By frameLocator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameByElement(WebElement frameElement, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	public void clickElementWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public void clickElementWhenReady(By locator, int timeout, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(intervalTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public WebElement waitForElementPresntUsingFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage(Errors.ELEMENT_NOT_FOUND_ERROR_MSG);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	
	public WebElement waitForElementUsingWebDriverWait(By locator, int timeOut, int pollingTime) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait
		.pollingEvery(Duration.ofSeconds(pollingTime))
		.ignoring(NoSuchElementException.class)
		.ignoring(StaleElementReferenceException.class)
		.withMessage(Errors.ELEMENT_NOT_FOUND_ERROR_MSG);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	/********************** Custom wait time **********/
	
	
	public WebElement retryingElement(By locator, int timeout) {
		
		WebElement element = null;
		int attempts = 0;
		
		while(attempts < timeout) {
			
			try {
				element = getElement(locator);
				break;
			}
			catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt : "+ attempts + " : "+ locator);
				try {
					Thread.sleep(500);	
				} catch (InterruptedException e1) {
					System.out.println(e1);
				}				
			}
			attempts++;			
		}
		
		if(element == null) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("element is not found exception ... tried for : "+ timeout + 
									" with the interval of : "+ 500 + "ms");
			}
		}
		return element;		
	}
	
	
	public WebElement retryingElement(By locator, int timeout, long pollingTime) {
		
		WebElement element = null;
		int attempts = 0;
		
		while(attempts < timeout) {
			
			try {
				element = getElement(locator);
				break;
			}
			catch (NoSuchElementException e) {
				System.out.println("element is not found in attempt : "+ attempts + " : "+ locator);
				try {
					Thread.sleep(pollingTime);	
				} catch (InterruptedException e1) {
					System.out.println(e1);
				}				
			}
			attempts++;			
		}
		
		if(element == null) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("element is not found exception ... tried for : "+ timeout + 
									" with the interval of : "+ pollingTime + "ms");
			}
		}
		return element;		
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
		
}
