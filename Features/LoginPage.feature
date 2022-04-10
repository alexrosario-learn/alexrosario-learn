Feature: Login page feature

Scenario: Login with correct credentials
Given user is on login page
When user enters username "sadmin"
And user enters password "sadminu#t"
And user clicks on Login button
When user gets the title of the page
And page title should be "Siebel Web Call Center Home"