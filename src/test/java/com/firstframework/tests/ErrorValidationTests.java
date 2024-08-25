package com.firstframework.tests;

import com.firstframework.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTests extends BaseTest {
	@Test(groups = {"ErrorValidations"})
	public void LoginErrorValidationIncorrectPassword() {
		String email ="karthikkb@gmail.com";
		String password ="Karthik4@";
		landingPage.loginApplication(email, password);
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
	@Test
	public void LoginErrorValidationIncorrectEmail() {
		String email ="karthikkbincorrect@gmail.com";
		String password ="Karthikkb4@";
		landingPage.loginApplication(email, password);
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
	@Test
	public void LoginErrorValidationIncorrectEmailandPassword() {
		String email ="karthikkbincorrect@gmail.com";
		String password ="Karthikkb4@";
		landingPage.loginApplication(email, password);
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
}
