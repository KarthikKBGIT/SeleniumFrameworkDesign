package com.firstframework;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.firstframwork.pageobjects.CartProducts;
import com.firstframwork.pageobjects.ConfirmationPage;
import com.firstframwork.pageobjects.LandingPage;
import com.firstframwork.pageobjects.ProductCatalogue;

import java.time.Duration;
import java.util.List;

public class StandAloneTest_POM {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		LandingPage landingPage = new LandingPage(driver);
		//ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		//CartProducts cartProducts = new CartProducts(driver);
		//ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String email ="karthikkb@gmail.com";
		String password ="Karthikkb4@";
		String productName = "ADIDAS ORIGINAL";
		String partialCountryName ="ind";

		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		
		List<WebElement> products = productCatalogue.getProducts();
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
