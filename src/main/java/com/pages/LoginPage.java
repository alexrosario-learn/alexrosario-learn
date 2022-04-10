package com.pages;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.utillities.DBConnectionUtil;

import org.openqa.selenium.WebDriver;

public class LoginPage {

	/*
	 * locators
	 */
	private By userName = By.xpath("//input[@name='SWEUserName']");
	private By password = By.xpath("//input[@name='SWEPassword']");
	private By btnLogin = By.xpath("//a[contains(@onclick,'SWEExecuteLogin')]");
	private WebDriver driver;
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
		
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public void enterUserName(String uName) {
		driver.findElement(userName).sendKeys(uName);
	}
	
	public void enterPassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}

	public void clickOnLogin() {
		driver.findElement(btnLogin).click();
	}
	
	public void waitForApperance(WebElement element) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			System.out.println("Element did not appear after 20 seconds");
		}
	}
	
	public void waitForPageLoad() {
		JavascriptExecutor j = (JavascriptExecutor) driver;
	      j.executeScript("return document.readyState")
	      .toString().equals("complete");
	}
	
	public HomePage doLogin(String uname, String pwd) {
		driver.findElement(userName).sendKeys(uname);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(btnLogin).click();
		return new HomePage(driver);
	}
	
}
