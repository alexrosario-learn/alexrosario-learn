package com.pages;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utillities.DBConnectionUtil;

public class HomePage extends BasePage{
	/*
	 * locators
	 */
	
	private WebDriver driver;
	WebDriverWait wait;
	public static String kpkNumber;
	
	@FindBy(how = How.XPATH, using = "//select[@aria-label='First Level View Bar']")
	WebElement firstdropDown;
	
	private By firstLevelView = By.xpath("//select[@aria-label='First Level View Bar']");
	private By qrCode = By.xpath("//input[@aria-label='QR Code']");
	private By btn_Go = By.xpath("//button[@title='Search:Go']");
	private By lnk_partnerId = By.xpath("//td[text()='Active']/../td[2]/a[@name='Partner Id']");
	private By fourthLevelViewBar = By.xpath("//select[@aria-label='Fourth Level View Bar']");
	private By PretupsBalance = By.xpath("//td[text()='ETOPUP']/preceding-sibling::td[1]");
	private By subscriptionMenu = By.xpath("//a[text()='Subscriptions']");
	private By searchNewStarterPack = By.xpath("//a[text()='Search Screen for New Starter Pack']");
	private By searchMSISDN = By.xpath("//input[@name='s_1_1_1_0']");
	private By goSearchButton = By.xpath("//button[@ id='s_1_1_0_0_Ctrl']");
	private By pricePlanName = By.xpath("//input[@name='s_1_1_23_0']");
	 
	 
	
	public HomePage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	}

	public boolean verifyTitle(String expectedtitle) {
		String actualTItle = driver.getTitle();
		if(expectedtitle.equals(actualTItle)) {
			System.out.println("User is on Home Page");
			return true;
		} else {
			System.out.println("USer is not on Home page");
		}
		return false;
	}

	public String getMsisdn() throws ClassNotFoundException, SQLException {
		DBConnectionUtil msisdn = new DBConnectionUtil();
		   String MSD = msisdn.getData();
		   System.out.println("The output KPK number is" + MSD);
		   return MSD;
	}

	public void verifyPreTupsBalance(String qrcode) throws InterruptedException {
		this.selectAngiePRM();
		this.searchQRcode(qrcode);
		this.selectPreTUpBalance();
		this.getPretupsBalance();
	}

	private void getPretupsBalance() {
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(PretupsBalance)));
		String pretupBal = driver.findElement(PretupsBalance).getText();
		System.out.println("Pretups retailer balance is "+ pretupBal);
	}

	private void selectPreTUpBalance() {
		
		Select preElm = new Select(driver.findElement(fourthLevelViewBar));
		preElm.selectByValue("tabView15");
		System.out.println(" pretups balance has been slected");
	}

	private void searchQRcode(String qrcode) {
		System.out.println("inside Search QR code");
		//driver.findElement(qrCode).clear();
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(qrCode)));
		driver.findElement(qrCode).click();
		driver.findElement(qrCode).sendKeys(qrcode);
		driver.findElement(btn_Go).click();
		driver.findElement(lnk_partnerId).click();
		System.out.println("Search QR code has been success");
		
		 
	}

	private void selectAngiePRM() throws InterruptedException {
		//driver.findElement(firstLevelView).click();
		Thread.sleep(5000);
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(firstLevelView)));
		Select element = new Select(driver.findElement(firstLevelView));
		element.selectByValue("tabScreen42");
	}

	public String fetchKPKNumFromDB() {
		try {
			kpkNumber = this.getMsisdn();
			System.out.println("KPK number from DB " + kpkNumber);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kpkNumber;
	}

	public void VerifyPricePlanName(String KpKNumber) {
		 driver.findElement(subscriptionMenu).click();
		 driver.findElement(searchNewStarterPack).click();
		 driver.findElement(searchMSISDN).clear();
		 driver.findElement(searchMSISDN).sendKeys(KpKNumber);
		 driver.findElement(goSearchButton).click();
		 String getPriceplanName = driver.findElement(pricePlanName).getText();
		 if(getPriceplanName.equals("Zero Air Time")) {
			 System.out.println("price plan name is Zero Air TIme as expected");
		 } else {
			 System.out.println("price plan name is not Zero Air TIme");
		 }
		 
		
	}
}
