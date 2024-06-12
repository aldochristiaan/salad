Feature: Cucumber Feature

  @Pass
  Scenario: Scenario A
    Given user is on main page
    Then user is doing validation

  @Fail
  Scenario: Scenario B
    Given user is on main page
    Then user is facing an error

