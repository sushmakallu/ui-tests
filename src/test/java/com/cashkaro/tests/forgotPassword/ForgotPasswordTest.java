package com.cashkaro.tests.forgotPassword;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cashkaro.helpers.ForgotPasswordHelper;

/**
 * Created by narendra on 02/02/2016.
 */
public class ForgotPasswordTest {

	ForgotPasswordHelper forgotPasswordHelper;

	@BeforeTest
	public void setup() {
		forgotPasswordHelper = new ForgotPasswordHelper();
	}

	@Test(priority = 1)
	public void verifyForgotPasswordTest() {

		forgotPasswordHelper.verifyForgotPassword("abc@gmail.com");
		forgotPasswordHelper.verifyForgotPasswordConfirmation();
	}

	@Test(priority = 2)
	public void verifyForgotPasswordErrorTest() {
		forgotPasswordHelper.verifyForgotPassword("");
		forgotPasswordHelper.verifyForgotPasswordError();
	}

}
