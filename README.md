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
----------------------------
Download project zip folder and extract file

**Open in Eclipse**
----------------------------
File → Import → Existing Maven Project → Select project folder → Finish

**Execution Instructions:**
----------------------------
**Run Tests in Non-Headless Mode**
----------------------------
**Headless Mode Execution Video**
[https://youtu.be/FOHy2zkn2pw](https://youtu.be/8-DgIeJCfyE)

Open *applications.properties*

Set parameter headless to false.
headless=false
<img width="547" height="573" alt="image" src="https://github.com/user-attachments/assets/42f60cff-2616-4824-81b2-194fe03a1542" />



**Run Tests in Headless Mode**
----------------------------
Open *applications.properties*

Set parameter headless to true.
headless=true
<img width="536" height="555" alt="image" src="https://github.com/user-attachments/assets/c87c0369-241d-4a53-8bf5-a3049f3ff821" />


**Parallel Execution**
----------------------------
**Parallel Execution Video**
https://youtu.be/FOHy2zkn2pw

**Parallel Execution repoet sccrenshot**
<img width="1918" height="575" alt="image" src="https://github.com/user-attachments/assets/d54d402e-0113-483f-b561-297577edcfd4" />

**Configured in testng.xml:**

<suite name="YatraSuite" parallel="tests" thread-count="3">

Adjust thread-count as needed.
<img width="691" height="302" alt="image" src="https://github.com/user-attachments/assets/3f3c1b58-8ad6-4720-8550-1c23beca4bce" />



**Allure reports**
-------------------
<img width="1915" height="971" alt="image" src="https://github.com/user-attachments/assets/590af242-b731-4ce8-b9d3-377ca6454cc5" />
<img width="1910" height="946" alt="image" src="https://github.com/user-attachments/assets/7912d02c-7c62-4098-9b2e-5df3fd1cf0c4" />


Execution report link:http://127.0.0.1:57728/#suites/46b261ce93b2afa0244c023bc623d837/e9a10fcfc0a0ba3a/


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
