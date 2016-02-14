package com.cashkaro.tests.login;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cashkaro.helpers.FacebookHelper;
import com.cashkaro.helpers.SignInHelper;

public class LoginWithFacebookTest {

	SignInHelper signInHelper;

	@BeforeTest
	public void setup() {
		signInHelper = new SignInHelper();
	}

	@Test
	public void loginTest() {

		signInHelper.loginWithFacebook("narenqa@rediffmail.com", "narenqa123");
		signInHelper.verifyHomepage();
		signInHelper.logout();

	}

}
