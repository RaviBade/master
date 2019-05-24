@Feature01
Feature: Verification of vehicle registration details in DVLA 
  This feature supports to verify vehicle registration details from DVLA

@smokeTest
  Scenario: Scan and list files for a given directory
    Given Scan and list the configured directory for files
    Then List excel and csv files only

    
@functionalTest
  Scenario: Validate vehicle registration details
  	Given Open vehicle information page from dvla
    Then Read file and validate vehicle information

