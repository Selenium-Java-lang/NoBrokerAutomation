package eCommerceSeleniumFrameworkDesign1.Tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import eCommerceSeleniumFrameworkDesign1.TestComponents.BaseTest;
import eCommerceSeleniumFrameworkDesign1.TestComponents.Retry;
import eCommerceSeleniumFrameworkDesign1.pageobjects.CheckoutPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.ConfirmationPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.LandingPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.ProductCatalogue;
import eCommerceSeleniumFrameworkDesign1.pageobjects.cartPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {
	
@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
public void LoginErrorValidation() throws IOException, InterruptedException
{
	
		String productName="AUTOMATION";
		
		landingPage.logInApplications("pandeypiyush781@gmail.com", "Piyush81@");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		
		
	}
@Test
public void productErrorValidation() throws IOException, InterruptedException
{
	
		String productName="AUTOMATION";
		
		ProductCatalogue productCatalogue=landingPage.logInApplications("pandeypiyush781@gmail.com", "Piyush781@");
		
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		cartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyDisplay("AUTOMATION");
		//Assert.assertTrue(match);
		
		
	}
			
		
		
		
		


}
/*
 * Before creation of base code :Submit Order test
 * String productName="AUTOMATION";
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		LandingPage landingPage=new LandingPage(driver);
		landingPage.goTo();
		driver.manage().window().maximize();
		ProductCatalogue productCatalogue=landingPage.logInApplications("pandeypiyush781@gmail.com", "Piyush781@");
		
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		cartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMsg=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
 */


