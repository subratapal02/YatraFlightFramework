package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import base.DriverFactory;
import base.ExtentFactory;
import base.ExtentReportSetup;



public class ListernersImplementation implements ITestListener{
	static ExtentReports report;
		   ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(test);
	}

	public void onTestSuccess(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test case: " + result.getMethod().getMethodName() + " is Passed");
		File source = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
		Date date = new Date();
		
		String actualDate = sdf.format(date);
		String screenshotPath = "/Reports/Screenshots/" + actualDate + "_"+Math.random()+".png";
		File destination = new File(screenshotPath);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = null;
		try {
			byte[] byteArray = IOUtils.toByteArray(new FileInputStream(screenshotPath));
			 path = Base64.getEncoder().encodeToString(byteArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentFactory.getInstance().getExtent().pass("Screenshot Attached",MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
			//test.addScreenCaptureFromPath(screenshotPath, "Test case success screenshot");
			 //ExtentFactory.getInstance().removeExtentObject();
	}

	public void onTestFailure(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test case: " + result.getMethod().getMethodName() + " is Failed");
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());
		File source = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
		Date date = new Date();
		
		String actualDate = sdf.format(date);
		String screenshotPath = "/Reports/Screenshots/" + actualDate + "_"+Math.random()+".png";
		File destination = new File(screenshotPath);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = null;
		try {
			byte[] byteArray = IOUtils.toByteArray(new FileInputStream(screenshotPath));
			 path = Base64.getEncoder().encodeToString(byteArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentFactory.getInstance().getExtent().fail("Screenshot Attached",MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
			//ExtentFactory.getInstance().removeExtentObject();
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		report = ExtentReportSetup.ExtentSetup();
	}

	//@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}