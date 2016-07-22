

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.testng.annotations.*;



/*
 *  Created by Pavan Chokshi on 4/26/16.
*/


public class InviteFriendsTest {

  // public static Long sleepBetweenOperations = 4000L;
   AppiumDriver driver;
   @BeforeMethod
   public void setup() throws Exception {
       DesiredCapabilities capabilities=new DesiredCapabilities();
       capabilities.setCapability("platformName", "Android");
       capabilities.setCapability("deviceName","emulator-5554");
       capabilities.setCapability("app", "/Users/rbathej/SJSU/287/GroupProject/Zomato.apk");
       capabilities.setCapability("appPackage", "com.application.zomato");


       driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
   }
   // disable the driver after test has been executed 
   @AfterClass
   public void teardown() throws Exception{
       driver.quit();
   }

   @Test
   //Check In functionality check
   public void inviteFriendsByMail(){

       //first time when you open the app,comes the location dialogue asking user to allow the location to turn on
       
       driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
       //driver.findElement(By.xpath("//android.widget.Button[@text='Select city']")).click();
       
       driver.findElement(By.id("com.application.zomato:id/layout_login")).click();
       
       
       driver.findElement(By.id("com.application.zomato:id/login_email")).sendKeys("team287SJSU@gmail.com");
       driver.findElement(By.id("com.application.zomato:id/login_password")).sendKeys("test123");
       driver.hideKeyboard();
       driver.findElement(By.id("com.application.zomato:id/submit_button")).click();
       driver.manage().timeouts().implicitlyWait(50000, TimeUnit.SECONDS);
       
       //Clicks on "Account button"
       driver.findElement(By.id("com.application.zomato:id/account_tab_button")).click();
       //Scroll to "Invite Friends to Zomato" link and clicks on it
       driver.scrollTo("Invite Friends to Zomato");
       driver.findElement(By.id("com.application.zomato:id/invite_contact")).click();
       //Selects the textView so that it becomes editable
       driver.findElement(By.xpath("//android.widget.LinearLayout[@index='1']")).click();
       //Enter the email id to send the invite
       driver.findElement(By.id("com.google.android.gms:id/search")).sendKeys("bhallakapil@gmail.com");
       //Press Enter to confirm the email id and hide keyboard
       ((AndroidDeviceActionShortcuts) driver).sendKeyEvent(AndroidKeyCode.ENTER);
       driver.hideKeyboard();
       //Clicks "Send" button to send the mail
       driver.findElement(By.id("com.google.android.gms:id/menu_send")).click();
       
      
   }

}
