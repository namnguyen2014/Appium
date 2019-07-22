package WEB;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

public class Excution {
    public static String chromeDriverPath = "/home/namnth/Downloads/Nam_Folder/WebDrivers/chromedriver";
    public static String firefoxDriverPath = "/home/namnth/Downloads/Nam_Folder/WebDrivers/geckodriver";
    public static ChromeOptions chromeOpts = new ChromeOptions();
    public static FirefoxOptions firefoxOpts;
    public static FirefoxProfile firefoxProfile;
    public WebDriver driver;
    public static GLOBAL global;
    public static String excelPath = "/home/namnth/IdeaProjects/SELENIUM/src/main/resources/DATA.xlsx";
    public static ExtentTest exTest;
    public static ExtentReports exReport;

    @BeforeTest
    @Parameters("browser")
    // Detect browser
    public void Init_Browser(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            // Run firefox in Private mode.
            firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
            driver = new FirefoxDriver(firefoxProfile);
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            // Enable Chrome runs with automation mode
            //chromeOpts.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            // Disable Infor bar
            chromeOpts.addArguments("disable-infobars");
            // Disable extensions
            chromeOpts.addArguments("--disable-extensions");
            // Run chrome in Incognito mode.
            chromeOpts.addArguments("--incognito");
            // Apply to driver
            driver = new ChromeDriver(chromeOpts);
        }else{
            throw new Exception("Browser is not correct");
        }
    }
    // Using Extent Report
    @BeforeClass
    public void Start_Test_Using_Extent_Report() {
        exReport = new ExtentReports
                (System.getProperty("/home/namnth/IdeaProjects/SELENIUM/src/main/resources/extentreports-java-2.41.2")
                        + "ExtentReportResults.html", true);
        exTest = exReport.startTest("Excution");
    }
    // Verify NEWS page
    @Test(priority = 1)
    @Parameters("browser")
    public void TC01_VERIFY_ICON_GOOGLE(String browser) throws IOException {
        // Access to URL
        global.Access_URL(driver, "http://gmail.com");
        exTest.log(LogStatus.INFO, "INFO - Access to GMAIL Home page.");
        // Maximize window
        global.Maximize_Window(driver);
        exTest.log(LogStatus.INFO, "INFO - Maximized window.");
        // Wait 3s after maximized windows.
        global.Wait_For_Page_Loading(3);

        String excel_IconGoole = null;
        // Read data from Excel - to get element string
        excel_IconGoole = global.Read_Data_From_Excel(excelPath, "ELEMENTS", 0, 2);
        if(excel_IconGoole == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate element by xPath
        WebElement ele_IconGoogle = null;
        ele_IconGoogle = global.Find_Element_By_XPath(driver, excel_IconGoole);
        if(ele_IconGoogle == null){
            exTest.log(LogStatus.ERROR, "ERROR - Cannot find Icon Google");
        }
        Boolean isExist_IconGoogle = true;
        if(isExist_IconGoogle == global.Element_Is_Existed(ele_IconGoogle)){
            if(isExist_IconGoogle){
                exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC01 - Icon Google is displayed.");
            } else {
                exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC01 - Icon Google is not displayed.");
            }
        }
        global.Wait_For_Page_Loading(3);
    }
    @Test(priority = 2)
    @Parameters("browser")
    public void TC02_VERIFY_TEXT_DANGNHAP(String browser)throws IOException {
        String excel_TxtDangNhap = null;
        // Read data from Excel - to get element string
        excel_TxtDangNhap = global.Read_Data_From_Excel(excelPath,"ELEMENTS",1,2);
        if(excel_TxtDangNhap == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate Element by Xpath
        WebElement ele_TxtDangNhap = null;
        ele_TxtDangNhap = global.Find_Element_By_XPath(driver,excel_TxtDangNhap);
        if(ele_TxtDangNhap == null){
            exTest.log(LogStatus.ERROR, "ERROR - Cannot find text Dang nhap");
        }
        Boolean isExist_TextDangNhap = global.Element_Is_Existed(ele_TxtDangNhap);
        if(isExist_TextDangNhap == true){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC02 - Text Dang nhap is displayed.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC02 - Text Dang nhap is not displayed.");
        }
        String text_Ele_TextDangNhap = "Đăng nhập";
        // Get text from ele_TxtDangNhap
        String textGetFromEle_DangNhap = global.Get_Text(ele_TxtDangNhap);
        if(textGetFromEle_DangNhap.equals(text_Ele_TextDangNhap)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC02.1 - Text Dang nhap is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC02.1 - Text Dang nhap is not correct.");
        }
        global.Wait_For_Page_Loading(3);
    }
    @Test(priority = 3)
    @Parameters("browser")
    public void TC03_VERIFY_TEXT_TIEPTUC(String browser)throws IOException {
        String excel_TxtTieptuc = null;
        // Read data from Excel - to get element string
        excel_TxtTieptuc = global.Read_Data_From_Excel(excelPath,"ELEMENTS",2,2);
        if(excel_TxtTieptuc == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate Element by Xpath
        WebElement ele_TxtTieptuc = null;
        ele_TxtTieptuc = global.Find_Element_By_XPath(driver,excel_TxtTieptuc);
        if(ele_TxtTieptuc == null){
            exTest.log(LogStatus.ERROR,"ERROR - Cannot find text Tiep tuc toi Gmail");
        }
        Boolean isExist_TxtTieptuc = global.Element_Is_Existed(ele_TxtTieptuc);
        if(isExist_TxtTieptuc == true){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC03 - Text Tiep tuc toi Gmail is displayed.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC03 - Text Tiep tuc toi Gmail is not displayed.");
        }
        String text_Ele_TextTieptuc = "Tiếp tục tới Gmail";
        // Get text from ele_TxtTieptuc
        String textGetFromEle_TiepTuc = global.Get_Text(ele_TxtTieptuc);
        if(textGetFromEle_TiepTuc.equals(text_Ele_TextTieptuc)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC03.1 - Text Tiep tuc toi Gmail is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC03.1 - Text Tiep tuc toi Gmail is not correct.");
        }
        global.Wait_For_Page_Loading(3);
    }
    @Test(priority = 4)
    @Parameters("browser")
    public void TC04_VERIFY_TEXTBOX_EMAIL(String browser)throws IOException {
        String excel_TxtboxEmail = null;
        // Read data from Excel - to get element string
        excel_TxtboxEmail = global.Read_Data_From_Excel(excelPath, "ELEMENTS",3,2);
        if(excel_TxtboxEmail == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate Element by Xpath
        WebElement ele_TxtboxEmail = null;
        ele_TxtboxEmail = global.Find_Element_By_XPath(driver,excel_TxtboxEmail);
        if(ele_TxtboxEmail == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC04 - Textbox Email is not displayed.");

        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC04 - Textbox Email is displayed.");
        }
        // Get placeholder text
        String excel_CSS_Txtbox = null;
        excel_CSS_Txtbox = global.Read_Data_From_Excel(excelPath, "ELEMENTS",3,3);
        if(excel_CSS_Txtbox == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate Element by CSS Selector
        WebElement ele_CSS_TxtboxEmail = null;
        ele_CSS_TxtboxEmail = global.Find_Element_By_Css_Selector(driver,excel_CSS_Txtbox);
        if(ele_CSS_TxtboxEmail == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC04.1 - Textbox Email - CSS is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC04.1 - Textbox Email - CSS is displayed.");
        }
        String placeHolderText_Txtbox_Email = "Email hoặc số điện thoại";
        String textGetFromEle_TxtBoxEmail = ele_CSS_TxtboxEmail.getAttribute("aria-label");
        if(textGetFromEle_TxtBoxEmail != null && textGetFromEle_TxtBoxEmail.equals(placeHolderText_Txtbox_Email)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC04.2 - Placeholder Textbox Email is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC04.2 - Placeholder Textbox Email is not correct.");
        }
        global.Wait_For_Page_Loading(3);
    }
    @Test(priority = 5)
    @Parameters("browser")
    public void TC05_VERIFY_FORGOT_LINK(String browser)throws IOException {
        String excel_LinkForgot = null;
        // Read data from Excel - to get element string
        excel_LinkForgot = global.Read_Data_From_Excel(excelPath,"ELEMENTS",4,2);
        if(excel_LinkForgot == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate Element by Xpath
        WebElement ele_LinkForgot = null;
        ele_LinkForgot = global.Find_Element_By_XPath(driver,excel_LinkForgot);
        if(ele_LinkForgot == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC05 - Link Forgot Password is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC05 - Link Forgot Password is displayed.");
        }
        String text_ForgotLink = "Bạn quên địa chỉ email?";
        String textGetFromEle_ForgotLink = ele_LinkForgot.getText();
        if(textGetFromEle_ForgotLink != null && textGetFromEle_ForgotLink.equals(text_ForgotLink)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC05.1 - Text Forgot is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC05.1 - Text Forgot is not correct.");
        }
        global.Wait_For_Page_Loading(3);
    }
    @Test(priority = 6)
    @Parameters("browser")
    public void TC06_VERIFY_TEXT_LEARNMORE(String browser)throws IOException{
        // Read data from Excel - to get element string
        String excel_TxtLearnmore = null;
        excel_TxtLearnmore = global.Read_Data_From_Excel(excelPath,"ELEMENTS", 5,2);
        if(excel_TxtLearnmore == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "::[" + browser + "]:: ERROR - Cell or Row No. is wrong." );
        }
        // Locate Element by Xpath
        WebElement ele_TxtLearnmore = null;
        ele_TxtLearnmore = global.Find_Element_By_XPath(driver,excel_TxtLearnmore);
        if(ele_TxtLearnmore == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC06 - Text Learnmore is displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC06 - Text Learnmore  is not displayed.");
        }
        // Get text from Element.
        String textLearnmoreFF = "Không phải máy tính của bạn? Hãy sử dụng Cửa sổ riêng tư để đăng nhập. Tìm hiểu thêm";
        String textLearnmoreGC = "Không phải máy tính của bạn? Hãy sử dụng chế độ Khách để đăng nhập một cách riêng tư. Tìm hiểu thêm";

        String textGetFromEle_TxtLearnMore = ele_TxtLearnmore.getText();
        System.out.println("DEBUG --- " + textGetFromEle_TxtLearnMore);
        if(browser.equalsIgnoreCase("firefox") && textGetFromEle_TxtLearnMore != null && textGetFromEle_TxtLearnMore.equals(textLearnmoreFF)){
            exTest.log(LogStatus.PASS,":: [" + browser + "]:: TC06.1 - Text Learmore is correct.");
        } else if(browser.equalsIgnoreCase("chrome") && textGetFromEle_TxtLearnMore != null && textGetFromEle_TxtLearnMore.equals(textLearnmoreGC)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC06.1 - Text Learmore is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC06.1 - Text Learnmore is not correct.");
        }
        global.Wait_For_Page_Loading(3);
    }
    @Test(priority = 7)
    @Parameters("browser")
    public void TC07_VERIFY_LINK_TAOTK(String browser) throws IOException {
        // Read data from Excel
        String excel_Link_TaoTK = null;
        excel_Link_TaoTK = global.Read_Data_From_Excel(excelPath,"ELEMENTS",6,2);
        if(excel_Link_TaoTK == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate element by Xpath
        WebElement ele_Link_TaoTK = null;
        ele_Link_TaoTK = global.Find_Element_By_XPath(driver,excel_Link_TaoTK);
        if(ele_Link_TaoTK == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC07 - Link Tao Tai Khoan is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC07 - Link Tao Tai Khoan is displayed.");
        }
        // Get text from Element.
        String textTaoTK = "Tạo tài khoản";
        String textGetFromEle_LinkTaoTK = ele_Link_TaoTK.getText();
        System.out.println("DEBUG --- " + textGetFromEle_LinkTaoTK);
        if(textGetFromEle_LinkTaoTK != null && textGetFromEle_LinkTaoTK.equalsIgnoreCase(textTaoTK)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC07.1 - Link Tao Tai Khoan is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC07.1 - Link Tao Tai Khoan is not correct.");
        }
        global.Wait_For_Page_Loading(3);
    }
    @Test(priority = 8)
    @Parameters("browser")
    public void TC08_VERIFY_BUTTON_TIEPTHEO(String browser) throws IOException {
        // Read data from Excel
        String excel_BtnTiepTheo = null;
        excel_BtnTiepTheo = global.Read_Data_From_Excel(excelPath,"ELEMENTS",7,2);
        if(excel_BtnTiepTheo == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate element by Xpath
        WebElement ele_BtnTiepTheo = null;
        ele_BtnTiepTheo = global.Find_Element_By_XPath(driver, excel_BtnTiepTheo);
        if(ele_BtnTiepTheo == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC08 - Button Tiep theo is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC08 - Button Tiep theo is displayed.");
        }
        // Get text from Element.
        String textBtnTieptheo = "Tiếp theo";
        String textGetFromEle_BtnTieptheo = ele_BtnTiepTheo.getText();
        System.out.println("DEBUG --- " + textGetFromEle_BtnTieptheo);
        if(textGetFromEle_BtnTieptheo != null && textGetFromEle_BtnTieptheo.equals(textBtnTieptheo)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC08.1 - Button Tiep theo is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC08.1 - Link Tao Tai Khoan is not correct.");
        }
    }
    @Test(priority = 9)
    @Parameters("browser")
    public void TC09_VERIFY_NGONNGU(String browser) throws IOException {
        // Read data from Excel.
        String excel_Ngonngu = null;
        excel_Ngonngu = global.Read_Data_From_Excel(excelPath, "ELEMENTS",8,2);
        if(excel_Ngonngu == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate element by Xpath
        WebElement ele_Ngonngu = null;
        ele_Ngonngu = global.Find_Element_By_XPath(driver,excel_Ngonngu);
        if(ele_Ngonngu == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC09 - Ngon ngu is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC09 - Ngon ngu is displayed.");
        }

        Select selectNgonNgu = new Select(ele_Ngonngu);
        selectNgonNgu.selectByIndex(0);

        // Get text from Element.
        String textNgonngu = "Tiếng Việt";
        String textGetFromEle_Ngonngu = selectNgonNgu.getFirstSelectedOption().getText();
        System.out.println("DEBUG --- " + textGetFromEle_Ngonngu);
        if(textGetFromEle_Ngonngu != null && textGetFromEle_Ngonngu.equals(textNgonngu)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC09.1 - Ngon ngu is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC09.1 - Ngon ngu is not correct.");
        }
    }
    @Test(priority = 10)
    @Parameters("browser")
    public void TC10_VERIFY_TROGIUP(String browser) throws IOException {
        // Read data from Excel
        String excel_Trogiup = null;
        excel_Trogiup = global.Read_Data_From_Excel(excelPath, "ELEMENTS",9,2);
        if(excel_Trogiup == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate element by Xpath
        WebElement ele_Trogiup = null;
        ele_Trogiup = global.Find_Element_By_XPath(driver,excel_Trogiup);
        if(ele_Trogiup == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC10 - Link Tro giup is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC10 - Link Tro giup is displayed.");
        }
        // Get text from Element.
        String textTrogiup = "Trợ giúp";
        String textGetFromEle_Trogiup = ele_Trogiup.getText();
        if(textGetFromEle_Trogiup != null && textGetFromEle_Trogiup.equals(textTrogiup)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC10.1 - Link Tro giup is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC10.1 - Link Tro giup is not correct.");
        }
    }
    @Test(priority = 11)
    @Parameters("browser")
    public void TC11_VERIFY_BAOMAT (String browser) throws IOException {
        // Read data from Excel
        String excel_Baomat = null;
        excel_Baomat = global.Read_Data_From_Excel(excelPath, "ELEMENTS",10,2);
        if(excel_Baomat == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate element by Xpath
        WebElement ele_Baomat = null;
        ele_Baomat = global.Find_Element_By_XPath(driver,excel_Baomat);
        if(ele_Baomat == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC11 - Link Bao mat is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC11 - Link Bao mat is displayed.");
        }
        // Get text from Element.
        String textBaomat = "Bảo mật";
        String textGetFromEle_Baomat = ele_Baomat.getText();
        if(textGetFromEle_Baomat != null && textGetFromEle_Baomat.equals(textBaomat)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC11.1 - Link Bao mat is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC11.1 - Link Bao mat is not correct.");
        }
    }
    @Test(priority = 12)
    @Parameters("browser")
    public void TC12_VERIFY_DIEUKHOAN (String browser) throws IOException {
        // Read data from Excel
        String excel_Dieukhoan = null;
        excel_Dieukhoan = global.Read_Data_From_Excel(excelPath, "ELEMENTS",11,2);
        if(excel_Dieukhoan == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong." );
        }
        // Locate element by Xpath
        WebElement ele_Dieukhoan = null;
        ele_Dieukhoan = global.Find_Element_By_XPath(driver,excel_Dieukhoan);
        if(ele_Dieukhoan == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC12 - Link Dieu khoan is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC12 - Link Dieu khoan is displayed.");
        }
        // Get text from Element.
        String textDieukhoan = "Điều khoản";
        String textGetFromEle_Dieukhoan = ele_Dieukhoan.getText();
        if(textGetFromEle_Dieukhoan != null && textGetFromEle_Dieukhoan.equals(textDieukhoan)){
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC12.1 - Link Dieu khoan is correct.");
        } else {
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC12.1 - Link Dieu khoan is not correct.");
        }
    }
    @Test(priority = 13)
    @Parameters("browser")
    public void TC13_VERIFY_INPUT_EMAIL (String browser) throws IOException {
        // Read data from Excel
        String excel_txtboxEmail = null;
        String excel_btnTieptheo = null;
        excel_txtboxEmail = global.Read_Data_From_Excel(excelPath,"ELEMENTS",3,3);
        excel_btnTieptheo = global.Read_Data_From_Excel(excelPath, "ELEMENTS", 7,2);
        if(excel_txtboxEmail == null || excel_btnTieptheo == null){
            System.out.println("ERROR - Cell or Row No. is wrong.");
            exTest.log(LogStatus.ERROR, "ERROR - Cell or Row No. is wrong.");
        }
        // Locate element by Xpath
        WebElement ele_txtboxEmail = null;
        WebElement ele_btnTieptheo = null;
        ele_txtboxEmail = global.Find_Element_By_Css_Selector(driver,excel_txtboxEmail);
        ele_btnTieptheo = global.Find_Element_By_XPath(driver, excel_btnTieptheo);
        if(ele_txtboxEmail == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC13 - Textbox EMAIL is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC13 - Textbox EMAIL is displayed.");
        }
        if (ele_btnTieptheo == null){
            exTest.log(LogStatus.FAIL,"::[" + browser + "]:: TC13 - Button Tiep theo is not displayed.");
        } else {
            exTest.log(LogStatus.PASS,"::[" + browser + "]:: TC13 - Button Tiep theo is displayed.");
        }
        // Input value from Array to Email
        for(int i = 0; i <= global.INPUT_VALIDATION.length; i++){
            global.Send_Keys(ele_txtboxEmail, global.INPUT_VALIDATION[i]);
            System.out.println("DEBUG --- Input Validation: " + global.INPUT_VALIDATION[i]);
            global.Click(ele_btnTieptheo);
            ele_txtboxEmail.clear();
        }
        global.Processing_Time();
    }
    // End test and export report
    @AfterClass
    public void End_Test() {
        exReport.endTest(exTest);
        exReport.flush();
        driver.close();
        global.Processing_Time();
    }
}