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

import utils.AllureLogger;

public class YatraFlightBookingTest extends BaseTest{
//	
//	 @Test(description = "Search for flights from CCU to BOM with multiple passengers")
//	    @Story("Flight Search Functionality")
//	    @Severity(SeverityLevel.CRITICAL)
//	    @Description("Test to search flights from Kolkata (CCU) to Mumbai (BOM) with 3 Adults, 2 Children, and 3 Infants")
//	    public void testFlightBooking() {
//		 home = new HomePage();
//		 
//	        try {
//	            // Step 1-2: Navigate to Yatra.com
//	            home.navigateToYatra();
//	            
//	            System.out.println("navigateToYatra!");
//	            // Step 3: Handle login popup if present
//	            home.closeLoginPopupIfPresent();
//	            System.out.println("closeLoginPopupIfPresent");
//	            
//	            // 3) Verify landing page is opened
//	            home.verifyLandingPage();
//
//	            sleep(2000);   
//
//	            // 3.1) Scroll to Flights tab (so it becomes visible/clickable)
//	            home.scrollIntoView(home.getFlightsTab());   // <-- add getter in HomePage
//	            sleep(1000);
//
//	            // 4) Click on Flights tab
//	            home.clickFlightsTab();
//	            sleep(500);
//	        
//	            System.out.println("clickFlightsTab");
//	            
//	           
//	            home.enterTripCities("CCU", "BOM");
//	            
//	           
//	            System.out.println("\"CCU\", \"BOM\"");
//	            // Optional: If you want to pick a date and click search you can add:
//	            // home.selectDepartureDate("30/08/2024");
//	            // home.clickSearchFlights();
//
//	            System.out.println("Test finished successfully!");
//	            sleep(500);
////	         
//	         // Step 7: Choose lowest price date in August
//	            home.selectLowestPriceDate();
//	         // 7️⃣ Set travellers: 3 Adults, 2 Children, 3 Infants
//	            home.setTravellers(3,2,1);
//	            System.out.println("Set travellers");
//	            // 8️⃣ Click Search Flights
//	            sleep(2000);
//	            home.clickSearchFlightsButton();
//	            sleep(10000);
//	            System.out.println("Click Search Flights");
//	            // Optional: Log a message in Extent Reports
//	            home.extentLog(Status.INFO, "Flight search initiated successfully.");
////	            
//	        } catch (Exception e) {
////	            // Take screenshot on failure
////	            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, "flight_search_failure");
////	            Allure.addAttachment("Test Failure Screenshot", 
////	                new ByteArrayInputStream(ScreenshotUtils.getScreenshotAsBytes(driver)));
////	            
////	            Allure.step("Test failed with error: " + e.getMessage());
////	            throw new RuntimeException("Test execution failed", e);
//	        }
//	    }
	@Test(description = "Search for flights from CCU to BOM with multiple passengers")
	@Story("Flight Search Functionality")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test to search flights from Kolkata (CCU) to Mumbai (BOM) with 3 Adults, 2 Children, and 1 Infant")
	public void testFlightBooking() {
	    home = new HomePage();

	    try {
	        // Step 1-2: Navigate to Yatra.com
	        AllureLogger.logStepWithScreenshots("Navigate to Yatra.com", () -> home.navigateToYatra());
	        System.out.println("navigateToYatra!");

	        // Step 3: Handle login popup if present
	        AllureLogger.logStepWithScreenshots("Close login popup if present", () -> home.closeLoginPopupIfPresent());
	        System.out.println("closeLoginPopupIfPresent");

	        // Step 3.1: Verify landing page is opened
	        AllureLogger.logStepWithScreenshots("Verify landing page opened", () -> home.verifyLandingPage());
	        sleep(2000);

	        // Step 4: Scroll to Flights tab
	        AllureLogger.logStepWithScreenshots("Scroll to Flights tab", () -> home.scrollIntoView(home.getFlightsTab()));
	        sleep(1000);

	        // Step 5: Click on Flights tab
	        AllureLogger.logStepWithScreenshots("Click Flights tab", () -> home.clickFlightsTab());
	        sleep(500);
	        System.out.println("clickFlightsTab");

	        // Step 6: Enter trip cities
	        AllureLogger.logStepWithScreenshots("Enter trip cities CCU -> BOM", () -> home.enterTripCities("CCU", "BOM"));
	        System.out.println("\"CCU\", \"BOM\"");

	        // Step 7: Select lowest price date in August
	        AllureLogger.logStepWithScreenshots("Select lowest price date in August", () -> home.selectLowestPriceDate());

	        // Step 8: Set travellers: 3 Adults, 2 Children, 1 Infant
	        AllureLogger.logStepWithScreenshots("Set travellers 3 Adults, 2 Children, 1 Infant", () -> home.setTravellers(3, 2, 1));
	        System.out.println("Set travellers");
	        sleep(500);

	        // Step 9: Click Search Flights
	        AllureLogger.logStepWithScreenshots("Click Search Flights", () -> home.clickSearchFlightsButton());
	        sleep(10000);
	        System.out.println("Click Search Flights");

	        // Step 10: Log in Extent Reports
	        AllureLogger.logStepWithScreenshots("Log flight search initiation in Extent Reports", 
	            () -> home.extentLog(Status.INFO, "Flight search initiated successfully."));
	        
	        //  display all results
	        AllureLogger.logStepWithScreenshots("All Flights List", () -> {
				try {
					home.printAllFlightResults();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
	        sleep(10000);
	        

	        System.out.println("Test finished successfully!");

	    } catch (Exception e) {
	        AllureLogger.logStepWithScreenshots("Test failed with exception: " + e.getMessage(), () -> {});
	        throw new RuntimeException("Test execution failed", e);
	    }
	}

}
