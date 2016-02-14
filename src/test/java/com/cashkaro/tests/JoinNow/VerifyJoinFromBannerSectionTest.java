package com.cashkaro.tests.JoinNow;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cashkaro.helpers.JoinNowHelper;
import com.cashkaro.pages.JoinNowPage;

public class VerifyJoinFromBannerSectionTest {

	JoinNowHelper joinNowHelper;

	@BeforeTest
	public void setup() {
		joinNowHelper = new JoinNowHelper();
	}

	// Disabled the test as captcha is enabled
	@Test(enabled = false, dataProvider = "data")
	public void verifyJoinFromBannerSectionValidTest(String username,
			String email, String confirmEmail, String password) {
		joinNowHelper.navigateJoinNowFromBannerSection();
		joinNowHelper.verifyJoinNowFormElements();
		joinNowHelper.verifyJoinNowFormDefaultValues();
		joinNowHelper.verifyJoinNowWithValidInputs(username, email,
				confirmEmail, password);
		joinNowHelper.clickJoinNowButton();

	}

	@Test
	public void verifyJoinFromBannerSectionInvalidTest() {

		joinNowHelper.navigateJoinNowFromBannerSection();
		joinNowHelper.clickJoinNowButton();
		joinNowHelper.verifyJoinNowErrorValues();

	}

	@Test
	public void verifyJoinFormConfirmEmailErrorTest() {
		joinNowHelper.navigateJoinNowFromBannerSection();
		joinNowHelper.verifyConfirmEmailNotMatchingError("testuser@gmail.com",
				"newuser@gmail.com");
	}
	
	@DataProvider(name = "data")
	Object[][] loginData() {
		return new Object[][] { { "testuser1", "test1@gmail.com", "test1@gmail.com", "test123"}, { "testuser2", "test2@gmail.com", "test2@gmail.com", "test123"} };

	}
}
