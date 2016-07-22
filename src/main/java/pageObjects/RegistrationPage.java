package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	
	WebDriver driver;


	public RegistrationPage(WebDriver dr) {
		driver = dr;
	}
	
	
	public void setName(String Name){
		WebElement enter_name=driver.findElement(By.id("login_username")); 
		   enter_name.sendKeys("Dishant");
	}
	
	
	public void setEmail(String email){
		WebElement enter_email=driver.findElement(By.id("login_email")); 
		enter_email.sendKeys(email);
	}
	
	public void setPassword(String pwd){
		WebElement enter_pwd=driver.findElement(By.id("login_password")); 
		enter_pwd.sendKeys(pwd);
	}
	
	public void clickSubmit(){
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit_button")));
		WebElement signup=driver.findElement(By.id("submit_button"));
		signup.click();
	}
	
	public void assertSignUp(){
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_tag_image")));
		Assert.assertTrue((driver.findElement(By.id("search_tag_image")).isDisplayed()));
	}
	
	public void assertSignUpPage(){
		Assert.assertTrue((driver.findElement(By.id("submit_button")).isDisplayed()));
	}
}
