package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class editProfile {
	
	WebDriver driver;

	public editProfile(WebDriver dr) {
		driver = dr;
	}
	
	
	public void setName(String Name){
		driver.findElement(By.id("com.application.zomato:id/edit_username")).sendKeys(Name);
	}
	
	public void setCity(String city){
		driver.findElement(By.id("com.application.zomato:id/edit_city")).click();
        driver.findElement(By.id("com.application.zomato:id/search_city")).sendKeys(city);
	}

	
	public void setBio(String bio){
		driver.findElement(By.id("com.application.zomato:id/edit_bio")).sendKeys(bio);
	}
	
	public void setPhone(String phone){
		driver.findElement(By.id("com.application.zomato:id/edit_phonenumber")).sendKeys(phone);
	}
	
	public void clickProceed(){
		 driver.findElement(By.id("com.application.zomato:id/tick_proceed_icon")).click();
	}
	
	
	
}
