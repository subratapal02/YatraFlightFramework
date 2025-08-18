# Yatra.com Flight Booking Automation

This project automates flight booking search on **Yatra.com** using Selenium WebDriver, TestNG, and Allure reports.  
It implements the **Page Object Model (POM)**, supports **parallel execution**, and provides an option to run tests in **headless mode**.

---

## **Features**
- Open Chrome browser in normal or headless mode.
- Close login or signup popups automatically.
- Search for flights (One Way, Round Trip, Multi City).
- Automatically select departure, destination, and travel date.
- Set travelers (Adults, Children, Infants).
- Capture all flight search results.
- Take screenshots and attach to Allure report on success or failure.
- Parallel test execution using TestNG.

---

## **Prerequisites**
- Java JDK 11 or above installed.
- Maven installed.
- Chrome browser installed.
- Eclipse IDE or any Java IDE.
- Internet connection to access Yatra.com.

---

## **Project Setup**
**Clone the repository**
----------------------------
git clone <repository-url>

cd yatra-flight-booking-automation

**Open in Eclipse**
----------------------------
File → Import → Existing Maven Project → Select project folder → Finish

**Execution Instructions:**
----------------------------
**Run Tests in Non-Headless Mode**
----------------------------
Open *applications.properties*

Set parameter headless to false.
headless=false



**Run Tests in Headless Mode**
----------------------------
Open *applications.properties*

Set parameter headless to true.
headless=true

**Parallel Execution**
----------------------------

**Configured in testng.xml:**

<suite name="YatraSuite" parallel="tests" thread-count="3">

Adjust thread-count as needed.

Test Steps Automated
----------------------------
1. Open Chrome browser in non-headless mode.

2. Load the website https://www.yatra.com/
3. Check if any &quot;Login&quot; or &quot;Create an Account&quot; popup is displayed. If showing, close it by
clicking the cross button.

4. Navigate to the Flights tab.

5. Check which option is selected: One Way, Round Trip, or Multi City.

6. If &quot;One Way&quot; is selected, then:

o Click on &quot;Departure From&quot; field

o Type &quot;CCU&quot; and select it from the dropdown

o Click on &quot;Going To&quot; field

o Type &quot;BOM&quot; and select it from the dropdown

7. Choose a date in August where the price is lowest.

8. Click on the &quot;Travellers&quot; section and set:

o 3 Adults

o 2 Children

o 3 Infants

9. Click search and display all results in list format.

10. Take a screenshot of the result and generate an Allure report attaching the screenshot
once the test case is completed. Also, if test fails screenshot should attached with the
allure report.<suite name="YatraSuite" parallel="tests" thread-count="3"><suite name="YatraSuite" parallel="tests" thread-count="3">