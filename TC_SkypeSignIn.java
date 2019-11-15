/**
 * Created by Nam on 2/21/2017.
 */

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class TC_SkypeSignIn {
    AndroidDriver _driver;
    String fileName = "Skype.apk";
    File filePath = new File("src/main/resources/" + fileName);

    @BeforeTest
    public void BeforeTest() throws MalformedURLException {
        DesiredCapabilities _cap = new DesiredCapabilities();

        _cap.setCapability("App", filePath);
        // Platform
        _cap.setCapability("platformName", "ANDROID");
        // Device name
        _cap.setCapability("deviceName", "emulator-5554");

        _driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), _cap);
    }

    @Test(priority = 1)
    /* Testcase flow:
    1. Input username
    2. Press Continue
    3. Input Password
    4. Press Continue
    Wait 5s
    */

    public void SignInFailed() {
        WebElement username;
        WebElement btnContinue;
        WebElement password;
        WebElement btnContinue_AfterPassword;

        String Screen1 = _driver.currentActivity();
        System.out.print(Screen1);
        // 1. Input Username
        _driver.findElementById("com.skype.raider:id/sign_in_userid").sendKeys("nam2003");
        
        //username = (WebElement) _driver.findElementsByClassName("android.widget.EditText").get(0);
        //username.sendKeys("nam.nguyen2003");
        // 2. Press Continue
        _driver.findElementById("com.skype.raider:id/sign_in_next_btn").click();
        
        //btnContinue = (WebElement) _driver.findElementsByClassName("android.widget.TextView").get(2);
        //btnContinue.click();
        
        // Take element from current page
        String Screen2 = _driver.currentActivity();
        
        // 3. Input Password
        _driver.findElementById("com.skype.raider:id/signin_password").sendKeys("*****");
        
        //password = (WebElement)_driver.findElementsByClassName("android.widget.EditText").get(1);
        //password.sendKeys("*****");
        // 4. Press Continue
        _driver.findElementById("com.skype.raider:id/sign_in_btn").click();
        
        //btnContinue_AfterPassword = (WebElement)_driver.findElementsByClassName("android.widget.TextView").get(2);
        //btnContinue_AfterPassword.click();
        // 5. Wait 5s
        WebDriverWait wait5s = new WebDriverWait(_driver,5);
    }

    @Test(priority = 2)
    public void SignInSuccess() {
        WebElement username_secondTime;
        WebElement password;
        WebElement btnContinue2nd;

        // Find and Clear Username Field
        username_secondTime = (WebElement) _driver.findElementsByClassName("android.widget.EditText").get(1);
        username_secondTime.clear();
        username_secondTime.sendKeys("namnguyen2003");

        // Fine ARROW to Continue
        btnContinue2nd = (WebElement) _driver.findElementsByClassName("android.widget.TextView").get(2);
        btnContinue2nd.click();
        // Wait until Password filed appears
        WebDriverWait watingTime = new WebDriverWait(_driver, 5);
        // Input Password
        password = (WebElement)_driver.findElementsByClassName("android.widget.EditText").get(3);
        password.sendKeys("*****");
        // Find ARROW to Continue
        btnContinue2nd.click();
    }
}
