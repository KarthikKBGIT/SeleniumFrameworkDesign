package com.firstframework.abstractcomponents;

import java.time.Duration;

import com.firstframwork.pageobjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement myCart;

	@FindBy(xpath="//button[contains(@routerlink,'myorders')]")
	WebElement orders;
	
	By myCartText = By.xpath("//h1[text()='My Cart']");
	
	public void waitForElementToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToInvisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void goToCartPage() {
		myCart.click();
		waitForElementToAppear(myCartText);
	}
	public OrdersPage goToOrders(){
		orders.click();
		return new OrdersPage(driver);
	}
}
