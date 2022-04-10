Feature: FRC Via SMS
  I want to do First Recharge for the KPK number vis SMS
  
Background:
Given User Login into Seibel CRM Application with valid credentials
|username|password|
|sadmin|"sadminu#t"|

Scenario: First Recharge via SMS for single region
Given User is on Seibel CRM Home screen
And User verify Retailer Balance for "00000394" in CRM
When User fetch one valid KPK number from CRM Database
Then User verify Price plan name for KPK CRM Subscription Menu 
When User Launch NSGUI Application 
And User Submit short message with "089688523444" and 313 and 1133
And User verify Retailer Balance for "00000390" in CRM
And User verify Price plan name for KPK CRM Subscription Menu 
And User Logout of Siebel CRM Application