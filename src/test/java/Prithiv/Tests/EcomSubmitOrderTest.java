package Prithiv.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import prithiv.PageObjects.CartPage;
import prithiv.PageObjects.CheckOutPage;
import prithiv.PageObjects.ConfirmationPage;
import prithiv.PageObjects.ProductPage;

public class EcomSubmitOrderTest extends BaseTest {
	
	@Test(groups = {"purchase order"}, dataProvider = "getData")
public  void SubmitOrderTest(HashMap<String, String> input) throws InterruptedException {
		
		
		
		ProductPage cataloguePage= landingPage.loginApplication(input.get("email"),input.get("password"));
		
		List<WebElement> products = cataloguePage.getProductList();
		cataloguePage.addProductToCart(input.get("product"));
		CartPage goToCart = cataloguePage.goToCart();
		boolean match=goToCart.verifyProductDisplay(input.get("product"));
	
		Assert.assertTrue(match);
		CheckOutPage checkout = goToCart.goToCheckout();
		checkout.selectCountry("india");
		
		ConfirmationPage submitOrder = checkout.submitOrder();
		String confirmationMessage = submitOrder.getConfirmationMessage();
		
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			
		
}
	@Test(dependsOnMethods= {"SubmitOrderTest"},dataProvider = "getData")
	public void OrderHistoryTest(HashMap<String, String> input)
	{
		ProductPage cataloguePage = landingPage.loginApplication(input.get("email"), input.get("password"));
		prithiv.PageObjects.OrderPage ordersPage = cataloguePage.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(input.get("product")));
		
}
	@DataProvider
	public Object [][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonData("C:\\Users\\prith\\eclipse-workspace\\AcadameyEcom\\src\\main\\java\\prithiv\\resorces\\DataReader.json");

		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
}
