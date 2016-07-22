package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class searchPage {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, 200);
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Allow")));

	public searchPage(WebDriver dr) {
		driver = dr;
	}
	
	public void clickDropDown(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/down_arrow_search_suggestion")));
		driver.findElement(By.id("com.application.zomato:id/down_arrow_search_suggestion")).click();
	}
	
	public void searchCity(String city){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/search_city")));
        driver.findElement(By.id("com.application.zomato:id/search_city")).sendKeys(city);
	}
	
	
	public void assertUserLocation(String location){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/all_places_textview")));
        assert driver.findElement(By.id("com.application.zomato:id/all_places_textview")).getText().equals(location);
	}
	
	
	public void enterRestaurantName(String restaurant){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/search_edit_text")));
        driver.findElement(By.id("com.application.zomato:id/search_edit_text")).sendKeys(restaurant);
	}
	
	
	public void clickCategoryName(){
		driver.findElement(By.id("com.application.zomato:id/category_name")).click();
	}
	
	
	public void clickReviewsContainer(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/rating_and_reviews_container")));
        driver.findElement(By.id("com.application.zomato:id/rating_and_reviews_container")).click();
	}
	
	
	public void assertFirstResult(String result){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/rest_name")));
        assert driver.findElement(By.id("com.application.zomato:id/rest_name")).getText().equals(result);
	}
	

}
