package Prithiv.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.auth.StandardAuthScheme;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import prithiv.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	
	public WebDriver initializeBrowser() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\prithiv\\resorces\\GlobaData.properties");
		
		prop.load(fis);

		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			if (browserName.contains("chrome")) {
				ChromeOptions options = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if(browserName.contains("headless")){
				options.addArguments("headless");
				}		
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440,900));
		}
			
		else if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();

		}
		else {
			WebDriverManager.edgedriver().setup();

			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.manage().window().maximize();
		
	}
		return driver;
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		
		driver =initializeBrowser();
		 landingPage= new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
		
	}
	
	public List<HashMap<String, String>> getJsonData(String filepath) throws IOException {
  String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
  ObjectMapper mapper = new ObjectMapper();
  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
});
  
  return data;
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
TakesScreenshot ts = (TakesScreenshot)driver;

File src = ts.getScreenshotAs(OutputType.FILE);
File destination = new File(System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png");
FileUtils.copyFile(src, destination);

return System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";
	}
}
