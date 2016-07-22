import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by rbathej on 4/22/16.
 * Mobile Automation test cases - Zomato App - Search functionality
 */
public class ZomatoSearch {

    WebDriver driver;
    public static Long sleepBetweenOperations = 4000L;
    Properties prop = new Properties();
    InputStream input = null;

    @BeforeClass
    public void setUp() throws MalformedURLException, FileNotFoundException, IOException {
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        System.out.println("Starting the Zomato App");
        File app = new File("/Users/rbathej/SJSU/287/GroupProject/Zomato.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, " ");
        cap.setCapability("deviceName", "XT1064");
        cap.setCapability("platformVersion", "5.0.1");
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", app.getAbsolutePath());
        cap.setCapability("appPackage", "com.application.zomato");
        //cap.setCapability("appActivity", "com.application.zomato.app.ZomatoSearch");

        input = getClass().getClassLoader().getResourceAsStream("renu.properties");

        // load a properties file
        prop.load(input);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * Positive Automation Test Case for Search Functionality
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    @Test
    public void testZomatoSearch() throws MalformedURLException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Allow")));

        // click allow
        driver.findElement(By.name("Allow")).click();

        // Click 'Skip Login'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/skipLoginText")));
        driver.findElement(By.id("com.application.zomato:id/skipLoginText")).click();

        // Click search
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/icon_search")));
        driver.findElement(By.id("com.application.zomato:id/icon_search")).click();

        // Click drop down
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/down_arrow_search_suggestion")));
        driver.findElement(By.id("com.application.zomato:id/down_arrow_search_suggestion")).click();

        // Type 'San Jose'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/search_city")));
        driver.findElement(By.id("com.application.zomato:id/search_city")).sendKeys(prop.getProperty("user.place"));

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.ExpandableListView[1]/android.widget.LinearLayout[2]")));
        Thread.sleep(sleepBetweenOperations);
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.ExpandableListView[1]/android.widget.LinearLayout[2]")).click();

        // assert 'Your Location'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/all_places_textview")));
        assert driver.findElement(By.id("com.application.zomato:id/all_places_textview")).getText().equals(prop.getProperty("user.all.places"));

        // Type 'Punjab Cafe'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/search_edit_text")));
        driver.findElement(By.id("com.application.zomato:id/search_edit_text")).sendKeys(prop.getProperty("restaurant.name"));

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/category_name")));
        Thread.sleep(sleepBetweenOperations);
        driver.findElement(By.id("com.application.zomato:id/category_name")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/rating_and_reviews_container")));
        driver.findElement(By.id("com.application.zomato:id/rating_and_reviews_container")).click();

        // Assert the first result is 'Punjab Cafe'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/rest_name")));
        assert driver.findElement(By.id("com.application.zomato:id/rest_name")).getText().equals(prop.getProperty("restaurant.name"));

        //driver.close();

    }


    /**
     * Automation Test Case for Search Functionality - With Filters
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    @Test
    public void testZomatoSearchWithFilters() throws MalformedURLException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 200);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/back_icon")));

        // click 'back icon'
        driver.findElement(By.id("com.application.zomato:id/back_icon")).click();

        // Click nearby
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]")));
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]")).click();

        // Click 'filter icon'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/filter_icon")));
        driver.findElement(By.id("com.application.zomato:id/filter_icon")).click();

        // Click 'sort rating' icon
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/sort_rating_icon")));
        driver.findElement(By.id("com.application.zomato:id/sort_rating_icon")).click();

        // Click 'Open Now' checkbox
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/quickies_open_now_icon")));
        driver.findElement(By.id("com.application.zomato:id/quickies_open_now_icon")).click();

        // Select Cuisine - Afghani
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/cuisine_proceed")));
        driver.findElement(By.id("com.application.zomato:id/cuisine_proceed")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/list_name")));
        driver.findElement(By.id("com.application.zomato:id/list_name")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/header_button_right_icon")));
        driver.findElement(By.id("com.application.zomato:id/header_button_right_icon")).click();

        // assert selected cuisine as Afghani
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/selected_cuisine_string")));
        assert driver.findElement(By.id("com.application.zomato:id/selected_cuisine_string")).getText().equals(prop.getProperty("cuisine.name"));


        // Choose establishment type
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/estab_proceed")));
        driver.findElement(By.id("com.application.zomato:id/estab_proceed")).click();
        // Choose Lounge
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.RelativeLayout[1]")));
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.RelativeLayout[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/header_button_right_icon")));
        driver.findElement(By.id("com.application.zomato:id/header_button_right_icon")).click();

        // assert selected establishment as Lounge
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/selected_estab_string")));
        assert driver.findElement(By.id("com.application.zomato:id/selected_estab_string")).getText().equals(prop.getProperty("establishment.type"));

        // Click 'Apply'
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("apply_caps")));
        driver.findElement(By.id("apply_caps")).click();

        // With this combination, nothing is found. So assert no results found.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.application.zomato:id/no_results_found")));
        assert driver.findElement(By.id("com.application.zomato:id/no_results_found")).getText().equals(prop.getProperty("no.results.found"));

        //driver.close();

    }

    @AfterClass
    public void teardown() {
        //close the app
        driver.quit();
    }

}