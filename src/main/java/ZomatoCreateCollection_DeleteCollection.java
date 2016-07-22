

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ZomatoCreateCollection_DeleteCollection {

	// creating the object of config.properites file
	public static Properties CONFIG = new Properties();
	AppiumDriver dr;

	// launching the app with basic initial set up
	public void LaunchingZomato() throws IOException {


		InputStream fn =  getClass().getClassLoader().getResourceAsStream("deepa.properties");
		CONFIG.load(fn);
		File app = new File("/Users/rbathej/SJSU/287/GroupProject/Zomato.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.BROWSER_NAME, " ");
		cap.setCapability("deviceName", CONFIG.getProperty("DeviceName"));
		cap.setCapability("platformVersion", CONFIG.getProperty("PlatformVersion"));
		cap.setCapability("platformName", CONFIG.getProperty("PlatformName"));
		cap.setCapability("app", app.getAbsolutePath());
		cap.setCapability("appPackage", CONFIG.getProperty("AppPackage"));

		dr = new AndroidDriver(new URL(CONFIG.getProperty("URLADD")), cap);
		dr.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		/*dr.findElement(By.id("android:id/content"));
		dr.findElement(By.id("android:id/button1")).click();
		dr.findElement(By.id("com.application.zomato:id/search_city")).sendKeys(CONFIG.getProperty("City"));
		dr.findElement(By.id("com.application.zomato:id/city_name")).click();
		dr.findElement(By.id("com.application.zomato:id/skipOrCancelLoginContainer")).click();*/

		WebDriverWait wait = new WebDriverWait(dr, 200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Allow")));

		// click allow
		dr.findElement(By.name("Allow")).click();

		// Click 'Skip Login'
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/skipLoginText")));
		dr.findElement(By.id("com.application.zomato:id/skipLoginText")).click();
	}

	@Test
	/*
	 * Test Case to Create Collection and verify the created collection by the
	 * phone number of the restaurant for which the Collection has been created
	 */

	public void CreateCollectionTest() throws IOException {
		LaunchingZomato();
		// collection tab
		dr.findElement(By.id("com.application.zomato:id/collections_tab_button")).click();
		dr.findElement(By.id("com.application.zomato:id/card_button")).click();
		// login to create collection
		dr.findElement(By.id("com.application.zomato:id/layout_login")).click();
		// enter username
		dr.findElement(By.id("com.application.zomato:id/login_email")).sendKeys(CONFIG.getProperty("LoginEmail"));
		// enter password
		dr.findElement(By.id("com.application.zomato:id/login_password")).sendKeys(CONFIG.getProperty("LoginPassword"));

		// scroll up to reach Login Button
		dr.scrollTo(CONFIG.getProperty("LoginButton"));
		dr.findElement(By.id("com.application.zomato:id/submit_button")).click();
		// create collection button

		dr.findElement(By.id("com.application.zomato:id/card_button")).click();
		// create collection steps
		dr.findElement(By.id("com.application.zomato:id/new_collection_name"))
				.sendKeys(CONFIG.getProperty("CollectionName"));
		dr.findElement(By.id("com.application.zomato:id/new_collection_description"))
				.sendKeys("Place where you can get breakfast before you woke up");
		dr.findElement(By.id("com.application.zomato:id/new_collection_tags"))
				.sendKeys(CONFIG.getProperty("CollectionTags"));
		dr.findElement(By.id("com.application.zomato:id/create_collection_next")).click();
		// search restaurant name
		dr.findElement(By.id("com.application.zomato:id/add_restaurants_searchbox"))
				.sendKeys(CONFIG.getProperty("RestaurantName"));

		// check box
		dr.findElement(By.id("com.application.zomato:id/checkbox_icon")).click();
		// done
		dr.findElement(By.id("com.application.zomato:id/create_collection")).click();
		// publish collection
		dr.findElement(By.id("com.application.zomato:id/create_collection")).click();
		String ExpectedIhopPhone = CONFIG.getProperty("ExpectedPhoneNum");
		// phone call
		dr.findElement(By.id("com.application.zomato:id/call_icon")).click();
		/*String ActualIhopPhone = dr.findElement(By.id("android:id/text1")).getText();
		// Test case will fail if Phone Numbers are different
		// ExpectedIhopPhone is taken from Coffee Chai link
		Assert.assertEquals(ActualIhopPhone, ExpectedIhopPhone, "Create Collection Passed");
		// clicking the cancel buttion
		dr.findElement(By.id("android:id/button2")).click();*/
		//dr.quit();

	}

	@Test
	/*
	 * Test case to delete the already created collection in the preveious step
	 * and by verifying that the the loaded page should not contain the user
	 * name
	 */
	public void DeleteCollectionTest() throws IOException {
		LaunchingZomato();
		// clicking the Account tab
		dr.findElement(By.id("com.application.zomato:id/account_tab_button")).click();
		dr.findElement(By.id("com.application.zomato:id/customlabel_cell_horizontal_house")).click();
		// login
		dr.findElement(By.id("com.application.zomato:id/layout_login")).click();
		dr.findElement(By.id("com.application.zomato:id/login_email")).sendKeys(CONFIG.getProperty("LoginEmail"));
		dr.findElement(By.id("com.application.zomato:id/login_password")).sendKeys(CONFIG.getProperty("LoginPassword"));
		dr.scrollTo(CONFIG.getProperty("LoginButton"));
		dr.findElement(By.id("com.application.zomato:id/submit_button")).click();
		// clicking the Collections tab
		dr.findElement(By.id("com.application.zomato:id/collections_tab_button")).click();
		// find the collections created by mer (user) by clicking the Mine tab
		dr.findElement(By.name(CONFIG.getProperty("MineCollection"))).click();
		// click the collection
		dr.findElement(By.id("com.application.zomato:id/collection_grid_item_title")).click();
		// edit collection
		dr.findElement(By.id("com.application.zomato:id/icon_edit_collection")).click();
		// click Delete collection
		dr.findElement(By.id("com.application.zomato:id/customlabel_cell_horizontal_house")).click();
		// confirm Delete
		dr.findElement(By.id("android:id/button1")).click();
		// verify
		Assert.assertFalse(isTextPresent(CONFIG.getProperty("CollectionName")), "Collection Deleted");

	}

	// whether a particular string is present or not
	boolean isTextPresent(String text) {
		try {
			boolean b = dr.getPageSource().contains(text);
			return b;
		} catch (Exception e) {
			return false;
		}
	}

	@AfterClass
	public void teardown() {
		//close the app
		dr.quit();
	}


}
