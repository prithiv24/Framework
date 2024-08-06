package prithiv.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import prithiv.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
public CartPage(WebDriver driver) {
	
	super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

@FindBy(css=".cartSection h3")

List<WebElement>addedProducts;

@FindBy(xpath="(//button[@type='button'])[2]")
WebElement checkOut;

public boolean verifyProductDisplay(String productName) {
	
	 boolean match = addedProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
	 
	 return match;

	
}

public CheckOutPage goToCheckout() {

	checkOut.click();
	
	return new CheckOutPage(driver);
	
}

}
