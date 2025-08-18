package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseTest;
import base.DriverFactory;
import io.qameta.allure.Step;

public class FlightResultsPage extends BaseTest {
	WebDriver driver;
	@FindBy(xpath = "//div[contains(@class, 'result') or contains(@class, 'flight-details')]")
    private List<WebElement> flightResults;
    
    @FindBy(xpath = "//div[contains(@class, 'list-view') or contains(text(), 'List')]")
    private WebElement listViewButton;
    
    @FindBy(xpath = "//div[contains(@class, 'flight-info') or contains(@class, 'flight-card')]")
    private List<WebElement> flightCards;
    
    public FlightResultsPage() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
    
    @Step("Check if flight results are displayed")
    public boolean areFlightResultsDisplayed() {
        try {
            // Wait for results to load
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'result')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'flight-details')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'flight-card')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'flight-info')]"))
            ));
            
            List<WebElement> results = driver.findElements(
                By.xpath("//div[contains(@class, 'result') or contains(@class, 'flight-details') or contains(@class, 'flight-card')]"));
            
            return !results.isEmpty();
            
        } catch (Exception e) {
            System.out.println("Error checking flight results: " + e.getMessage());
            return false;
        }
    }
    
    @Step("Switch to list view")
    public void switchToListView() {
        try {
            WebElement listView = driver.findElement(
                By.xpath("//div[contains(@class, 'list-view') or contains(text(), 'List') or contains(@class, 'list')]"));
            if (listView.isDisplayed()) {
                listView.click();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("List view already active or not found: " + e.getMessage());
        }
    }
    
    @Step("Get flight results count")
    public int getFlightResultsCount() {
        try {
            List<WebElement> results = driver.findElements(
                By.xpath("//div[contains(@class, 'result') or contains(@class, 'flight-details') or contains(@class, 'flight-card')]"));
            return results.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Step("Verify results page loaded successfully")
    public boolean isResultsPageLoaded() {
        try {
            // Check for common elements that appear on results page
            return wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'search-results')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'flight-list')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Flight')]")),
                ExpectedConditions.urlContains("flight")
            )) != null;
        } catch (Exception e) {
            return false;
        }
    }
}