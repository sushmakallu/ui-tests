package com.cashkaro.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cashkaro.constants.constants;
import com.cashkaro.pages.FacebookPage;
import com.cashkaro.pages.JoinNowPage;
import com.cashkaro.ui_tests.DriverScript;

public class JoinNowHelper extends DriverScript {

	WebDriver driver = getDriver();
	JoinNowPage joinNowPage = PageFactory.initElements(driver,
			JoinNowPage.class);
	FacebookPage facebookPage = PageFactory.initElements(driver,
			FacebookPage.class);
	WebDriverWait wait = new WebDriverWait(driver, 10);

	public void navigateJoinNowFromTopNav() {
		goToURL(constants.BASE_URL);
		joinNowPage.joinFreeTopNav.click();
		wait.until(ExpectedConditions.visibilityOf(joinNowPage.joinForm));
	}

	public void navigateJoinNowFromBannerSection() {
		goToURL(constants.BASE_URL);
		joinNowPage.joinFreeBannerSection.click();
		wait.until(ExpectedConditions.visibilityOf(joinNowPage.joinForm));
	}

	public void navigateJoinNowFromWhiteDividerSection() {
		goToURL(constants.BASE_URL);
		joinNowPage.joinFreeWhiteDividerSection.click();
		wait.until(ExpectedConditions
				.frameToBeAvailableAndSwitchToIt(joinNowPage.joinNowFrame));
	}

	public void verifyJoinWithFacebook(String username, String password) {
		driver.manage().deleteAllCookies();
		joinNowPage.facebookButton.click();

		FacebookHelper facebookHelper = new FacebookHelper();
		facebookHelper.loginFacebook(username, password);
		wait.until(ExpectedConditions.textToBePresentInElement(
				facebookPage.confirmationText, constants.FACEBOOK_CONFIRMATION));
	}

	public void verifyJoinWithFacebookConfirmation() {

		facebookPage.publicProfileSection.isDisplayed();
		facebookPage.emailSection.isDisplayed();

		facebookPage.emailCheckbox.isEnabled();

		facebookPage.cancelButton.isEnabled();
		facebookPage.okayButton.isEnabled();
	}

	public void verifyJoinOkayWithFacebook(String username, String password) {

		verifyJoinWithFacebook(username, password);
		verifyJoinWithFacebookConfirmation();
		facebookPage.okayButton.click();
		switchBackToOriginalBrowser();
	}

	public void verifyJoinCancelWithFacebook(String username, String password) {
		verifyJoinWithFacebook(username, password);
		verifyJoinWithFacebookConfirmation();
		facebookPage.cancelButton.click();
	}

	public void verifyJoinNowFormElements() {

		Assert.assertTrue(joinNowPage.firstName.isDisplayed(),
				"First Name is not displayed");
		Assert.assertTrue(joinNowPage.email.isDisplayed(),
				"Email is not displayed");
		Assert.assertTrue(joinNowPage.confirmEmail.isDisplayed(),
				"Confirm email is not displayed");
		Assert.assertTrue(joinNowPage.terms.isDisplayed(),
				"Terms and conditions is not displayed");
		Assert.assertTrue(joinNowPage.joinButton.isEnabled(),
				"Join now buttons is not enabled");
	}

	public void verifyJoinNowFormDefaultValues() {

		wait.until(ExpectedConditions.visibilityOf(joinNowPage.joinForm));

		Assert.assertEquals(joinNowPage.firstName.getAttribute("value"),
				constants.FULLNAME, "Full Name label is not matching");
		Assert.assertEquals(joinNowPage.email.getAttribute("value"),
				constants.EMAIL, "Email label is not matching");
		Assert.assertEquals(joinNowPage.confirmEmail.getAttribute("value"),
				constants.CONFIRM_EMAIL, "Confirm email label is not matching");
		Assert.assertEquals(joinNowPage.password.getAttribute("value"),
				constants.CHOOSE_PASSWORD, "Password label is not matching");
		Assert.assertEquals(joinNowPage.joinButton.getText(),
				constants.JOINBUTTON, "Join Button label is not matching");

	}

	public void clickJoinNowButton() {
		joinNowPage.joinButton.click();
	}

	public void verifyJoinNowErrorValues() {

		Assert.assertEquals(joinNowPage.firstName.getAttribute("value"),
				constants.FULLNAME_ERROR,
				"Full Name error label is not matching");
		Assert.assertEquals(joinNowPage.email.getAttribute("value"),
				constants.EMAIL_ERROR, "Email error label is not matching");
		Assert.assertEquals(joinNowPage.confirmEmail.getAttribute("value"),
				constants.EMAIL_ERROR,
				"Confirm Email error label is not matching");
		Assert.assertEquals(joinNowPage.password.getAttribute("value"),
				constants.PASSWORD_ERROR,
				"Password error label is not matching");
		Assert.assertEquals(joinNowPage.joinButton.getText(),
				constants.JOINBUTTON, "Join button error label is not matching");
	}

	public void verifyJoinNowWithValidInputs(String firstname, String email,
			String confirmEmail, String password) {

		joinNowPage.firstName.sendKeys(firstname);
		joinNowPage.email.sendKeys(email);
		joinNowPage.confirmEmail.sendKeys(confirmEmail);
		joinNowPage.password.sendKeys(password);
		joinNowPage.terms.click();
		joinNowPage.joinButton.click();

	}

	public void verifyJoinNowWithBlankInputs() {
		verifyJoinNowErrorValues();
		Assert.assertTrue(joinNowPage.termsError.isDisplayed(),
				"Terms error message is displayed");
		Assert.assertEquals(joinNowPage.termsError.getText(),
				constants.TERMS_ERROR,
				"Terms and conditions error message is not matching");
	}

	public void verifyConfirmEmailNotMatchingError(String email,
			String confirmEmail) {
		joinNowPage.email.sendKeys(email);
		joinNowPage.confirmEmail.sendKeys(confirmEmail);
		joinNowPage.password.clear();

		Assert.assertEquals(joinNowPage.confirmEmail.getAttribute("value"),
				constants.EMAIL_NOT_MATCHING_ERROR,
				"Email matching error message is not expected");

	}

	public void closeDialog() {
		driver.switchTo().defaultContent();
		joinNowPage.closeJoinNowFrame.click();

	}
}
