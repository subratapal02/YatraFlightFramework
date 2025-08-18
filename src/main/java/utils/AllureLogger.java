package utils;
import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import base.DriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class AllureLogger {

    @Step("{stepDescription}")
    public static void logStep(String stepDescription) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot - " + stepDescription,
                    new ByteArrayInputStream(screenshot));
        }
    }
    /**
     * Log a step with screenshots BEFORE and AFTER executing the action.
     * This provides full visual context for debugging.
     *
     * @param stepDescription Description of the step
     * @param action          The lambda containing the step code
     */
    public static void logStepWithScreenshots(String stepDescription, Runnable action) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            // BEFORE action screenshot
            if (driver != null) {
                byte[] before = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(stepDescription + " - BEFORE", new ByteArrayInputStream(before));
            }

            // Execute the step
            action.run();

            // AFTER action screenshot
            if (driver != null) {
                byte[] after = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(stepDescription + " - AFTER", new ByteArrayInputStream(after));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Allure.step("Step failed: " + stepDescription + " Exception: " + e.getMessage());
        }
    }
}