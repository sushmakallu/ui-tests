package com.cashkaro.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cashkaro.pages.FacebookPage;
import com.cashkaro.pages.JoinNowPage;
import com.cashkaro.ui_tests.DriverScript;

public class FacebookHelper extends DriverScript {

	WebDriver driver = getDriver();
	JoinNowPage joinNowPage = PageFactory.initElements(driver,
			JoinNowPage.class);
	FacebookPage facebookPage = PageFactory.initElements(driver,
			FacebookPage.class);
	WebDriverWait wait = new WebDriverWait(driver, 10);
		
		public void loginFacebook(String username, String password){
		switchToBrowser("Facebook");
		facebookPage.userName.clear();
		facebookPage.userName.sendKeys(username);
		facebookPage.password.sendKeys(password);
		facebookPage.loginButton.click();
	}
	
}
