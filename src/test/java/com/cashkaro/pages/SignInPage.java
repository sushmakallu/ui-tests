package com.cashkaro.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by narendra on 02/02/2016.
 */
public class SignInPage {

	@FindBy(xpath = "//div[@class='header_top']//li/a[contains(text(),'SIGN IN')]")
	public WebElement signIn;

	@FindBy(xpath = "//iframe[@class='cboxIframe']")
	public WebElement signInFrame;

	@FindBy(id = "signin_form")
	public WebElement signinForm;

	@FindBy(id="close_and_go_fb")
	public WebElement facebookSigninButton;
	
	@FindBy(id = "uname")
	public WebElement userName;

	@FindBy(id = "pwd-txt")
	public WebElement password;

	@FindBy(id="rem_user")
	public WebElement rememberUser;
	
	@FindBy(xpath = "//*[@id='signin_form']/div/span[@class='fl']")
	public WebElement keepSignedIn;
	
	@FindBy(partialLinkText = "Forgot Password")
	public WebElement forgotPasswordLink;

	@FindBy(id = "login_msg")
	public WebElement errorMessage;
	
	@FindBy(id="sign_in")
	public WebElement signInButton;

}
