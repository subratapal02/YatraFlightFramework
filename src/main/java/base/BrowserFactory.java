package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BrowserFactory{
	//Declares a public class named BrowserFactory, responsible for creating WebDriver instances.

	//Defines a method createBrowserInstance that takes a browser name as input and returns a WebDriver instance.
	public WebDriver createBrowserInstance(String browser) {
		WebDriver driver = null;//Declares a WebDriver variable but does not initialize it yet.
		
		 // Read headless flag from properties (assume PropertiesOperation exists and works)
        String headless = PropertiesOperation.getApplicationPropertyValue("headless");
        
		if (browser.equalsIgnoreCase("chrome")) {//Checks if the browser input is "chrome".
			//Uses WebDriverManager to download and set up the ChromeDriver.
			//Creates an instance of ChromeOptions, enabling Incognito mode.
			//Initializes driver as a new ChromeDriver instance with those options.
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			////headless
			if ("true".equalsIgnoreCase(headless)) {
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu"); // Optional, recommended for Windows
                chromeOptions.addArguments("--window-size=1920,1080");
            }
			//headless
			
			driver = new ChromeDriver(chromeOptions);
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions foOptions = new FirefoxOptions();
			foOptions.addArguments("-private");
			//headless
			if ("true".equalsIgnoreCase(headless)) {
                //foOptions.setHeadless(true);
				foOptions.addArguments("-headless");
            }
			
			
			driver = new FirefoxDriver(foOptions);
		} else if (browser.equalsIgnoreCase("internetexplorer")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		return driver;//Returns the initialized WebDriver instance.
	}
}
/*
 * Browser setup and management (BrowserFactory, DriverFactory)
 * This class is responsible for creating browser instances.
It uses WebDriverManager to set up drivers for Chrome, Firefox, and Internet Explorer.
Depending on the browser type passed as an argument, it initializes a WebDriver instance with appropriate options.
It enables Incognito mode for Chrome and Private mode for Firefox.
 */
