package com.factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	
	public static ThreadLocal<RemoteWebDriver> tlDriver = new ThreadLocal<>();
	private static final ThreadLocal<WebDriverWait> wait = new  ThreadLocal<WebDriverWait>();

	public void setWait() {
		wait.set(new WebDriverWait(getDriver(), 30));
	}
	/**
	 * 
	 * @param browser
	 * @return
	 */
			
	public WebDriver init_driver(String browser) {
		System.out.println("browser valie is: " + browser);
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver()); // create a local driver copy for specific driver
		}
		else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} else if(browser.equals("safari")) {
		//	WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println(" Please pass the correct browser " + browser);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return getDriver();
	}
	
	/**
	 * This is used to get the driver with ThreadLocal. Synchronized is used to run parallel thread execution
	 * @return
	 */
	public static synchronized RemoteWebDriver getDriver() {
		return tlDriver.get();
	}
	
	public WebDriverWait getWait() {
		return wait.get();
	}
	
//	public abstract long takeSnap();
}
