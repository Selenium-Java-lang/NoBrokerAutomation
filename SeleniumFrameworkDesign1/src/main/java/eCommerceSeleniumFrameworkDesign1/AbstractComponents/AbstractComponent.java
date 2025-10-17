package eCommerceSeleniumFrameworkDesign1.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import eCommerceSeleniumFrameworkDesign1.pageobjects.OrderPage;
import eCommerceSeleniumFrameworkDesign1.pageobjects.cartPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='/dashboard/cart']")
	WebElement cartHeader;
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement OrderHeader;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
		
	}
	public void waitForWebElementToAppear(WebElement ErrorMessage)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ErrorMessage));
		
	}
	public cartPage goToCartPage()
	{
		cartHeader.click();	
		cartPage cartPage = new cartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrdersPage()
	{
		OrderHeader.click();	
		OrderPage orderPage = new OrderPage(driver);
		return orderPage; 
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void goToCheckOut(WebElement checkOutEle) throws InterruptedException
	{
		Thread.sleep(1000);
	}
	

}
