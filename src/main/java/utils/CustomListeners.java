package utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.DriverFactory;
import io.qameta.allure.Allure;

public class CustomListeners implements ITestListener {

    private static final String ALLURE_RESULTS_DIR = "allure-results";

    // Common screenshot capture
    private void captureAndAttachScreenshot(String status) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        if (driver != null) {
            try {
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // Save screenshot file (optional: useful for debugging)
                File screenshotFile = new File(ALLURE_RESULTS_DIR,
                        status + "_screenshot_" + System.currentTimeMillis() + ".png");
                Files.write(screenshotFile.toPath(), screenshotBytes);

                // Attach screenshot to Allure
                Allure.addAttachment(status + " Test Screenshot",
                        new ByteArrayInputStream(screenshotBytes));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	// Capture screenshot automatically on failure
        captureAndAttachScreenshot("FAILED");

        // Attach exception message to Allure
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            Allure.step("Test failed with exception: " + throwable.getMessage());
        }
        
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        captureAndAttachScreenshot("Passed");
    }

//    @Override
//    public void onFinish(ITestContext context) {
//        try {
//            // Create unique timestamped report folder
//            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
//            String reportFolder = "allure-report-" + timestamp;
//
//            // Generate report
//            ProcessBuilder pbGenerate = new ProcessBuilder(
//                    "cmd", "/c", "allure generate " + ALLURE_RESULTS_DIR + " --clean -o " + reportFolder);
//            pbGenerate.inheritIO();
//            pbGenerate.start().waitFor();
//
////            // Open report in default browser
////            ProcessBuilder pbOpen = new ProcessBuilder(
////                    "cmd", "/c", "start " + reportFolder + "\\index.html");
////            pbOpen.inheritIO();
////            pbOpen.start();
//            //
//            // Automatically open Allure report after test execution
//            ProcessBuilder pb = new ProcessBuilder(
//                    "cmd", "/c", "allure serve " + ALLURE_RESULTS_DIR);
//            pb.inheritIO();
//            pb.start();
//            
//            
//            //
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    
    //new addded finaly working this one
    @Override
    public void onFinish(ITestContext context) {
        try {
            // Create unique timestamp folder inside project
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportFolder = "allure-report-" + timestamp;

            // Step 1: Generate the report in your project folder
            ProcessBuilder pbGenerate = new ProcessBuilder(
                    "cmd", "/c", "allure generate " + ALLURE_RESULTS_DIR + " --clean -o " + reportFolder);
            pbGenerate.inheritIO();
            pbGenerate.start().waitFor();

            // Step 2: Open the generated report in the browser
            ProcessBuilder pbOpen = new ProcessBuilder(
                    "cmd", "/c", "allure open " + reportFolder);
            pbOpen.inheritIO();
            pbOpen.start();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //

    // Empty unused methods
    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
}