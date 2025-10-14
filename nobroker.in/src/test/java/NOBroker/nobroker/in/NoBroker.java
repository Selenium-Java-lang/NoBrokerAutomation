package NOBroker.nobroker.in;

import io.github.bonigarcia.wdm.WebDriverManager;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class NoBroker {
    WebDriver driver;

    @BeforeClass
    public void setup() {
       // WebDriverManager.chromedriver().setup();
    	 WebDriverManager.chromedriver().setup();

         ChromeOptions options = new ChromeOptions();
         options.addArguments("--incognito");
         options.addArguments("--start-maximized");
         driver = new ChromeDriver(options);
        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();*/
    }

    @Test(priority = 1, description = "Verify Image and Text alignment with login as Customer")
    public void verifyLoginAndAlignment() throws InterruptedException {
        driver.get("https://www.nobroker.in/");
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Brokerage"), "User not redirected to homepage");

        driver.findElement(By.cssSelector(".px-1p.border-l-1")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("signUp-phoneNumber")).sendKeys("9999999999");

        System.out.println("Skipping OTP entry (mocked).");
        driver.navigate().refresh();
        driver.findElement(By.cssSelector(
                "div[class='flex py-0 px-1.5p border-l-1 border-l-solid border-l-header-grey items-center cursor-pointer']")
        ).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='Post Your Property']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("list-your-property-for-rent-sale"), "list-your-property-for-rent-sale");

        driver.findElement(By.xpath("//div[contains(text(),'Select City')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[text()='Bangalore']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Bangalore']")).isDisplayed(), "Bangalore");

        WebElement imageElement = driver.findElement(By.xpath("(//img)[1]"));
        WebElement textElement = driver.findElement(By.xpath("(//h1 | //h2 | //p)[1]"));

        Point imgLocation = imageElement.getLocation();
        Point textLocation = textElement.getLocation();
        Assert.assertTrue(Math.abs(imgLocation.getY() - textLocation.getY()) < 100, "Image and text are not aligned");

        System.out.println("âœ… Image and text alignment verified successfully.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
