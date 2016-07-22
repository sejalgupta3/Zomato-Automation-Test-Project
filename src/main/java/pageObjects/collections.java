package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class collections {
	
	// Locators
	By collectionstab = By.id("com.application.zomato:id/collections_tab_button");
	By createCollectionsButton = By.id("com.application.zomato:id/card_button");
	
	
	WebDriver driver;
	
	public collections(WebDriver dr) {
		driver = dr;
	}
	
	
	public void navigateToCollections(){
		driver.findElement(collectionstab).click();
	}
	
	public void clickCreateCollection(){
		driver.findElement(createCollectionsButton).click();
	}
	
	public void setCollectionName(){
		driver.findElement(By.id("com.application.zomato:id/new_collection_name")).sendKeys("Early Coffee");
	}
	
	public void setCollectionDescription(){
		driver.findElement(By.id("com.application.zomato:id/new_collection_description"))
		.sendKeys("Place where you can get breakfast before you woke up");
	}
	
	
	public void setCollectionTags(){
		driver.findElement(By.id("com.application.zomato:id/new_collection_tags")).sendKeys("#Morning#Exciting#Coffee");
	}
	
	
	public void clickNextOnCollection(){
		driver.findElement(By.id("com.application.zomato:id/create_collection_next")).click();
	}
	
	
	public void addRestaurant(){
		driver.findElement(By.id("com.application.zomato:id/add_restaurants_searchbox")).sendKeys("Coffee Chai");
	}
	
	
	public void checkCheckbox(){
		driver.findElement(By.id("com.application.zomato:id/checkbox_icon")).click();
	}
	
	
	public void collectionClickDone(){
		driver.findElement(By.id("com.application.zomato:id/create_collection")).click();
	}
	
	
	public void publishCollection(){
		driver.findElement(By.id("com.application.zomato:id/create_collection")).click();
	}
	
	
	public void collectionPhoneCall(){
		driver.findElement(By.id("com.application.zomato:id/call_icon")).click();
	}
	
	
	public void getActualPhoneNumber(){
		driver.findElement(By.id("android:id/text1")).getText(); 
	}
	
	
}
