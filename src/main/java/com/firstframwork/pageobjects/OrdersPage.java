package com.firstframwork.pageobjects;

import com.firstframework.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//table[contains(@class,'ng-star-inserted')]/tbody//td[2]")
	List<WebElement> orderNames;

	public List<String> getOrderNames(){
		return orderNames.stream().map(product -> product.getText()).toList();
	}
	public boolean validateOrdername(String productName){
		return getOrderNames().contains(productName);
	}
}
