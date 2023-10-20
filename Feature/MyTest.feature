Feature: Registration Functionality 
Scenario: Verification of the Use on the Restricted page 
Given Open the Chrome and launch the Web application
When Restricted Username and Password 
And Click on the Submit button 
Then Redirect to the Registration page

Scenario: Verify the step1 Confirm Eligibility page
Given Select the CURRENTLY EMPLOYED checkbox
When Select the Aggrement Checkbox
And Click on the Confirm Eligibility Next button 
Then Redirect to the Step2 page

Scenario: Verify the step2 Create Your Account page
Given the user is on the Create Your Account page
When the user provides the user information
And the user selects their company as "fxbytes"
And the user agrees to the terms and conditions
And the user enters the captcha code within 10 seconds
Then the registration process is completed successfully

Scenario: Mobile Number Verification
  When the user selects the country code for mobile number
  And the user enters the mobile number [mob]
  And the user clicks the Verify button
  And the user confirms the action
  Then the OTP verification process is initiated

  Scenario: Basic Data Entry
  When the user enters the address as [address]
  And the user selects a state
  And the user enters the city as "NewYour"
  And the user enters the postal code as "gh32"
  And the user adds an image within 15 seconds
  And the user submits the basic data
  Then the basic data entry is completed successfully
  
  Scenario: Work Experience Entry
  When the user enters "7" years in legal operations
  And the user enters "7" years in direct managing
  And the user selects the role of "Sr. Analyst"
  And the user selects "Y" for being a licensed attorney
  And the user enters the job title as "QA"
  And the user selects the current level as "Sr. Analyst"
  And the user selects the start date
  And the user selects "Y" for attorney role
  And the user selects "Y" for reporting to the General Counsel
  And the user enters "7" for the number of years managed
  And the user selects the US state of California
  And the user selects the IT department
  And the user selects the legal department size as "25"
  And the user selects "no" for leading the legal operations team
  And the user submits the work experience
  Then the work experience entry is completed successfully
  
 Scenario: Annual Compensation Entry
  When the user enters "10000" for the base compensation in 2020
  And the user enters "10000" for the bonus compensation in 2020
  And the user enters "10000" for the stock compensation in 2020
  And the user selects the job level as "Sr. Analyst"
  And the user enters "10000" for the base compensation in 2020 (2nd time)
  And the user enters "10000" for the bonus compensation in 2020 (2nd time)
  And the user enters "10000" for the stock compensation in 2020 (2nd time)
  And the user clicks the Next button
  Then the annual compensation details are entered successfully
  
Scenario: Education Entry
  When the user selects the degree/certification option
  And the user selects the school/university attended as "American Academy of Art"
  And the user selects the program level as "Certificate"
  And the user selects the start date as "2022"
  And the user selects the end date as "2023"
  And the user selects the area of study as "Arts"
  And the user clicks the Next button
  Then the education details are entered successfully
  
  