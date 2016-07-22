

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.net.URL;
import java.util.concurrent.TimeUnit;

 import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

        import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


/**
 * Created by Sejal Gupta on 4/27/16.
 */


public class EditProfileTest {

    // public static Long sleepBetweenOperations = 4000L;
    AppiumDriver driver;
    
    @BeforeMethod
    public void setup() throws Exception {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName","007aad1e59d20b87");
        capabilities.setCapability("app", "/Users/rbathej/SJSU/287/GroupProject/Zomato.apk");
        capabilities.setCapability("appPackage", "com.application.zomato");
        capabilities.setCapability("platformVersion", "5.1.1");

        driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
// click allow

        driver.findElement(By.name("Allow")).click();
    }

    @Test
    //Edit Profile functionality check
    public void editProfile(){

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("sejal.properties");

            // load a properties file
            prop.load(input);

            driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

            //Login
            driver.findElement(By.id("com.application.zomato:id/layout_login")).click();
            driver.findElement(By.id("com.application.zomato:id/login_email")).sendKeys("team287SJSU@gmail.com");
            driver.findElement(By.id("com.application.zomato:id/login_password")).sendKeys("test123");
            driver.hideKeyboard();
            driver.findElement(By.id("com.application.zomato:id/submit_button")).click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

            //Get data size
            int dataSize = Integer.parseInt(prop.getProperty("size"));

            //Loop to test different test data
            for(int i=1; i<=dataSize; i++) {
                driver.findElement(By.id("com.application.zomato:id/account_tab_button")).click();
                driver.findElement(By.xpath("//android.widget.RelativeLayout[@index='2']")).click();
                driver.findElement(By.id("com.application.zomato:id/edit_username")).sendKeys(prop.getProperty("user." + i + ".name"));
                driver.findElement(By.id("com.application.zomato:id/edit_city")).click();
                driver.findElement(By.id("com.application.zomato:id/search_city")).sendKeys(prop.getProperty("user." + i + ".place"));
                driver.findElement(By.xpath("//android.widget.RelativeLayout[@index='4']/android.widget.ExpandableListView[@index='0']/android.widget.LinearLayout[@index='1']")).click();
                driver.findElement(By.id("com.application.zomato:id/edit_bio")).sendKeys(prop.getProperty("user." + i + ".aboutMe"));
                driver.findElement(By.id("com.application.zomato:id/edit_phonenumber")).sendKeys(prop.getProperty("user."+i+".phone"));
                driver.findElement(By.id("com.application.zomato:id/tick_proceed_icon")).click();
                driver.findElement(By.id("com.application.zomato:id/dineline_tab_button")).click();
                String userName = driver.findElement(By.id("com.application.zomato:id/infoview_user_name")).getText();
                AssertJUnit.assertTrue(userName.equals(prop.getProperty("user."+i+".name")));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* disable the driver after test has been executed */
    @AfterMethod public void teardown() throws Exception{
        driver.quit();
    }

}
