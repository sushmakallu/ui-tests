package com.cashkaro.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by narendra on 02/02/2016.
 */
public class HomePage {

	

	@FindBy(xpath = "//section[contains(@class,'home_login')]/section[2]/p")
	public WebElement welcomeText;

	@FindBy(xpath = "//nav[contains(@class,'top_nav')]//li/a[contains(text(),'LOGOUT')]")
	public WebElement logout;

}
