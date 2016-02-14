package com.cashkaro.tests.JoinNow;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cashkaro.helpers.JoinNowHelper;
import com.cashkaro.helpers.SignInHelper;

public class JoinWithFacebookTest {

	JoinNowHelper joinNowHelper;
	SignInHelper signInHelper;

	@BeforeTest
	public void setup() {
		joinNowHelper = new JoinNowHelper();
		signInHelper = new SignInHelper();
	}

	@Test(enabled= false, priority = 1, dataProvider = "data")
	void verifyJoinWithFacebookTest(String username, String password) {

		joinNowHelper.navigateJoinNowFromBannerSection();
		joinNowHelper.verifyJoinOkayWithFacebook(username, password);
		signInHelper.logout();

	}

	@Test(enabled = false, priority = 2)
	void verifyJoinWithFacebookCancelTest() {
		joinNowHelper.navigateJoinNowFromBannerSection();
		joinNowHelper.verifyJoinCancelWithFacebook("test123@gmail.com", "password");

	}

	@DataProvider(name = "data")
	Object[][] loginData() {
		return new Object[][] { { "abcd@gmail.com", "abc123" },
				{ "test@gmail.com", "test123" } };

	}
}
