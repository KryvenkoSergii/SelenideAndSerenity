Feature: Smoke
  As a user
  I want to add Top 3 Films to Favorites
  And check if they display on the FavoriteMovies page
  
  Scenario Outline: Check add login page
  	Given User is on the Login page
  	And User checks URL address
  	Then User login to app
  	And User navigates to Showtimes page
  	And User checks if '<technology>' technology is presents
  	When User navigates to Movies page
  	And User add Top 3 films to Favorites
  	Then User navigates to FavoriteMovies page
  	And User checks if all selected films were added
  	
  	Examples:
      | technology  |
      | 4DX					|