package prithiv.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import prithiv.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent  {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	public ProductPage loginApplication(String userEmail , String password) {
		
		userName.sendKeys(userEmail);
		userPassword.sendKeys(password);
		submit.click();
		
		ProductPage productCatalogue= new ProductPage(driver);
		
		return productCatalogue;

	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	

	

}
