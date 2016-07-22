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
 * Created by havisha on 4/25/16
 * Mobile Automation test cases - Zomato App - Filters Testing
 */
public class ZomatoFilter {

    WebDriver driver;
    public static Long sleepBetweenOperations = 4000L;
    Properties config_properties = new Properties();
    InputStream input = null;

    @BeforeClass
    public void setUp() throws MalformedURLException, FileNotFoundException, IOException {
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        File app = new File("/Users/rbathej/SJSU/287/GroupProject/Zomato.apk");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, " ");
        cap.setCapability("deviceName", "S4");
        cap.setCapability("platformVersion", "5.0.1");
        cap.setCapability("platformName", "Android");
        cap.setCapability("app", app.getAbsolutePath());
        cap.setCapability("appPackage", "com.application.zomato");

        input = getClass().getClassLoader().getResourceAsStream("havisha.properties");

        // load a config properties
        config_properties.load(input);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testZomatoFilters() throws MalformedURLException, InterruptedException {

        // click allow
        driver.findElement(By.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[2]")).click();

        // Click 'Skip Login'
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]")).click();

        // Click search
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.view.ViewGroup[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.TextView[3]")).click();

        // Click drop down
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.TextView[3]")).click();
        Thread.sleep(sleepBetweenOperations);

        // Type current location from config.properties
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]")).sendKeys(config_properties.getProperty("current.location"));
        Thread.sleep(sleepBetweenOperations);
        driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.ExpandableListView[1]/android.widget.LinearLayout[2]")).click();

        // assert all places of current location from config.properties
        assert driver.findElement(By.id("com.application.zomato:id/all_places_textview")).getText().equals(config_properties.getProperty("current.location.all.places"));

        // Type cusine type from config.properties
        driver.findElement(By.id("com.application.zomato:id/search_edit_text")).sendKeys(config_properties.getProperty("cusine.type"));

        Thread.sleep(sleepBetweenOperations);

        // Click 'filter icon'
        driver.findElement(By.id("com.application.zomato:id/filter_icon")).click();

        // Click 'Open Now' checkbox
        Thread.sleep(sleepBetweenOperations);
        driver.findElement(By.id("com.application.zomato:id/quickies_open_now_icon")).click();

        //click on 'rated 3.5+' check box
        Thread.sleep(sleepBetweenOperations);
        driver.findElement(By.id("com.application.zomato:id/quickies_rated_icon")).click();

        // Click 'Apply'
        Thread.sleep(sleepBetweenOperations);
        driver.findElement(By.id("apply_caps")).click();

        // assert if the seleted rating is > 3.5+
        Thread.sleep(sleepBetweenOperations);
        assert Integer.parseInt(driver.findElement(By.id("com.application.zomato:id/search_result_rating")).getText()) >= 3.5;

        // Click on first link
        Thread.sleep(sleepBetweenOperations);
        driver.findElement(By.id("com.application.zomato:id/rating_and_reviews_container")).click();

        //assert if HOURS == OPEN NOW
        Thread.sleep(sleepBetweenOperations);
        assert driver.findElement(By.name(config_properties.getProperty("filterText.opennow"))).getText().equals(config_properties.getProperty("filterText.opennow"));


    }
    

    @AfterClass
    public void teardown() {
        //close the app
        driver.quit();
    }

}