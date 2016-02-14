package com.cashkaro.tests.JoinNow;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cashkaro.helpers.JoinNowHelper;

public class VerifyJoinFromWhiteDividerTest {

	JoinNowHelper joinNowHelper;

	@BeforeTest
	public void setup() {
		joinNowHelper = new JoinNowHelper();
	}

	// Disabled the test as captcha is enabled
	@Test(enabled = false, dataProvider = "data")
	public void verifyJoinFromWhiteDividerValidTest(String username,
			String email, String confirmEmail, String password) {

		joinNowHelper.navigateJoinNowFromWhiteDividerSection();
		joinNowHelper.verifyJoinNowFormElements();
		joinNowHelper.verifyJoinNowFormDefaultValues();

		joinNowHelper.verifyJoinNowWithValidInputs(username, email,
				confirmEmail, password);
		joinNowHelper.clickJoinNowButton();

	}

	@Test(priority=2)
	public void verifyJoinFromBannerSectionInvalidTest() {

		joinNowHelper.navigateJoinNowFromWhiteDividerSection();
		joinNowHelper.clickJoinNowButton();
		joinNowHelper.verifyJoinNowErrorValues();
		joinNowHelper.closeDialog();
	}

	@Test(priority=1)
	public void verifyJoinFormConfirmEmailErrorTest() {
		joinNowHelper.navigateJoinNowFromWhiteDividerSection();
		joinNowHelper.verifyConfirmEmailNotMatchingError("testuser@gmail.com",
				"newuser@gmail.com");
		joinNowHelper.closeDialog();

	}

	@DataProvider(name = "data")
	Object[][] loginData() {
		return new Object[][] {
				{ "testuser1", "test1@gmail.com", "test1@gmail.com", "test123" },
				{ "testuser2", "test2@gmail.com", "test2@gmail.com", "test123" } };

	}
	
}
