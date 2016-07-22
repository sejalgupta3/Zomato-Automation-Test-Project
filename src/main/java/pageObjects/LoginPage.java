package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	// Locators
	
	By login_id = By.id("com.application.zomato:id/login_email");
	By pwd = By.id("com.application.zomato:id/login_password");
	By login_button = By.id("com.application.zomato:id/submit_button");
	By profile_button = By.id("com.application.zomato:id/dineline_tab_button");

	WebDriver driver;
	public LoginPage(WebDriver dr) {
		driver = dr;
	}
	
	
	
	public void login(String id, String p){
		WebDriverWait wait = new WebDriverWait(driver, 200);
		driver.findElement(login_id).sendKeys(id);
		driver.findElement(pwd).sendKeys(p);
		wait.until(ExpectedConditions.visibilityOfElementLocated(login_button));
		driver.findElement(login_button).click();
		
	}
	
	public boolean assertLoginPage(){
		timelapse();
		return driver.findElement(login_id).isDisplayed();
	}
	
	public void assertSuccessfulLogin(){
		timelapse();
		driver.findElement(profile_button).isDisplayed();
	}
	
	
	public void timelapse(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
