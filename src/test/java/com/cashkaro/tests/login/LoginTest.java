package com.cashkaro.tests.login;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cashkaro.constants.constants;
import com.cashkaro.helpers.SignInHelper;
import com.cashkaro.ui_tests.Utils;

public class LoginTest {

	SignInHelper signInHelper;

	@BeforeTest()
	public void setup() {
		signInHelper = new SignInHelper();
	}

	@Test(priority = 1)
	public void verifyLoginElementsTest() {
		signInHelper.verifyLoginElements();
	}

	@Test(priority = 1)
	public void verifyLoginTest() {
		JSONObject data = null;
		data = Utils.readContentFromJson(constants.USER_DATA, "user1");

		String username = data.get("username").toString();
		String password = data.get("password").toString();

		signInHelper.login(username, password);
		signInHelper.verifyHomepage();
		signInHelper.logout();
	}

	@Test(dataProvider = "data", priority = 2)
	public void verifyInvalidLoginTest(String username, String password) {
		signInHelper.verifySigninErrors(username, password);

	}

	@DataProvider(name = "data")
	Object[][] loginData() {
		return new Object[][] { { "abcd", "" }, { "", "abcdefg" }, { "", "" } };

	}

}
