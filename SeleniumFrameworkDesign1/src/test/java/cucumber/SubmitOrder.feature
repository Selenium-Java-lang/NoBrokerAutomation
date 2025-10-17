@Tag
Feature:  Purchase the order from Ecommerce website
I  want to use this template for my feature file
Background:
    Given I landed on Ecommerce Page
    And goes to landing page
 @Regression          
    Scenario Outline: Positive Test of submitting the order
    	Given Logged in with username <name> and password <password>
    	When I add product <productName> to cart
    	And  checkout <productName>  and submit the order
    	Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    		
    		Examples:
    		| name                   | password      | productName | 
    		| pandeypiyush781@gmail.com | Piyush781@ | AUTOMATION  |
    		
    		    