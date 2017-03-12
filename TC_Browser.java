/**
 * Created by Nam on 3/5/2017.
 */

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import static  com.thoughtworks.selenium.SeleneseTestCase.assertNotEquals;
import java.net.URL;

public class TC_Browser {
    AndroidDriver driver;
    WebElement btnSignIn;
    WebElement iconSkype;
    WebElement userName;
    WebElement password;

    @BeforeTest
    public  void  BeforeTest() throws MalformedURLException
    {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("platformName","ANDROID");
        cap.setCapability("deviceName","emulator-5554");
        // Using Default Browser of device
        cap.setCapability(MobileCapabilityType.APP,"Browser");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
    }

    @Test
    public void LoginFailed() throws MalformedURLException
    {
        //String screenHome = driver.currentActivity();
        //Run Browser and go to URL
        driver.navigate().to("https://www.skype.com");
        btnSignIn = (WebElement) driver.findElementByClassName("title");
        btnSignIn.click();
        iconSkype = (WebElement) driver.findElementByLinkText("Use Skype online");
        iconSkype.click();
        WebDriverWait wait_01 = new WebDriverWait(driver,10);

        //String screenUsername;
        //screenUsername = driver.currentActivity();
        //assertNotEquals(screenHome,screenUsername);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userName = driver.findElementById("username");
        userName.sendKeys("nam.nguyen2003");
        driver.findElementById("signIn").click();
        WebDriverWait wait_02 = new WebDriverWait(driver,5);

        //String screenPassword;
        //screenPassword = driver.currentActivity();
        //assertNotEquals(screenUsername,screenPassword);
        password = (WebElement) driver.findElementByClassName("placeholder");
        password.sendKeys("123qwe789");
        driver.findElementById("idSIButton9").click();
        WebDriverWait wait_03 = new WebDriverWait(driver,5);
    }

    @AfterTest
    public void AfterTest()
    {
        driver.quit();
    }
}