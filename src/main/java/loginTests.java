import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pageObjects.LoginPage;
import pageObjects.homePage;


public class loginTests {
	

	InputStream inputStream;
	private Properties prop = new Properties();
	String propFileName = "login.properties";

		// Read from properties file
		@BeforeMethod public void getPropValues() throws IOException{
		try {

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}


		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
	}
	
	
	

	  WebDriver driver;
	    public static Long sleepBetweenOperations = 4000L;

	    @BeforeMethod public void setUp() throws MalformedURLException {
	        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
	        File app = new File("/Users/rbathej/SJSU/287/GroupProject/Zomato.apk");
	        DesiredCapabilities cap = new DesiredCapabilities();
	        cap.setCapability(CapabilityType.BROWSER_NAME, " ");
	        cap.setCapability("deviceName", "HT4AYWM00997");
	        cap.setCapability("platformVersion", "5.0.2");
	        cap.setCapability("platformName", "Android");
	        cap.setCapability("app", app.getAbsolutePath());
	        cap.setCapability("appPackage", "com.application.zomato");
	        //cap.setCapability("appActivity", "com.application.zomato.app.ZomatoApp");

	        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// click allow

			driver.findElement(By.name("Allow")).click();
	    }
	    
	    
	    @Test
	    public void testInvalidLoginCredentials(){
	    	
	    	
	    		// Skip Login
		    	homePage hp = new homePage(driver);
		    	hp.clickLogin();
		    	
		    	// Login page functions
		    	LoginPage lp = new LoginPage(driver);
		    	
		    	String u = prop.getProperty("regNotInUseEmail");
		    	String p = prop.getProperty("regInvalidPwd");
		    	
		    	lp.login(u, p);
		    	
		    	lp.assertLoginPage();
	    	
	    }
	    
	    
	    @Test
	    public void validLogin(){
	    	// Skip Login
	    	homePage hp = new homePage(driver);
	    	hp.clickLogin();
	    	
	    	// Login page functions
	    	LoginPage lp = new LoginPage(driver);
	    	
	    	lp.login(prop.getProperty("regInUseEmail"), prop.getProperty("regValidPassword"));
	    	lp.assertSuccessfulLogin();
	    }
	    
	    
	    @AfterMethod 
	    public void teardown() {
	        //close the app
	        driver.quit();
	    }
	    

}
