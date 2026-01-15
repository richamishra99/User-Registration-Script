package com.link.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.link.base.BaseTest;
import com.link.modules.LoginLcd;


public class LoginTestCases extends BaseTest {

	LoginLcd loginlcd = new LoginLcd();
	
	@Test(priority = 1)
	public void registerUser() throws InterruptedException {
		test = extent.createTest("Verify successfull registration of new user").assignCategory("Regression");
		loginlcd.ClickSignUpAndLogin();
		loginlcd.NewUserName();
		loginlcd.NewUserEmail();
		loginlcd.ClickSignUpButton();
		loginlcd.EnterCreatePassword();
		loginlcd.EnterFirstName();
		loginlcd.EnterLastName();
		loginlcd.EnterAddress();
		loginlcd.EnterState();
		loginlcd.EnterCity();
		loginlcd.EnterZipCode();
		loginlcd.EnterMobileNumber();
		loginlcd.ClickCreateAccount();
		Thread.sleep(2000);
		loginlcd.VerifyAccountCreation();
	
	}

	@Test(priority = 2)
	public void verifyExistingEmailValidation() {
		loginlcd.ClickSignUpAndLogin();
		loginlcd.NewUserName();
		loginlcd.NewUserEmail();
		loginlcd.ClickSignUpButton();
		loginlcd.ExistingEmailValidation();
	}

}
