package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.utils.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name is : " + browserName);

		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		} else if (browserName.equals("safari")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the right browser :: " + browserName);
		}

		//getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		//openUrl(prop.getProperty("url"));
		
		URL url;
		try {
			url = new URL(prop.getProperty("url"));
			openUrl(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}		
		return getDriver();
	}

	/**
	 * getDriver(): it will return a thread local copy of WebDriver
	 */

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env"); // QA/dev/stage/uat

		if (envName == null) {
			System.out.println("Running on PROD env: "+ Constants.PROD_CONFIG_FILE_PATH);
			try {
				ip = new FileInputStream(Constants.PROD_CONFIG_FILE_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on environment : +" + envName);

			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream(Constants.QA_ENV_PATH);
					break;
				case "dev":
					ip = new FileInputStream(Constants.DEV_ENV_PATH);
					break;
				case "stage":
					ip = new FileInputStream(Constants.STAGE_ENV_PATH);
					break;
				case "UAT":
					ip = new FileInputStream(Constants.UAT_ENV_PATH);
					break;

				default:
					System.out.println("Please pass the right environment.....");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * take screenshot
	 */
	
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * launch url
	 * @param url
	 */
	public void openUrl(String url) {
		try {
			if(url == null) {
				throw new Exception("url is null");
			}			
		}catch(Exception e) {
			
		}		
		getDriver().get(url);
	}
	
	public void openUrl(URL url) {
		try {
			if(url == null) {
				throw new Exception("url is null");
			}			
		}catch(Exception e) {
			
		}		
		getDriver().navigate().to(url);
	}
	
	public void openUrl(String baseUrl, String path) {
		try {
			if(baseUrl == null) {
				throw new Exception("baseUrl is null");
			}			
		}catch(Exception e) {
			
		}
		//https:amazon.in/acctpage/users.html
		getDriver().get(baseUrl+"/"+path);
	}
	
	public void openUrl(String baseUrl, String path, String queryParam) {
		try {
			if(baseUrl == null) {
				throw new Exception("baseUrl is null");
			}			
		}catch(Exception e) {
			
		}
		//https:amazon.in/acctpage/users.html?fn="ashu" &&ln = "sngh"
		getDriver().get(baseUrl+"/"+path+"?"+queryParam);
	}
	
}
