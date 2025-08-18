package base;

import org.openqa.selenium.WebDriver;



//Declares a singleton class DriverFactory for WebDriver management.
public class DriverFactory {

	private DriverFactory() {//Defines a private constructor to prevent instantiation from outside

	}
	private static DriverFactory instance = new DriverFactory();//Creates a single static instance of DriverFactory.

	public static DriverFactory getInstance() {//Provides a global access point to get the DriverFactory instance
		return instance;
	}
	//Uses ThreadLocal to store WebDriver instances, ensuring thread safety in parallel test execution.
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public WebDriver getDriver() {//Retrieves the WebDriver instance for the current thread.
		return driver.get();
	}

	public void setDriver(WebDriver driverParam) {//Sets the WebDriver instance for the current thread.
		driver.set(driverParam);
	}

	public void closeBrowser() {//Closes the browser session and removes the WebDriver instance from ThreadLocal.
		driver.get().quit();
		driver.remove();
	}
}