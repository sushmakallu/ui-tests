package com.cashkaro.ui_tests;

//import static com.geae.automation.Payroll.test.TestScript.*;

import java.io.PrintStream;

public class AutomationException extends RuntimeException {
	private static final long serialVersionUID = -183778437175404190L;
	private String errorMsg;

	public AutomationException() {
	}

	public AutomationException(Throwable paramThrowable) {
		super(paramThrowable);
	}

	public AutomationException(String paramString) {
		System.out.println("Error Message : " + paramString);
		this.errorMsg = paramString;
		if (DriverScript.driver != null)
			TestReporting.captureSnapShot(DriverScript.driver);
	}

	public AutomationException(String paramString, boolean paramBoolean) {
		System.out.println("Error Message : " + paramString);
		this.errorMsg = paramString;
		if ((paramBoolean) && (DriverScript.driver != null))
			TestReporting.captureSnapShot(DriverScript.driver);
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String paramString) {
		this.errorMsg = paramString;
	}
}