package com.cashkaro.tests.JoinNow;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cashkaro.helpers.JoinNowHelper;

public class VerifyJoinFromTopNavTest {

	JoinNowHelper joinNowHelper;

	@BeforeTest
	public void setup() {
		joinNowHelper = new JoinNowHelper();
	}

	// Disabled the test as captcha is enabled
	@Test(enabled = false, dataProvider = "data")
	public void verifyJoinFromTopNavValidTest(String username, String email,
			String confirmEmail, String password) {

		joinNowHelper.navigateJoinNowFromTopNav();
		joinNowHelper.verifyJoinNowFormElements();
		joinNowHelper.verifyJoinNowFormDefaultValues();
		joinNowHelper.verifyJoinNowWithValidInputs(username, email,
				confirmEmail, password);
		joinNowHelper.clickJoinNowButton();

	}

	@Test
	public void verifyJoinFromBannerSectionInvalidTest() {

		joinNowHelper.navigateJoinNowFromTopNav();
		joinNowHelper.clickJoinNowButton();
		joinNowHelper.verifyJoinNowErrorValues();

	}

	@Test
	public void verifyJoinFormConfirmEmailErrorTest() {

		joinNowHelper.navigateJoinNowFromTopNav();
		joinNowHelper.verifyConfirmEmailNotMatchingError("testuser@gmail.com",
				"testuser@rediffmail.com");

	}

	@DataProvider(name = "data")
	Object[][] loginData() {
		return new Object[][] {
				{ "testuser1", "test1@gmail.com", "test1@gmail.com", "test123" },
				{ "testuser2", "test2@gmail.com", "test2@gmail.com", "test123" } };

	}

}
