package com.firstframwork.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.firstframework.abstractcomponents.AbstractComponent;

public class CartProducts extends AbstractComponent{

	WebDriver driver;
	
	public CartProducts(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productName;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public List<String> getCartProductName(){
		return productName.stream().map(product -> product.getText()).toList();
	}
	
	public boolean checkProductNameInCart(String productName) {
		return getCartProductName().contains(productName);
	}
	
	public ConfirmationPage checkOut() {
		checkoutButton.click();
		return new ConfirmationPage(driver);
	}
}
