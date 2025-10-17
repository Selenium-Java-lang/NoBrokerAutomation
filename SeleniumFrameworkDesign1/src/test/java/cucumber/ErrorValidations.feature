@Tag
Feature:  Error Validations
I  want to use this template for my feature file

    
    
 @ErrorValidations          
    Scenario Outline: Test of Error Validations
        Given I landed on Ecommerce Page
    	When Logged in with username <name> and password <password>
    	Then "Incorrect email or password." message is displayed 
    		
    		Examples:
    		| name                      | password  | 
    		| pandeypiyush781@gmail.com | Piyush78@ | 
    		
    		    