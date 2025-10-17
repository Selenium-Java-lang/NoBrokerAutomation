package eCommerceSeleniumFrameworkDesign1.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import eCommerceSeleniumFrameworkDesign1.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".btnn.action__submit")
	WebElement submit;
	@FindBy(xpath="//button[2]//span[1][1]")
	WebElement selectCountry;
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		a.moveToElement(selectCountry).doubleClick().build().perform();
		
	}
	public ConfirmationPage submitOrder()
	{
		Actions a = new Actions(driver);
		a.moveToElement(submit).doubleClick().build().perform();
		return new ConfirmationPage(driver);
	}
	
}
