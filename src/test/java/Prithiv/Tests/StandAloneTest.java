package Prithiv.Tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("prithiv@gmail.com");
		
		driver.findElement(By.id("userPassword")).sendKeys("Prithiv@24");
		driver.findElement(By.id("login")).click();
		//product serched and added to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		
		List<WebElement> allProducts = driver.findElements(By.cssSelector(".card-body"));
		
		WebElement prod= allProducts.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
		Thread.sleep(2000);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		//addTocartPage
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> addedProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean anyMatch = addedProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase("IPHONE 13 PRO"));
		Assert.assertTrue(anyMatch);
		
		//checkout 
		//button[@type='button'])[2]
		
	driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		WebElement selCountry = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		selCountry.click();
		selCountry.sendKeys("india");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector("[class*='action__submit']")).click();
		
		//cfrm page
		
		boolean text = driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order.");
		
		Assert.assertTrue(text);
		
		
		
		
		
		
	driver.close();
		
		
		
		
	}

}
