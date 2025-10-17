package eCommerceSeleniumFrameworkDesign1.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import eCommerceSeleniumFrameworkDesign1.TestComponents.BaseTest;
import eCommerceSeleniumFrameworkDesign1.pageobjects.CheckoutPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.ConfirmationPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.LandingPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.ProductCatalogue;
import eCommerceSeleniumFrameworkDesign1.pageobjects.cartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionimpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public cartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage =launchApplication();
	}
	@And("goes to landing page")
    public void goes_to_landing_page() {
        // Optional step – can be used for navigation validation
    }

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password)
	{
		productCatalogue=landingPage.logInApplications(username, password);
	}
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@And("^checkout (.+) and submit the order$")
	public void checkout_productName_and_submit_the_order(String productName)
	{
        /*cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyDisplay((productName));
		Assert.assertTrue(match);
	    checkoutPage=cartPage.goToCheckOut();
		checkoutPage.selectCountry("India");
		confirmationPage=checkoutPage.submitOrder();*/
		cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.VerifyDisplay(productName);
        //Assert.assertTrue(match);

        checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string)
	{
		String confirmMsg=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		
	}
	 @Then("\"Incorrect email or password.\" message is displayed")
	    public void error_message_is_displayed() {
	        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	        driver.quit();
	    }

}
