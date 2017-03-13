/**
 * Created by Nam on 3/5/2017.
 */

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import static  com.thoughtworks.selenium.SeleneseTestCase.assertNotEquals;
import static junit.framework.Assert.assertEquals;

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

    @Test (priority = 1)
    public void LoginFailed() throws MalformedURLException
    {
        //Run Browser and go to URL
        driver.navigate().to("https://www.skype.com");
        btnSignIn = driver.findElementByClassName("title");
        btnSignIn.click();
        iconSkype = driver.findElementByLinkText("Use Skype online");
        iconSkype.click();
        WebDriverWait wait_01 = new WebDriverWait(driver,10);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userName = driver.findElementById("username");
        userName.sendKeys("nam.nguyen2003");
        driver.findElementById("signIn").click();
        WebDriverWait wait_02 = new WebDriverWait(driver,5);

        password = driver.findElementById("i0118");
        password.sendKeys("123qwe789");
        driver.findElementById("idSIButton9").click();

    }

//    @Test (priority = 2)
//    public  void LoginSuccess()
//    {
//        WebDriverWait wait_03 = new WebDriverWait(driver,5);
//        wait_03.until(ExpectedConditions.elementToBeClickable(By.id("idBtn_Back")));
//        driver.findElementById("idBtn_Back").click();
//        driver.findElementById("i0116").clear();
//        driver.findElementById("i0116").sendKeys("namnguyen2003");
//        driver.findElementById("idSIButton9").click();
//        driver.findElementById("i0118").sendKeys("123qwe789!");
//        driver.findElementById("idSIButton9").click();
//        WebDriverWait wait_04 = new WebDriverWait(driver,30);
//    }

    @AfterTest
    public void AfterTest()
    {
        driver.quit();
    }
}
