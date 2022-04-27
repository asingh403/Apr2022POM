package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		co.addArguments("--start-fullscreen");
		co.addArguments("--disable-blínk-features=AutomationControlled");
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))
			co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
			co.addArguments("--incognito");
		
		return co;
	}
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		fo.addArguments("--start-fullscreen");
		if(Boolean.parseBoolean(prop.getProperty("headless")))
			co.addArguments("--headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
			co.addArguments("--incognito");
		
		return fo;
	}

}
