package eCommerceSeleniumFrameworkDesign1.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import eCommerceSeleniumFrameworkDesign1.TestComponents.BaseTest;
import eCommerceSeleniumFrameworkDesign1.pageobjects.CheckoutPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.ConfirmationPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.LandingPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.OrderPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.ProductCatalogue;
import eCommerceSeleniumFrameworkDesign1.pageobjects.cartPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	//String productName="AUTOMATION";
	
@Test(dataProvider="getData",groups= {"purchase"})
public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
{
	
		
		
		ProductCatalogue productCatalogue=landingPage.logInApplications(input.get("email"), input.get("password"));
		
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		cartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyDisplay((input.get("product")));
		//Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMsg=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
// To verify AUTOMATION is displaying in orders page
@Test(dataProvider = "getData", dependsOnMethods = {"submitOrder"})
     public void OrderHistoryTest(HashMap<String, String> input)
     {
    	 ProductCatalogue productCatalogue=landingPage.logInApplications(input.get("email"), input.get("password"));
 		
 		OrderPage orderPage=productCatalogue.goToOrdersPage(); 
 		Assert.assertTrue(orderPage.VerifyOrderDisplay(input.get("product")));
     }
     
    
     
     @DataProvider
     public Object[][] getData() throws IOException
     {
    	HashMap<String,String> map=new HashMap<String,String>();
    	 map.put("email", "pandeypiyush781@gmail.com");
    	 map.put("password", "Piyush781@");
    	 map.put("product", "AUTOMATION");
    	 HashMap<String,String> map1=new HashMap<String,String>();
    	 map1.put("email", "pandeypiyush781@gmail.com");
    	 map1.put("password", "Piyush781@");
    	 map1.put("product", "AUTOMATION");
    	 return new Object[][] { {map},{map1}};
    	 //List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Amazon\\SeleniumFrameworkDesign1\\data\\PurchaseOrder.json");
    	 //return new Object[][] { {data.get(0)},{data.get(1)}};
    	 
    	 
     }
     
     
    // @DataProvider
 	//public Object[][] getData()
 	//{
    	// return new Object[][]  { {"pandeypiyush781@gmail.com","Piyush781@","AUTOMATION"},{"pandeypiyush781@gmail.com","Piyush781@","AUTOMATION"} };
 	//}
			
		
		
		


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


