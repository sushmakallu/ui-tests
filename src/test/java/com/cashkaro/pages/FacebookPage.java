package com.cashkaro.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookPage {

	@FindBy(id = "email")
	public WebElement userName;

	@FindBy(id = "pass")
	public WebElement password;

	@FindBy(id = "u_0_2")
	public WebElement loginButton;

	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	public WebElement cancelButton;

	@FindBy(xpath = "//button[contains(text(),'Okay')]")
	public WebElement okayButton;

	@FindBy(linkText = "Edit the info you provide")
	public WebElement editLink;

	@FindBy(xpath = "//input[@value='email'][@type='checkbox']")
	public WebElement emailCheckbox;

	@FindBy(xpath = "//*[@id='u_0_5']/div[2]/div[1]/div/div/label[1]/div[1]")
	public WebElement publicProfileSection;

	@FindBy(xpath = "//*[@id='u_0_5']/div[2]/div[1]/div/div/label[2]/div[1]")
	public WebElement emailSection;

	@FindBy(xpath = "//*[@id='u_0_3']/div[1]/div/div/div/div[1]/div[1]/div[2]")
	public WebElement confirmationText;
	
	@FindBy(xpath = "//*[@id='u_0_3']/div[1]/div/div/div/div[1]/div[1]/div[1]/img")
	public WebElement cashkaroImage;
}
