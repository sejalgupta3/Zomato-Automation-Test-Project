
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL; 
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities; 
import org.openqa.selenium.remote.RemoteWebDriver; 
import org.testng.annotations.*;

import pageObjects.RegistrationPage;
import pageObjects.homePage;

public class Registration { 
WebDriver AndroidDriver;

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


 @BeforeMethod
public void setUp() throws Exception{ 
    
	File app = new File("/Users/rbathej/SJSU/287/GroupProject/Zomato.apk");
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability(CapabilityType.BROWSER_NAME, " ");
    cap.setCapability("deviceName", "HT4AYWM00997");
    cap.setCapability("platformVersion", "5.0.2");
    cap.setCapability("platformName", "Android");
    cap.setCapability("app", app.getAbsolutePath());
    cap.setCapability("appPackage", "com.application.zomato");
    //cap.setCapability("appActivity", "com.application.zomato.app.ZomatoApp");
    AndroidDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
    AndroidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	 // click allow

	 AndroidDriver.findElement(By.name("Allow")).click();

}


@Test
public void signUpDisable(){
	// CLick Signup
	homePage hp = new homePage(AndroidDriver);
	hp.clickSignUp();
	
	// Click Signup
	RegistrationPage rp = new RegistrationPage(AndroidDriver);
	rp.clickSubmit();
	
	// Assert Still on signup page
	rp.assertSignUpPage();
	
}

@Test 
public void validRegistration() { 
  
  // CLick Signup
	homePage hp = new homePage(AndroidDriver);
	hp.clickSignUp();
	
   AndroidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   
   RegistrationPage rp = new RegistrationPage(AndroidDriver);
   //name
   rp.setName("Dishant");
   
   
   //Email
   rp.setEmail("dishant.kalyani@sjsu.edu");
      
   //Password
   rp.setPassword("Dishant");
   
   AndroidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

   
   rp.clickSubmit();
    
   //rp.assertSignUp();
   
   
}

@AfterMethod
public void teardown(){ 
    
	AndroidDriver.quit(); 
} 



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
