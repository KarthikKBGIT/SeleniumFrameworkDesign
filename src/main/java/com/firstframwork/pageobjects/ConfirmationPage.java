package com.firstframwork.pageobjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.firstframework.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;
	Random random = new Random();
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryTextBox;
	
	@FindBy(css=".list-group-item")
	List<WebElement> listOfOptions;
	
	@FindBy(xpath="//*[contains(text(),'Place Order')]")
	WebElement placeOrderButton;
	
	@FindBy(css=".hero-primary")
	WebElement thankYouText;
	
	public void selectCountry(String partialCountry) {
		countryTextBox.sendKeys(partialCountry);
		int index = random.nextInt(0, 1000) % listOfOptions.size();
		listOfOptions.get(index).click();
	}
	
	public void placeOrder() {
		placeOrderButton.click();
	}
	
	public String getThankYouText() {
		return thankYouText.getText();
	}

}
