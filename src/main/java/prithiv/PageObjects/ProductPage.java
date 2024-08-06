package prithiv.PageObjects;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import prithiv.AbstractComponents.AbstractComponent;

public class ProductPage extends AbstractComponent {

	WebDriver driver;

	WebDriverWait wait;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css=".card-body")
	List<WebElement> allProducts;

	By productsBy = By.cssSelector(".mb-3");

	By toaster = By.cssSelector(".toast-container");
	By spinner = By.cssSelector(".ng-animating");

	By addToCart= By.cssSelector(".card-body button:last-of-type");
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	public List<WebElement> getProductList() {

		waitForElementToAppear(productsBy);

		return allProducts;

	}

	public WebElement getProductByName(String prodcutName) {
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(prodcutName)).findFirst().orElse(null);

		return prod;
	}

	public void addProductToCart(String prodcutName) {
		WebElement prod = getProductByName(prodcutName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toaster);
		waitForElementToDisaappear(spinner);
	}





}
