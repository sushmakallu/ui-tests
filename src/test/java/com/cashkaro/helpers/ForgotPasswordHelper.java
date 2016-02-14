package com.cashkaro.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cashkaro.constants.constants;
import com.cashkaro.pages.ForgotPasswordPage;
import com.cashkaro.pages.SignInPage;
import com.cashkaro.ui_tests.DriverScript;

public class ForgotPasswordHelper extends DriverScript {

	WebDriver driver = getDriver();
	SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
	ForgotPasswordPage forgotPasswordPage = PageFactory.initElements(driver,
			ForgotPasswordPage.class);
	WebDriverWait wait = new WebDriverWait(driver, 10);

	public void verifyForgotPassword(String email) {
		goToURL(constants.BASE_URL);
		signInPage.signIn.click();
		driver.switchTo().frame(signInPage.signInFrame);
		Assert.assertTrue(signInPage.forgotPasswordLink.isDisplayed(),
				"Forgot password link is not displayed");
		signInPage.forgotPasswordLink.click();

		wait.until(ExpectedConditions.visibilityOf(forgotPasswordPage.email));
		forgotPasswordPage.email.sendKeys(email);
		forgotPasswordPage.submitButton.click();

	}

	public void verifyForgotPasswordConfirmation() {
		wait.until(ExpectedConditions
				.visibilityOf(forgotPasswordPage.resendPasswordConfirmation));

		Assert.assertEquals(
				forgotPasswordPage.resendPasswordConfirmation.getText(),
				constants.FORGOT_PASSWORD_CONFIRMATION,
				"Expected confirmation message is not displayed");
		JoinNowHelper joinNowHelper = new JoinNowHelper();

		joinNowHelper.closeDialog();
	}

	public void verifyForgotPasswordError() {
		wait.until(ExpectedConditions
				.visibilityOf(forgotPasswordPage.errorMessage));
		Assert.assertEquals(forgotPasswordPage.errorMessage.getText(),
				constants.EMAIL_ERROR, "Error message is not displayed");

	}
}
