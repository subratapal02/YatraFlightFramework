package pages;

import java.text.SimpleDateFormat;
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

public class HomePage2 extends BaseTest{
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
    @FindBy(xpath = "//div[@class=\"full-width wrapper\"]")
    private List<WebElement> resultList; // change from WebElement to List<WebElement>
    
    //
    public HomePage2() {
		PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
	}
    
    
    
    // ----------------- Page Actions -----------------
    @Step("Navigate to Yatra.com")
    public void navigateToYatra() {
        navigateToUrl("https://www.yatra.com"); // or from Properties
        sleep(1000);
    }

    @Step("Close login popup if present")
    public void closeLoginPopupIfPresent() {
        sleep(1000);
        closePopupIfPresent(loginPopupCloseButton);
    }

    @Step("Verify landing page")
    public void verifyLandingPage() {
        waitUntilElementVisibility(yatra);
    }
    
    public void printAllFlightResults() throws InterruptedException {
        Thread.sleep(10000); // wait for results to load; better use explicit wait

        System.out.println("Total flights found: " + resultList.size());

        for (WebElement flight : resultList) {
            System.out.println(flight.getText());
            System.out.println("----------------------------------------------------");
        }
    }

  

    

   
    
   
    
    
    

}
