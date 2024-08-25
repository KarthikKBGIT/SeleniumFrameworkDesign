package com.firstframework.tests;

import com.firstframework.testcomponents.BaseTest;
import com.firstframwork.pageobjects.CartProducts;
import com.firstframwork.pageobjects.ConfirmationPage;
import com.firstframwork.pageobjects.OrdersPage;
import com.firstframwork.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

	ProductCatalogue productCatalogue;
	CartProducts cartProducts;
	ConfirmationPage confirmationPage;
	OrdersPage ordersPage;

	@Test(dataProvider="getData", groups = {"SubmitOrderByDataProvider"})
	public void SubmitOrder(HashMap<String, String> input) {
		String partialCountryName ="ind";

		productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		cartProducts = productCatalogue.addProductToCart(input.get("productName"));
		
		Assert.assertTrue(cartProducts.checkProductNameInCart(input.get("productName")));
		confirmationPage = cartProducts.checkOut();
		
		confirmationPage.selectCountry(partialCountryName);
		confirmationPage.placeOrder();
		Assert.assertTrue(confirmationPage.getThankYouText().equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"}, dataProvider = "getData", groups = {"SubmitOrderByDataProvider"})
	public void orderHistoryPageValidation(HashMap<String, String> input) {
		productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		ordersPage = productCatalogue.goToOrders();
		Assert.assertTrue(ordersPage.validateOrdername(input.get("productName")));
	}

//	@DataProvider
//	public Object[][] getData(){
//		return new Object[][] {{"karthikkb@gmail.com","Karthikkb4@","ZARA COAT 3"},
//								{"karthikkb@gmail.com","Karthikkb4@","ADIDAS ORIGINAL"}};
//	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "//src//test//java//com//firstframework//data//PurchaseOrder.json");

		/*HashMap<String,String> dataMap1 = new HashMap<String, String>();
		HashMap<String,String> dataMap2 = new HashMap<String, String>();
		dataMap1.put("email", "karthikkb@gmail.com");
		dataMap1.put("password", "Karthikkb4@");
		dataMap1.put("productName", "ZARA COAT 3");

		dataMap2.put("email", "karthikkb@gmail.com");
		dataMap2.put("password", "Karthikkb4@");
		dataMap2.put("productName", "ADIDAS ORIGINAL");*/

		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
