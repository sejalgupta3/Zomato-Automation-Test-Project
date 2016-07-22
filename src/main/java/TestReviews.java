

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TakesScreenshot;

import java.text.DateFormat; 
import java.text.SimpleDateFormat; 
import java.util.Date;
import java.io.IOException;
import java.util.Properties;

public class TestReviews {

		public static Properties CONFIG = new Properties();
		DateFormat dateFormat;	
	    WebDriver driver;
	    public static Long sleepBetweenOperations = 4000L;
	    String destDir;

	    @BeforeClass
	    public void setUp() throws MalformedURLException, IOException {

	    	InputStream fn =  getClass().getClassLoader().getResourceAsStream("dhanya.properties");
			CONFIG.load(fn);	        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
	        System.out.println("Starting the Zomato App");
	        File app = new File("/Users/rbathej/SJSU/287/GroupProject/Zomato.apk");
	       
	        DesiredCapabilities cap = new DesiredCapabilities();
	        cap.setCapability(CapabilityType.BROWSER_NAME, "Android");
	        cap.setCapability("deviceName", "104ec392");
	        cap.setCapability("platformVersion", "4.4.4");
	        cap.setCapability("platformName", "Android");
	        cap.setCapability("app", app.getAbsolutePath());
			cap.setCapability("appPackage", "com.application.zomato");
			cap.setCapability("autoAcceptAlerts",true);

	        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	        if(!driver.findElements(By.id("android:id/content")).isEmpty())
	        {
	        driver.findElement(By.id("android:id/content"));
	        }
	        //on using the app for the first time, we need to 'select city'
			WebDriverWait wait = new WebDriverWait(driver, 200);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Allow")));

			// click allow
			driver.findElement(By.name("Allow")).click();

			 // if the
	        if(!driver.findElements(By.id("com.application.zomato:id/search_city")).isEmpty())
	        {
	        driver.findElement(By.id("com.application.zomato:id/search_city")).sendKeys("san jose");
	        driver.findElement(By.id("com.application.zomato:id/city_name")).click();
	        }
	        if(driver.findElement(By.id("com.application.zomato:id/layout_login")).isDisplayed())
	        {
	        driver.findElement(By.id("com.application.zomato:id/layout_login_text")).click();
	        driver.findElement(By.id("com.application.zomato:id/login_email")).sendKeys(CONFIG.getProperty("Email"));
	           driver.findElement(By.id("com.application.zomato:id/login_password")).sendKeys(CONFIG.getProperty("Pwd"));

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/submit_button")));
	           driver.findElement(By.id("com.application.zomato:id/submit_button")).click();
	        }
	        //driver.findElement(By.id("com.application.zomato:id/skipLoginText")).click();
	       // driver.findElement(By.id("android:id/button2")).click();
	        
	    }


	/**
	 * Automation Test for Review Posting with less than 140 chars
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	@Test
	public void testZomatoReviewsNegative() throws MalformedURLException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//click on '+'
		driver.findElement(By.id("com.application.zomato:id/fab_background")).click();
		//click on 'write a review'
		driver.findElements(By.id("com.application.zomato:id/customlabel_cell_horizontal_house")).get(1).click();
		//search for a restaurant
		driver.findElement(By.id("com.application.zomato:id/restaurant_selection_edit_text")).sendKeys(CONFIG.getProperty("Restaurant"));
		//click on 'Write a review'
		/*driver.findElement(By.id("com.application.zomato:id/choose_restaurant_list_parent")).click();
		driver.findElement(By.id("com.application.zomato:id/reviewRatingbar")).click();
		driver.findElement(By.id("com.application.zomato:id/ReviewText")).sendKeys(CONFIG.getProperty("ReviewTextLess"));
		driver.findElement(By.id("com.application.zomato:id/header_button_right_icon")).click();
		takeScreenShot();*/
	        /*if(driver.findElement(By.id("com.application.zomato:id/who_were_you_with_editText")).isDisplayed())
	        {
	        		driver.findElement(By.id("com.application.zomato:id/who_were_you_with_editText")).sendKeys(CONFIG.getProperty("FriendName"));
	        		driver.findElement(By.id("com.application.zomato:id/header_button_right_icon")).click();
	        		System.out.println("Posted review successfully");
	        		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	        		takeScreenShot();
	        }
	        else
	        {
	        	System.out.println("Review Posting with few characters Failed");
	        }*/
		//driver.close();
	}

	    /**
	     * Positive Automation Test Case for Review Functionality
	     * @throws MalformedURLException
	     * @throws InterruptedException
	     */
	   @Test
	    public void testZomatoReviews() throws MalformedURLException, InterruptedException {
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        //click on '+'	     
	        driver.findElement(By.id("com.application.zomato:id/fab_background")).click();
	        //click on 'write a review'
	        		driver.findElements(By.id("com.application.zomato:id/customlabel_cell_horizontal_house")).get(1).click();
	        //search for a restaurant
	        driver.findElement(By.id("com.application.zomato:id/restaurant_selection_edit_text")).sendKeys(CONFIG.getProperty("Restaurant"));
	        //click on 'Write a review'
	        driver.findElement(By.id("com.application.zomato:id/choose_restaurant_list_parent")).click();
	        driver.findElement(By.id("com.application.zomato:id/reviewRatingbar")).click();
	        driver.findElement(By.id("com.application.zomato:id/ReviewText")).sendKeys(CONFIG.getProperty("ReviewText"));
	        driver.findElement(By.id("com.application.zomato:id/header_button_right_icon")).click();	        
	        if(driver.findElement(By.id("com.application.zomato:id/who_were_you_with_editText")).isDisplayed())
	        {
	        		driver.findElement(By.id("com.application.zomato:id/who_were_you_with_editText")).sendKeys(CONFIG.getProperty("FriendName"));
	        		driver.findElement(By.id("com.application.zomato:id/header_button_right_icon")).click();
	        		System.out.println("Posted review successfully");
	        		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	        		takeScreenShot();
	        }
	        
	        //driver.close();

	    }

	    public void takeScreenShot() {
	    	// Set folder name to store screenshots. 
	    	destDir = "screenshots";
	    	// Capture screenshot. 
	    	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    	// Set date format to set It as screenshot file name. 
	    	dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa"); 
	    	// Create folder under project with name "screenshots" provided to destDir. 
	    	new File(destDir).mkdirs(); // Set file name using current date time. 
	    	String destFile = dateFormat.format(new Date()) + ".png"; 
	    	try { 
	    		// Copy paste file at destination folder location 
	    		FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile)); 
	    		} catch (IOException e)
	    	{ e.printStackTrace();
	    	} 
	    	} 
	    	
	    

	    @AfterClass
	    public void teardown() {
	        //close the app
	        driver.quit();
	    }

	}

