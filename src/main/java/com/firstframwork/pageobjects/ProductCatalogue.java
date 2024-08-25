package com.firstframwork.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.firstframework.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	String url="https://rahulshettyacademy.com/client";
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> cards = driver.findElements(By.cssSelector(".mb-3""));
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By productLocator = By.cssSelector(".mb-3");
	By addToCart= By.className("fa-shopping-cart");
	By toastMessage = By.id("toast-container");
	By animation = By.className("ng-animating");
	
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement clickableCart;
	
	public List<WebElement> getProducts(){
		waitForElementToAppear(productLocator);
		return products;
	}
	
	public WebElement getProductByname(String productName){
		return getProducts().stream()
				.filter(prod -> prod.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
	}
	
	public CartProducts addProductToCart(String productName) {
		WebElement prod = getProductByname(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToInvisible(animation);
		waitForElementToBeClickable(clickableCart);
		goToCartPage();
		return new CartProducts(driver);
	}
}
