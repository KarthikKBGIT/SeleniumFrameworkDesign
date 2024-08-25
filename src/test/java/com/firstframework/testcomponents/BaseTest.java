package com.firstframework.testcomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import com.firstframwork.pageobjects.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") 
				+ "\\src\\main\\java\\com\\firstframework\\resources\\GlobalData.properties");
		properties.load(fis);
		if(properties.getProperty("browser").equalsIgnoreCase("Chrome")) {
			this.driver = new ChromeDriver();
		}
		else if(properties.getProperty("browser").equalsIgnoreCase("Edge")) {
			this.driver = new EdgeDriver();
		}
		else if(properties.getProperty("browser").equalsIgnoreCase("Firefox")) {
			this.driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		WebDriver driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void CloseDriver(){
		driver.close();
	}
}
