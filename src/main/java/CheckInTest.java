import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.*;


/**
 * Created by shauryachawla on 4/22/16.
 */


public class CheckInTest {

    AppiumDriver driver;
    @BeforeMethod
    public void setup() throws Exception {
        File app = new File("/Users/rbathej/SJSU/287/GroupProject/Zomato.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, " ");
        cap.setCapability("deviceName", "XT1064");
        cap.setCapability("platformVersion", "5.0.1");
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", app.getAbsolutePath());
        cap.setCapability("appPackage", "com.application.zomato");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

    }

    /* disable the driver after test has been executed */
    @AfterClass
    public void teardown() throws Exception{
        driver.quit();
    }

    @Test
    //Check In functionality check
    public void CheckInFunctionality(){

        //first time when you open the app,comes the location dialogue asking user to allow the device's location
        driver.findElement(By.name("Allow")).click();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

        //checks LogIn functionality, say through email
        if(driver.findElement(By.id("com.application.zomato:id/layout_login")).isDisplayed()){

            driver.findElement(By.id("com.application.zomato:id/layout_login")).click();
            driver.findElement(By.id("com.application.zomato:id/login_email")).sendKeys("team287SJSU@gmail.com");
            driver.findElement(By.id("com.application.zomato:id/login_password")).sendKeys("test123");
            driver.hideKeyboard();
            driver.findElement(By.id("com.application.zomato:id/submit_button")).click();
        }

        //clicking fab action("+")button or fab button/floating action
        driver.findElement(By.id("com.application.zomato:id/fab_action_button")).click();
        //clicking Check In option
        driver.findElement(By.name("Check In")).click();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

        //search for restaurant that you want to check in
        driver.findElement(By.name("Search for a restaurant...")).sendKeys("Jack in the Box");
        //clicking the option that comes in search for a restaurant
        driver.findElement(By.id("com.application.zomato:id/restaurant_snippet_name")).click();
        //clicking the check in icon
        driver.findElement(By.id("com.application.zomato:id/header_button_right_icon")).click();
        //assert to ensure if the restaurant has been Check In
        assert driver.findElement(By.id("com.application.zomato:id/welcome_rest_name")).getText().equals("Jack in the Box");


    }

}


