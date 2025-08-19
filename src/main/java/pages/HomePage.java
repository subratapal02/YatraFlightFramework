package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;

import base.BaseTest;
import base.DriverFactory;
import base.ExtentFactory;
import base.PropertiesOperation;
import io.qameta.allure.Step;

public class HomePage extends BaseTest{
	WebDriver driver;
	// Page Elements
	
	@FindBy(xpath="//p[@title='New Delhi']")
	private WebElement defaultvalue;
	
	//Enter "CCU" into "Departure From"
	@FindBy(xpath="//input[@id='input-with-icon-adornment']")
	private WebElement departurefrom;
	
	//Click on "Kolkata, (CCU)"
	@FindBy(xpath="//div[contains(text(),'Kolkata, (CCU)')]")
	private WebElement kolata;
	
	//Click on "mumbai"
	@FindBy(xpath="//p[contains(@title,'Mumbai')]")
	private WebElement mumbai;
	
	//Enter "BOM" into "Going To"
	@FindBy(xpath="//input[@id='input-with-icon-adornment']")
	private WebElement going;
	
	//Click on "Mumbai, (BOM)"
	@FindBy(xpath="//div[contains(text(),'Mumbai, (BOM)')]")
	private WebElement bom;
	
	@FindBy(xpath="//li[contains(.,'3')]")
	private WebElement adult3;
	@FindBy(xpath="//div[contains(@class,'MuiStack-root css-1yhvumz')]//div[2]//div[1]//ul[1]//li[3]")
	private WebElement child2;
	@FindBy(xpath="//div[@aria-label='travellers_card_section0-infant-group']//li[@aria-label='Select age 1'][normalize-space()='1']")
	private WebElement infant1;
	@FindBy(xpath="//button[normalize-space()='Done']")
	private WebElement done;

	@FindBy(xpath="//img[@alt=\"yatraLogo\"]")
	private WebElement yatra;
	
    @FindBy(xpath = "//span[contains(@class,'style_cross')]//img[@alt='cross']")
    private WebElement loginPopupCloseButton;
    
    @FindBy(xpath = "//span[normalize-space()='Flights']")
    private WebElement flightsTab;
    
 // Input box for Departure
    @FindBy(xpath = "//label[normalize-space()='Departure From']/following::input[1]")
    private WebElement departureFromField;

    // Dropdown option for CCU / Kolkata
    @FindBy(xpath = "//div[contains(text(),'Kolkata, (CCU)')]")
    private WebElement departureCCUOption;
    

 // Input box for Destination
    @FindBy(xpath = "//label[normalize-space()='Going To']/following::input[1]")
    private WebElement goingToField;

    // Dropdown option for BOM / Mumbai
    @FindBy(xpath = "//div[contains(text(),'Mumbai, (BOM)')]")
    private WebElement goingToBOMOption;
    

    
    @FindBy(xpath = "//input[contains(@class, 'trip-type') and @checked='checked']/following-sibling::label")
    private WebElement selectedTripType;
    
    @FindBy(xpath = "//span[contains(.,'Economy')]")
    private WebElement travellersSection;
    
    @FindBy(xpath = "//button[contains(@class, 'increment') and contains(@onclick, 'adult')]")
    private WebElement adultIncrement;
    
    @FindBy(xpath = "//button[contains(@class, 'increment') and contains(@onclick, 'child')]")
    private WebElement childIncrement;
    
    @FindBy(xpath = "//button[contains(@class, 'increment') and contains(@onclick, 'infant')]")
    private WebElement infantIncrement;
    
    @FindBy(xpath = "//button[contains(@class, 'decrement') and contains(@onclick, 'adult')]")
    private WebElement adultDecrement;
    
    @FindBy(xpath = "//button[contains(@class, 'decrement') and contains(@onclick, 'child')]")
    private WebElement childDecrement;
    
    @FindBy(xpath = "//button[contains(@class, 'decrement') and contains(@onclick, 'infant')]")
    private WebElement infantDecrement;
    
    @FindBy(xpath = "//div[@class='css-w7k25o']")
    private WebElement departureDateField;
    
    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchFlightsButton;
    
    @FindBy(xpath = "//input[@value='O']")
    private WebElement oneWayRadio;

    @FindBy(xpath = "//input[@value='R']")
    private WebElement roundTripRadio;

    @FindBy(xpath = "//input[@value='M']")
    private WebElement multiCityRadio;
    
    @FindBy(xpath = "//div[@class='viewport']/div/li")  // dropdown items
    private List<WebElement> dropdownOptions;
 // Dropdown option lists for dynamic travellers
    @FindBy(xpath = "//p[@aria-label='Adult']/following-sibling::div//ul/li")
    private List<WebElement> adultOptions;

    @FindBy(xpath = "//p[@aria-label='Child']/following-sibling::div//ul/li")
    private List<WebElement> childOptions;

    @FindBy(xpath = "//p[@aria-label='Infant']/following-sibling::div//ul/li")
    private List<WebElement> infantOptions;
    
    
    @FindBy(xpath = "//td[contains(@class,'date') or contains(@class,'MuiPickersDay-root')]")
    private List<WebElement> allDates;

    @FindBy(xpath = "//td[contains(@class,'MuiPickersDay-root')]//div[contains(@class,'lowest-price')]")
    private List<WebElement> lowestPriceDates;
//    @FindBy(xpath = "//div[@class=\"full-width wrapper\"]")
//    private List<WebElement> resultList; 
    
    @FindBy(xpath = "//div[contains(@class, 'flightItem') and contains(@class, 'border-shadow') and contains(@class, 'pr')]")
    private List<WebElement> resultList; 
  


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements for this driver
    }
    
    @Step("Navigate to Yatra.com")
    public void navigateToYatra() {
    	navigateToUrl(PropertiesOperation.getPropertyValue("applicationUrl"));
    	sleep(1000);
    }
    
    @Step("Close login popup if present")
    public void closeLoginPopupIfPresent() {
    	waitUntilElementVisibility(loginPopupCloseButton);
    	sleep(1000);
    	closePopupIfPresent(loginPopupCloseButton);
    }
    @Step("Verify Page open Yatra.com")
    public void verifyLandingPage() {
		waitUntilElementVisibility(yatra);
		sleep(1000);
    	
	}
    
    @Step("Click on Flights tab")
    public void clickFlightsTab() {
    	waitUntilElementVisibility(flightsTab);
    	flightsTab.click();
    	sleep(2000);
        
    }
    
    
    @Step("Get selected trip type")
    public String getSelectedTripType() {
        if (getSelected(oneWayRadio, "One Way", "").equals("One Way"))
            return "One Way";
        if (getSelected(roundTripRadio, "Round Trip", "").equals("Round Trip"))
            return "Round Trip";
        if (getSelected(multiCityRadio, "Multi City", "").equals("Multi City"))
            return "Multi City";
        return "Unknown";
    }
    //
	 public void enterTripCities(String fromCode, String toCode) {
	        // Departure
		 
		    defaultvalue.click();
		    sleep(1000);
		    departurefrom.click();
		    sleep(1000);
		    departurefrom.sendKeys(fromCode);
		    sleep(1000);
//		    kolata.click();
		    waitUntilElementVisibility(departureCCUOption);
		    sleep(1000);
		    departureCCUOption.click();
		    sleep(1000);
	       

	        // Destination
		    mumbai.click();
		    sleep(1000);
		   
		    going.sendKeys(toCode);
	        waitUntilElementVisibility(goingToBOMOption);
	        sleep(1000);
	        goingToBOMOption.click();
	        sleep(1000);
	    }
	
    
    @Step("Select lowest price date in August")
    public void selectLowestPriceDate() {
        try {
            WebDriver driver = DriverFactory.getInstance().getDriver();

            // 1️⃣ Click Departure Date field to open calendar
            WebElement departureDateField = driver.findElement(By.xpath("//div[@class='css-w7k25o']"));
            System.out.println("1:"+departureDateField);
            waitUntilElementVisibility(departureDateField);
            departureDateField.click();
            sleep(1000);

            // 2️⃣ Find month container for August
            WebElement augustMonthContainer = driver.findElement(By.xpath(
                "//span[text()='August 2025']/ancestor::div[contains(@class,'react-datepicker__month-container')]//div[contains(@class,'react-datepicker__month')]"
            ));
            System.out.println("2:"+augustMonthContainer);

            // 3️⃣ Get all day elements inside August that are not disabled
            List<WebElement> allDays = augustMonthContainer.findElements(By.xpath(
                ".//div[contains(@class,'react-datepicker__day') and not(contains(@class,'react-datepicker__day--disabled'))]"
            ));
            System.out.println("3:"+allDays);

            int lowestPrice = Integer.MAX_VALUE;
            WebElement lowestPriceDay = null;

            for (WebElement day : allDays) {
                try {
                    WebElement priceSpan = day.findElement(By.xpath(".//span[contains(@class,'custom-day-content')]"));
                    System.out.println("4"+priceSpan);
                    String priceText = priceSpan.getText().replaceAll("[^0-9]", "");
                    if (priceText.isEmpty()) continue;

                    int price = Integer.parseInt(priceText);
                    if (price < lowestPrice) {
                        lowestPrice = price;
                        lowestPriceDay = day;
                    }
                } catch (Exception e) {
                    // Skip days without price
                }
            }

            if (lowestPriceDay != null) {
                lowestPriceDay.click();
                ExtentFactory.getInstance().getExtent().log(Status.PASS,
                        "Selected lowest price date in August: ₹" + lowestPrice);
            } else {
                ExtentFactory.getInstance().getExtent().log(Status.INFO,
                        "Lowest price date not found – skipping date selection");
            }

        } catch (Exception e) {
            e.printStackTrace();
            ExtentFactory.getInstance().getExtent().log(Status.FAIL,
                    "Failed to select lowest price date in August");
        }
    }

    @Step("Set travellers: Adults, Children, Infants")
    public void setTravellers2() {
        try {
            // 1️⃣ Click on Travellers section
            waitUntilElementVisibility(travellersSection);
            travellersSection.click();
            sleep(500);
            System.out.println("Click on Travellers section");
            waitUntilElementVisibility(adult3);
            adult3.click();
            waitUntilElementVisibility(child2);
            child2.click();
            waitUntilElementVisibility(infant1);
            infant1.click();
            waitUntilElementVisibility(done);
            done.click();
         
            
        } catch (Exception e) {
            e.printStackTrace();
            ExtentFactory.getInstance().getExtent().log(Status.FAIL,
                    "Failed to set travellers");
        }
    }
 
    // ---------- Travellers ----------
    private void selectFromOptions(List<WebElement> options, int value) {
        for (WebElement option : options) {
            if (option.getText().equals(String.valueOf(value))) {
                option.click();
                break;
            }
        }
    }

    
    @Step("Set travellers: Adults, Children, Infants")
    public void setTravellers(int adults, int children, int infants) {
        try {
            waitUntilElementVisibility(travellersSection);
            travellersSection.click();
            sleep(500);

            selectFromOptions(adultOptions, adults);
            selectFromOptions(childOptions, children);
            selectFromOptions(infantOptions, infants);

            waitUntilElementVisibility(done);
            done.click();
            sleep(500);

            System.out.println("Travellers set: Adults-" + adults + ", Children-" + children + ", Infants-" + infants);
        } catch (Exception e) {
            e.printStackTrace();
            ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Failed to set travellers");
        }
    }
    
    
    @Step("Click Search Flights button")
    public void clickSearchFlightsButton() {
        try {
            WebDriver driver = DriverFactory.getInstance().getDriver();
            
            // Ensure button is visible
            waitUntilElementVisibility(searchFlightsButton);
            sleep(2000);

            // Click via JS executor to avoid overlay issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchFlightsButton);
            sleep(9000); // wait for page load

            // Check if protected page or CAPTCHA appears
            if (driver.getPageSource().toLowerCase().contains("protected") ||
                driver.getPageSource().toLowerCase().contains("captcha")) {
                ExtentFactory.getInstance().getExtent().log(Status.WARNING,
                    "Yatra blocked automation: Protected/CAPTCHA page detected.");
                System.out.println("Automation blocked: Protected/CAPTCHA page detected.");
            } else {
                ExtentFactory.getInstance().getExtent().log(Status.PASS,
                    "Search button clicked successfully, results page loaded.");
            }

        } catch (Exception e) {
            ExtentFactory.getInstance().getExtent().log(Status.FAIL,
                "Error clicking Search Flights button: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Step("Click search flights button")
    public void clickSearchFlights() {
        try {
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Search']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
            
            // Wait for results page to load
            Thread.sleep(9000);
            
        } catch (Exception e) {
            System.out.println("Error clicking search button: " + e.getMessage());
        }
    }
//display all results
    public void printAllFlightResults() throws InterruptedException {
        Thread.sleep(10000); // wait for results to load; better use explicit wait

        System.out.println("Total flights found: " + resultList.size());

        for (WebElement flight : resultList) {
            System.out.println(flight.getText());
            System.out.println("----------------------------------------------------");
        }
    }
    /**
     * Helper method to select dropdown option by value safely
     */
    private void selectFromDropdown(String value) {
        try {
            if (!dropdownOptions.isEmpty()) {
                for (WebElement option : dropdownOptions) {
                    if (option.getText().contains(value)) {
                        option.click();
                        break;
                    }
                }
            } else {
                ExtentFactory.getInstance().getExtent()
                        .log(Status.FAIL, "Dropdown options not found for value: " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExtentFactory.getInstance().getExtent()
                    .log(Status.FAIL, "Error selecting dropdown value: " + value);
        }
    }
 
    public WebElement getFlightsTab() {
        return flightsTab;
    }

}
