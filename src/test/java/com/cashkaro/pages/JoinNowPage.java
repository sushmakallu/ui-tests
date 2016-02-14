package com.cashkaro.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JoinNowPage {

	@FindBy(xpath = "//section[contains(@class,'white_divider')]/nav/ul/li/a[contains(@href,'join-free-now')]")
	public WebElement joinFreeWhiteDividerSection;

	@FindBy(xpath = "//section[contains(@class,'banner_bg')]//a[contains(@href,'join-free-now')]")
	public WebElement joinFreeBannerSection;

	@FindBy(xpath = "//div[@class='header_top']/div/nav/ul/li[4]/a[contains(@href,'/join-free-now')]")
	public WebElement joinFreeTopNav;
	
	@FindBy(id="close_and_go_fb")
	public WebElement facebookButton;

	@FindBy(id = "join_free")
	public WebElement joinForm;

	@FindBy(xpath = "//iframe[@class='cboxIframe']")
	public WebElement joinNowFrame;

	@FindBy(id = "cboxClose")
	public WebElement closeJoinNowFrame;
	
	@FindBy(id = "firstname")
	public WebElement firstName;

	@FindBy(id = "email")
	public WebElement email;

	@FindBy(id = "con_email")
	public WebElement confirmEmail;

	@FindBy(id = "pwd-txt")
	public WebElement password;

	@FindBy(id = "terms_conditions")
	public WebElement terms;

	@FindBy(id = "tc")
	public WebElement termsError;

	@FindBy(id = "join_free_submit")
	public WebElement joinButton;

}
