Feature: Smoke
  As a user
  I want to test check if I am on the login page
  Next I fill my cred and login to app
  
  Scenario Outline: Check add login page
  	Given a User is on the Login page
  	And User checks URL address
  	Then a User login to app 
