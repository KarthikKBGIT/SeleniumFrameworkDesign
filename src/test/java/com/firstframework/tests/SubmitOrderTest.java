package com.firstframework.tests;

import com.firstframework.testcomponents.BaseTest;
import com.firstframwork.pageobjects.CartProducts;
import com.firstframwork.pageobjects.ConfirmationPage;
import com.firstframwork.pageobjects.OrdersPage;
import com.firstframwork.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {

	String email ="karthikkb@gmail.com";
	String password ="Karthikkb4@";
	String productName = "ADIDAS ORIGINAL";

	ProductCatalogue productCatalogue;
	CartProducts cartProducts;
	ConfirmationPage confirmationPage;
	OrdersPage ordersPage;

	@Test
	public void SubmitOrder() {
		String partialCountryName ="ind";

		productCatalogue = landingPage.loginApplication(email, password);
		
		cartProducts = productCatalogue.addProductToCart(productName);
		
		Assert.assertTrue(cartProducts.checkProductNameInCart(productName));
		confirmationPage = cartProducts.checkOut();
		
		confirmationPage.selectCountry(partialCountryName);
		confirmationPage.placeOrder();
		Assert.assertTrue(confirmationPage.getThankYouText().equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void orderHistoryPageValidation() {
		productCatalogue = landingPage.loginApplication(email, password);
		ordersPage = productCatalogue.goToOrders();
		Assert.assertTrue(ordersPage.validateOrdername(productName));
	}
}
