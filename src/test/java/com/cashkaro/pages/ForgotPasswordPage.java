package com.cashkaro.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by narendra on 02/02/2016.
 */
public class ForgotPasswordPage {

	@FindBy(xpath = "//iframe[@class='cboxIframe']")
	public WebElement forgotPasswordFrame;

	@FindBy(id = "from_email")
	public WebElement email;

	@FindBy(id = "submit_req")
	public WebElement submitButton;

	@FindBy(xpath = "//div[@class='f_pass_s']/p")
	public WebElement resendPasswordConfirmation;

	@FindBy(id = "cboxClose")
	public WebElement closeDialog;
	
	@FindBy(id = "login_msg")
	public WebElement errorMessage;

}
