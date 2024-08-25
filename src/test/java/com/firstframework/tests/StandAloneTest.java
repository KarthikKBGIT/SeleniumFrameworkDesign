package com.firstframework.tests;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String productName = "ADIDAS ORIGINAL";

		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("karthikkb@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Karthikkb4@");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Sign Out')]")));
		List<WebElement> cards = driver.findElements(By.xpath("//div[@class='card']"));
		WebElement product = cards.stream()
				.filter(card -> card.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("fa-shopping-cart")));
        assert product != null;
        product.findElement(By.className("fa-shopping-cart")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@routerlink,'cart')]")));
		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='My Cart']")));
		List<WebElement> productNamesOnCart = driver.findElements(By.cssSelector(".cartSection h3"));
		Assert.assertTrue(productNamesOnCart.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName)));
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countryOption = driver.findElements(By.cssSelector(".list-group-item"));
		int index = random.nextInt(0, 1000) % 3;
		countryOption.get(index).click();
		driver.findElement(By.xpath("//*[contains(text(),'Place Order')]")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order."));
		System.out.println("Test Completed successfully. Quitting driver..");
		driver.quit();
	}
}
