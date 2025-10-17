package eCommerceSeleniumFrameworkDesign1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eCommerceSeleniumFrameworkDesign1.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent{
	WebDriver driver;
	public cartPage(WebDriver driver)
	{ 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
	//pageFactory
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutEle;
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	
	
	
	
	public Boolean VerifyDisplay(String productName)
	{
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return(match);
	}
	public CheckoutPage goToCheckOut()
	{
		checkOutEle.click();
		return new CheckoutPage(driver);
		
	}
	

}
