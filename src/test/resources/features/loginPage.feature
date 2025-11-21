Feature: Login functionality

  Scenario: User logs in with valid credentials
    Given I am on the login page
    When I enter username "testuser" and password "testpass"
    And I click on login button
    Then I should see the Dashboard page
    And I log out
    Then I should see the login button

