package com.link.modules;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.link.base.BaseTest;

public class LoginLcd extends BaseTest {

	public void ClickSignUpAndLogin(){
		WebElement signUpAndLogin = driver.findElement(By.linkText(loc.getProperty("SIGNUP_LOGIN")));
		signUpAndLogin.click();
	}

	//Mandatory fields are only created below

	public void NewUserName(){
		WebElement newUserName = driver.findElement(By.xpath(loc.getProperty("NEW_USER_SIGNUP_NAME")));
		newUserName.sendKeys(config.getProperty("enterNewUserName"));
	}


	public void NewUserEmail(){
		WebElement newUserEmail = driver.findElement(By.xpath(loc.getProperty("NEW_USER_EMAIL")));
		newUserEmail.sendKeys(config.getProperty("enterNewUserEmail"));
	}

	public void ClickSignUpButton(){
		WebElement clickSignUpButton = driver.findElement(By.xpath(loc.getProperty("SIGN_UP_BUTTON")));
		clickSignUpButton.click();
	}

	public void EnterCreatePassword(){
		WebElement enterFullName = driver.findElement(By.xpath(loc.getProperty("ENTER_CREATE_PASSWORD")));
		enterFullName.sendKeys(config.getProperty("createPassword"));
	}

	public void EnterFirstName(){
		WebElement enterFirstName = driver.findElement(By.xpath(loc.getProperty("ENTER_FIRST_NAME")));
		enterFirstName.sendKeys(config.getProperty("enterFirstName"));
	}

	public void EnterLastName(){
		WebElement enterLastName = driver.findElement(By.xpath(loc.getProperty("ENTER_LAST_NAME")));
		enterLastName.sendKeys(config.getProperty("enterLastName"));
	}

	public void EnterAddress(){
		WebElement enterAddress = driver.findElement(By.xpath(loc.getProperty("ENTER_ADDRESS")));
		enterAddress.sendKeys(config.getProperty("enterAddress"));
	}

	public void EnterState(){
		WebElement enterState = driver.findElement(By.xpath(loc.getProperty("ENTER_STATE")));
		enterState.sendKeys(config.getProperty("enterState"));
	}

	public void EnterCity(){
		WebElement enterCity = driver.findElement(By.xpath(loc.getProperty("ENTER_CITY")));
		enterCity.sendKeys(config.getProperty("enterCity"));
	}

	public void EnterZipCode(){
		WebElement enterZipCode = driver.findElement(By.xpath(loc.getProperty("ENTER_ZIPCODE")));
		enterZipCode.sendKeys(config.getProperty("enterZipCode"));
	}

	public void EnterMobileNumber(){
		WebElement enterMobileNumber = driver.findElement(By.xpath(loc.getProperty("ENTER_MOBILE_NUMBER")));
		enterMobileNumber.sendKeys(config.getProperty("enterMobileNumber"));
	}

	public void ClickCreateAccount(){
		WebElement clickCreateAccount = driver.findElement(By.xpath(loc.getProperty("CREATE_ACCOUNT_BUTTON")));
		clickCreateAccount.click();
	}

	public void VerifyAccountCreation(){
		String extectedUrl = config.getProperty("automationCreatedAccountUrl");      //For successfull creation of account validation
		String actualUrl = driver.getCurrentUrl();
		try{
			Assert.assertEquals(actualUrl, extectedUrl);
			test.pass("Redirected to LCD Dashboard page successfully");
		} catch (AssertionError e) {
			test.fail("Unable to redirect on LCD Dashboard page");
		}
		
	}

	public void ExistingEmailValidation(){
		WebElement existingEmailError = driver.findElement(By.xpath(loc.getProperty("EMAIL_ALREADY_EXISTS")));
		String actualError = existingEmailError.getText();
		Assert.assertEquals(actualError, "Email Address already exist!","Validation message mismatch!");
	}

}
