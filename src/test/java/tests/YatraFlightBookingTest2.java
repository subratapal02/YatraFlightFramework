package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.HomePage;
import pages.HomePage2;
import utils.AllureLogger;

public class YatraFlightBookingTest2 extends BaseTest{

	@Test(description = "Search for flights from CCU to BOM with multiple passengers")
	@Story("Flight Search Functionality")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test to search flights from Kolkata (CCU) to Mumbai (BOM) with 3 Adults, 2 Children, and 1 Infant")
	public void testFlightBooking() {
	    home2 = new HomePage2();

	    try {
	    	home2.navigateToYatra();
	    	home2.closeLoginPopupIfPresent();
	    	home2.verifyLandingPage();
	    	 // ✅ Call POM method to print all results
	        home2.printAllFlightResults();

	    } catch (Exception e) {
	        AllureLogger.logStepWithScreenshots("Test failed with exception: " + e.getMessage(), () -> {});
	        throw new RuntimeException("Test execution failed", e);
	    }
	}

}
