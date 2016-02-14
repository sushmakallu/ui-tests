package com.cashkaro.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cashkaro.constants.constants;
import com.cashkaro.pages.HomePage;
import com.cashkaro.pages.SignInPage;
import com.cashkaro.ui_tests.DriverScript;

public class SignInHelper extends DriverScript {

	WebDriver driver = getDriver();
	SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	WebDriverWait wait = new WebDriverWait(driver, 10);

	public void navigateToSignInForm() {
		goToURL(constants.BASE_URL);
		signInPage.signIn.click();
		switchToFrame(signInPage.signInFrame);
	}

	public void verifyLoginElements() {
		navigateToSignInForm();
		Assert.assertTrue(signInPage.userName.isDisplayed(),
				"User name field is not displayed");
		Assert.assertTrue(signInPage.password.isDisplayed(),
				"Password field is not displayed");
		Assert.assertTrue(signInPage.rememberUser.isDisplayed(),
				"Keep me signed in is not displayed");
		Assert.assertTrue(signInPage.forgotPasswordLink.isDisplayed(),
				"Forgot password link is not displayed");

		Assert.assertTrue(signInPage.signInButton.isEnabled(),
				"SignIn button is not displayed");
		Assert.assertEquals(signInPage.keepSignedIn.getText(),
				constants.KEEP_SIGNED_IN,
				"Keep me signed in text is not matching");

	}

	public void login(String username, String password) {
		navigateToSignInForm();
		signInPage.userName.clear();
		signInPage.userName.sendKeys(username);
		signInPage.password.sendKeys(password);
		signInPage.signInButton.click();

	}

	public void verifyHomepage() {
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.visibilityOf(homePage.logout));
	}

	public void logout() {
		homePage.logout.click();
		wait.until(ExpectedConditions.visibilityOf(signInPage.signIn));

	}

	public void verifySigninErrors(String username, String password) {
		login(username, password);
		if (username == "" && password == "") {
			Assert.assertTrue(
					signInPage.errorMessage.getText().contains(
							constants.SIGNIN_USERNAME_ERROR),
					"Error message is not matching");
			Assert.assertTrue(
					signInPage.errorMessage.getText().contains(
							constants.SIGNIN_PASSWORD_ERROR),
					"Error message is not matching");
		} else if (username == "" && password != "") {
			Assert.assertEquals(signInPage.errorMessage.getText(),
					constants.SIGNIN_USERNAME_ERROR,
					"Error message is not matching");

		} else if (username != "" && password == "") {
			Assert.assertEquals(signInPage.errorMessage.getText(),
					constants.SIGNIN_PASSWORD_ERROR,
					"Error message is not matching");

		}

	}

	public void loginWithFacebook(String username, String password) {
		FacebookHelper facebookHelper = new FacebookHelper();
		navigateToSignInForm();
		signInPage.facebookSigninButton.click();
		facebookHelper.loginFacebook(username, password);
		switchBackToOriginalBrowser();

	}

}
