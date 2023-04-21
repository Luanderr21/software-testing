#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@S4
Feature: As a player
    I want to be able to suspend the game
    So that I can pause the game and do something else

    @S4.1
    Scenario: Suspend the game
        Given The game has started
        When The player click the "Stop" button
        Then The game pauses

    @S4.2
    Scenario: Resume the game
        Given The game is suspended
        When The player clicks the "Start" button
        Then the game is resumed
