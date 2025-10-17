package eCommerceSeleniumFrameworkDesign1.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import eCommerceSeleniumFrameworkDesign1.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	

	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir")+"\\src\\main\\java\\eCommerceSeleniumFrameworkDesign1\\resources\\GlobalData.properties");
		prop.load(fis);
		//to run in any browser through cmd used this code 
		//don't need to change anything in GlobalData.properties
		String browserName =System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("Browser");
		//String browserName = prop.getProperty("Browser");
		if (browserName.contains("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
			

		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			//firefox
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edgedriver
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}
	
		
		 public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	     {
	    	 TakesScreenshot ts = (TakesScreenshot)driver;
	    	 File source=ts.getScreenshotAs(OutputType.FILE);
	    	 File file = new File(System.getProperty("user.dir")+"\\reports\\" + testCaseName+ ".png");
	    	 FileUtils.copyFile(source, file);
	    	 return System.getProperty("user.dir")+"\\reports\\" + testCaseName+ ".png";
	    	 
	     }

	

	/*public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent=FileUtils.readFileToString(new File(filePath),
		StandardCharsets.UTF_8);
		//String to HashMap Jackson Databind
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()
		{});
		return data;
		
	}*/
	
	
	@BeforeMethod(alwaysRun=true)	
	public LandingPage launchApplication() throws IOException
	{
		
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	/*@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}*/
	
}


