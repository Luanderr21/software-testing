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
# language: zh-CN
@S1 @framework
功能: 开始游戏
    作为一个游戏者
    我要启动游戏
    以便我能够开始玩游戏

    @S1.2
    场景: 点击 start 按钮
        假如用户已经启动游戏
        当用户按下 "Start" 按钮
        那么游戏开始运行
