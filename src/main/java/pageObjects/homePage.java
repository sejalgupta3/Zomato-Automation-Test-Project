package pageObjects;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;


public class homePage {
	
	WebDriver driver;
	
	// Locators
	By skip_Login = By.id("com.application.zomato:id/skipOrCancelLoginContainer");
	By login_button = By.id("com.application.zomato:id/layout_login");
	
	public homePage(WebDriver dr){
		driver = dr;
	}
		
	public void skipLogin(){
		driver.findElement(skip_Login).click();
	} 
	
	public void clickLogin(){
		driver.findElement(login_button).click();
	}
	
	public void clickSearch(){
        driver.findElement(By.id("com.application.zomato:id/icon_search")).click();
	}
	
	public void clickSignUp(){
		WebElement click_signup=driver.findElement(By.id("layout_signup"));
		click_signup.click();
	}
}
