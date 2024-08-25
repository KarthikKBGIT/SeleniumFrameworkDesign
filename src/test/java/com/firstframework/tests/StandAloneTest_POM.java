package com.firstframework.tests;
import org.openqa.selenium.*;
import org.testng.Assert;

import com.firstframework.testcomponents.BaseTest;
import com.firstframwork.pageobjects.CartProducts;
import com.firstframwork.pageobjects.ConfirmationPage;
import com.firstframwork.pageobjects.LandingPage;
import com.firstframwork.pageobjects.ProductCatalogue;
import java.io.IOException;
import java.util.List;

public class StandAloneTest_POM {

	public static void main(String[] args) throws IOException {
		BaseTest baseTest = new BaseTest();
		WebDriver driver = baseTest.initializeDriver();
		LandingPage landingPage = new LandingPage(driver);
		//ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		//CartProducts cartProducts = new CartProducts(driver);
		//ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String email ="karthikkb@gmail.com";
		String password ="Karthikkb4@";
		String productName = "ADIDAS ORIGINAL";
		String partialCountryName ="ind";

		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		
		CartProducts cartProducts = productCatalogue.addProductToCart(productName);
		
		Assert.assertTrue(cartProducts.checkProductNameInCart(productName));
		ConfirmationPage confirmationPage = cartProducts.checkOut();
		
		confirmationPage.selectCountry(partialCountryName);
		confirmationPage.placeOrder();
		Assert.assertTrue(confirmationPage.getThankYouText().equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Test Completed successfully. Quitting driver..");
		driver.quit();
	}
}
