package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.qameta.allure.Attachment;







public class BaseTest extends ObjectRepo {//TestBase inherits ObjectRepo, allowing access to its shared objects.{
	BrowserFactory bf = new BrowserFactory();
	/*
	 * Runs before each test method.
       The alwaysRun = true ensures execution even if test groups are used.
	 */
	@BeforeMethod(alwaysRun = true)
	public void TestStartUp() throws Exception {
		/*
		 * Reads the browser type from the config file.
Calls BrowserFactory to initialize the WebDriver.
Retrieves the WebDriver instance from DriverFactory.
		 */
		String browser = PropertiesOperation.getPropertyValue("browser");
		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
		DriverFactory.getInstance();
		WebDriver driver = DriverFactory.getInstance().getDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void TestTearDown() {
		
		
	DriverFactory.getInstance().closeBrowser();
	}
	
	
	public void navigateToUrl(String url) {
        WebDriver driver = DriverFactory.getInstance().getDriver();  // Ensure driver is initialized
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized! Call getDriver() first.");
        }
        driver.navigate().to(url);
    }
	
	public boolean waitUntilElementVisibility(WebElement element) {
		boolean result;
		wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(20));
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Element is not present :" + element);
			result = false;
		}
		return result;
	}
	public void extentLog(Status status, String logDescription) {
		ExtentFactory.getInstance().getExtent().log(status, logDescription);
		File source = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
		Date date = new Date();
		String actualDate = sdf.format(date);
// System.getProperty("user.dir") +
		String screenshotPath = System.getProperty("user.dir") + "/ExecutionReports/Screenshots/Screenshot_"
				+ actualDate + "_" + Math.random() + ".jpeg";
		File destination = new File(screenshotPath);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String path = null;
		try {
			byte[] byteArray = IOUtils.toByteArray(new FileInputStream(screenshotPath));
			path = Base64.getEncoder().encodeToString(byteArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (status.equals(Status.PASS)) {
			ExtentFactory.getInstance().getExtent()
					.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
		} else {
			ExtentFactory.getInstance().getExtent()
					.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
		}
	}
	
	/**
	 * Close login popup if present on the screen.
	 */
	/**
	 * Generic method to close a popup if the provided WebElement is visible
	 */
	public boolean closePopupIfPresent(WebElement popupCloseBtn) {
		boolean result;
		wait = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOf(popupCloseBtn));
			popupCloseBtn.click();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Popup closed successfully -> " + popupCloseBtn);
			result = true;
		} catch (TimeoutException e) {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Popup not displayed -> " + popupCloseBtn);
			result = true; // No popup present, treat as pass
		} catch (Exception e) {
			e.printStackTrace();
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to close Popup -> " + popupCloseBtn);
			result = false;
		}
		return result;
	}
	public void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the label of the selected trip type radio button.
	 */
	public String getSelected(WebElement radioElement, String labelIfSelected, String defaultLabel) {
	    try {
	        if (radioElement.isSelected()) {
	            return labelIfSelected;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Trip type radio not found: " + radioElement);
	    }
	    return defaultLabel;
	}
	 public void waitUntilClickable(WebElement element) {
	        new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(20))
	                .until(ExpectedConditions.elementToBeClickable(element));
	    }

	    public void waitForPageLoad() {
	        ExpectedCondition<Boolean> condition = driver ->
	                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	        new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(30)).until(condition);
	    }
	    public void scrollPage(int pixels) {
			js = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			js.executeScript("window.scrollBy(0," + pixels + ")");
		}
	    public void scrollIntoView(WebElement element) {
	        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	    }
	    
	    public WebDriver getDriver() {
	        return DriverFactory.getInstance().getDriver();
	    }

}