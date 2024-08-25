package com.firstframwork.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.firstframework.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	String url="https://rahulshettyacademy.com/client";
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail ;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	//ng-tns-c4-13 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	@FindBy(css = ".ng-trigger-flyInOut")
	WebElement errorMessage;

	
	public void goTo() {
		driver.get(this.url);
	}
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(this.driver);
		return productCatalogue;
	}

	public String getErrorMessage(){
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
