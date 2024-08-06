package prithiv.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import prithiv.PageObjects.CartPage;

public class AbstractComponent {

	 WebDriver driver;

	 WebDriverWait wait;

	public AbstractComponent(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findby) {
		wait = new  WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}

	public void waitForElementToDisaappear(By findby) {
		wait = new  WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findby));
	}
	public prithiv.PageObjects.OrderPage goToOrdersPage()
	{
		orderHeader.click();
		prithiv.PageObjects.OrderPage orderPage = new prithiv.PageObjects.OrderPage(driver);
		return orderPage;
	}
	public CartPage goToCart() {
		cartHeader.click();
		return new CartPage(driver);
		
	}


}
