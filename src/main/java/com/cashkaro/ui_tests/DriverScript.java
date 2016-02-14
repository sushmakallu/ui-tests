package com.cashkaro.ui_tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.cashkaro.constants.CashkaroConstants;
import com.cashkaro.constants.constants;

public class DriverScript {
	public static WebDriver driver = null;
	public static String winHandleBefore = null;
	public static Capabilities capabilities = null;
	public static Properties CONFIG = null;

	public static WebDriver getDriver() throws AutomationException {
		if (CONFIG == null) {
			CONFIG = new Properties();
			try {
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir")
								+ CashkaroConstants.CONFIG_PROPERTIES);
				CONFIG.load(fs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String browser = CONFIG.getProperty("browser");
		if (driver == null) {

			try {

				if (browser.equals("firefox")) {
					ProfilesIni profile = new ProfilesIni();
					FirefoxProfile fireFox = profile.getProfile("selenium");
					driver = new FirefoxDriver(fireFox);

				} else if (browser.equals("ie")) {

					System.setProperty("webdriver.ie.driver",
							CashkaroConstants.ieDriver);
					return driver = new InternetExplorerDriver();

				} else if (browser.equals("chrome")) {

					System.setProperty("webdriver.chrome.driver",
							CashkaroConstants.chromeDriver);
					return driver = new ChromeDriver();

				} else {
					throw new AutomationException(
							"Invalid Browser; Only IE, Firefox & Chrome supported",
							false);
				}
			} catch (RuntimeException localRuntimeException) {
				localRuntimeException.printStackTrace();
				throw new AutomationException("Unable to locate the driver",
						false);
			}
		}

		return driver;
	}

	public static void goToURL(String paramString) throws AutomationException {
		driver.get(paramString);
		driver.manage().timeouts().implicitlyWait(180L, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public static void closeBrowser() {
		driver.quit();
		driver = null;
	}

	public static By getLocator(String ObjectName) throws AutomationException {

		By locator = null;
		try {
			String str = null;
			String str2 = null;
			locator = getLocators(str2, str);
		} catch (NoSuchElementException e) {
			throw new AutomationException("Issue - getLocator");
		}
		return locator;
	}

	public static void switchToBrowser(String windowTitle)
			throws AutomationException {
		winHandleBefore = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> iter = windows.iterator();
		int i = 0;
		for (String win : windows) {

			driver.switchTo().window(win);
			driver.manage().window().maximize();
			if (driver.getTitle().equalsIgnoreCase(windowTitle)) {
				i = 1;
			}
		}

		if (i == 0)
			throw new AutomationException("The Browser Window with title : "
					+ windowTitle + " is not found");
	}

	public static void switchBackToOriginalBrowser() {
		driver.switchTo().window(winHandleBefore);
	}

	public static void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public static By getLocators(String paramString1, String paramString2) {
		if (paramString1.trim().equalsIgnoreCase("xpath"))
			return By.xpath(paramString2);
		if (paramString1.trim().equalsIgnoreCase("cssselector"))
			return By.cssSelector(paramString2);
		if (paramString1.trim().equalsIgnoreCase("tagname"))
			return By.tagName(paramString2);
		if (paramString1.trim().equalsIgnoreCase("id"))
			return By.id(paramString2);
		if (paramString1.trim().equalsIgnoreCase("name"))
			return By.name(paramString2);
		if (paramString1.trim().equalsIgnoreCase("linktext"))
			return By.linkText(paramString2);
		return null;
	}

	public static void sleep(float paramFloat) {
		long l1 = (long) (paramFloat * 1000.0F);
		long l2 = System.currentTimeMillis();
		while (l2 + l1 >= System.currentTimeMillis())
			;
	}

	public static void pressKey(String key) {
		try {
			Robot robot = new Robot();

			switch (key.toLowerCase()) {

			case "enter": {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				break;
			}
			case "tab": {
				System.out.println("Tab to press");
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				break;
			}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String convertDateFormate(String formatFrom, String formatTo) {
		try {
			SimpleDateFormat dateformate = new SimpleDateFormat("dd-MMM-yyyy");
			Date excelDateFormate = dateformate.parse(formatFrom);
			SimpleDateFormat out = new SimpleDateFormat(formatTo);
			String transDate = out.format(excelDateFormate);
			return transDate;
		} catch (Exception e) {
			return null;
		}

	}

}
