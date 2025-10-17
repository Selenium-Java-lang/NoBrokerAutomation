package eCommerceSeleniumFrameworkDesign1.Tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import eCommerceSeleniumFrameworkDesign1.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName="AUTOMATION";
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage=new LandingPage(driver);
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("pandeypiyush781@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Piyush781@");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod =products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
		.orElse(null);
		prod.findElement(By.cssSelector(".btn.w-10")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();
		List<WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//button[text()='Checkout']"))));
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[2]//span[1][1]")));
		WebElement n1 =driver.findElement(By.xpath("//button[2]//span[1][1]"));
		a.moveToElement(n1).doubleClick().build().perform();
		WebElement placeOrder=driver.findElement(By.cssSelector(".btnn.action__submit"));
		a.moveToElement(placeOrder).doubleClick().build().perform();
		String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		/*driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item:nth-of-type(2)")));
		List<WebElement> options =driver.findElements(By.cssSelector(".ta-item span"));
		Thread.sleep(10000);
		for(WebElement option : options)
		{
			if(option.getText().equalsIgnoreCase("India"))
				
				
			{
			option.click();
			break;
			
		}
		}*/
	}
			
		
		
		


}


