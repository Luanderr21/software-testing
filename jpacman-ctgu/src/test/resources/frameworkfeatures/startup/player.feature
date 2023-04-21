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
@S2 @framework
Feature: Move the player
    As a player,
    I want to move my Pacman around on the board;
    So that I can earn all points and win the game.

    @S2.1
    Scenario: The player consumes
        Given the game has started,
        And my Pacman is next to a square containing a pellet;
        When I press an arrow key towards that square;
        Then my Pacman can move to that square,
        And I earn the points for the pellet,
        And the pellet disappears from that square.
