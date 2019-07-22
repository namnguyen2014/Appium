package WEB;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class GLOBAL {
    public static String chromeDriverPath = "/home/namnth/Downloads/Nam_Folder/WebDrivers/chromedriver";
    public static String firefoxDriverPath = "/home/namnth/Downloads/Nam_Folder/WebDrivers/geckodriver";
    public static ChromeOptions chromeOpts = new ChromeOptions();
    public static FirefoxOptions firefoxOpts;
    public static WebDriver driver;
    public static String[] INPUT_VALIDATION = {"","   ","1234567890","abcdefghij","!@#$%^&*()_+.,/",
            "abc@.com","abc@g.c","abc@gmail.com","<script>alert(‘hello’)</script>"};


    // Setting Chrome
    public static WebDriver Setting_Chrome() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        // Enable Chrome runs with automation mode
        //chromeOpts.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        // Disable Infor bar
        chromeOpts.addArguments("disable-infobars");
        // Disable extensions
        chromeOpts.addArguments("--disable-extensions");
        // Apply to driver
        driver = new ChromeDriver(chromeOpts);
        return driver;
    }

    // Wait for page loading
    public static void Wait_For_Page_Loading(int time) {
        // Wait 3s for page loading
        try {
            driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
            System.out.println(time + "s is over.");
        } catch (NullPointerException nullEx){
            nullEx.printStackTrace();
        }
    }
    // Access to URL
    public static void Access_URL_Chrome(WebDriver driver, String url) {
        driver.get(url);
        System.out.println("Accessed to: " + url);
    }

    // Read data from Excel
    public static String Read_Data_From_Excel(String excelFile, String sheetName, int rowNo, int cellNo) throws IOException {
        try {
            XSSFWorkbook excelPath = new XSSFWorkbook(new FileInputStream(excelFile));
            System.out.println("Filepath: " + excelFile);
            // Get sheet data
            XSSFSheet excelSheet = excelPath.getSheet(sheetName);
            System.out.println("Sheet Name: " + sheetName);
            // Get row data
            XSSFRow excelRow = excelSheet.getRow(rowNo);
            excelRow.getRowNum();

            String cellValue = null;
            if (excelRow.getCell(cellNo).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                // Get column data
                cellValue = excelRow.getCell(cellNo).getStringCellValue();
                System.out.println("Value is: " + cellValue);
                excelPath.close();
            }
            return cellValue;
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    //Get length of row
    public static String[] Get_Num_Of_Rows(String excelFile, String sheetName, int rowNo, int cellNo) throws IOException {
        try {
            String[] collValueArr = {};
            XSSFWorkbook excelPath = new XSSFWorkbook(new FileInputStream(excelFile));
            XSSFSheet excelSheet = excelPath.getSheet(sheetName);
            XSSFRow excelRow = excelSheet.getRow(rowNo);

            if(excelRow.getCell(cellNo).getCellType() == HSSFCell.CELL_TYPE_STRING){
                for(int i = 0; i <= excelRow.getPhysicalNumberOfCells(); i++){
                    String cellValue = excelRow.getCell(i).getStringCellValue();
                    System.out.println("DEBUG --- [i]: " + cellValue);
                    System.out.println("DEBUG --- Arr.Length " + collValueArr.length);
                }
            }

            return collValueArr;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // Find element by XPath
    public static WebElement Find_Element_By_XPath(WebDriver driver, String xPath) {
        WebElement ele = null;
        ele = driver.findElement(By.xpath(xPath));
        return ele;
    }

    // Find element by Classname
    public static WebElement Find_Element_By_ClassName(WebDriver driver, String className){
        WebElement ele = null;
        ele = driver.findElement(By.className(className));
        return ele;
    }

    // Find element by Css Selector
    public static WebElement Find_Element_By_Css_Selector(WebDriver driver, String cssSelector){
        WebElement ele = null;
        ele = driver.findElement(By.cssSelector(cssSelector));
        return ele;
    }

    // Find element by ID
    public static WebElement Find_Element_By_ID(WebDriver driver, String id) {
        WebElement ele;
        ele = driver.findElement(By.id(id));
        return ele;
    }

    // Send keys/values to element
    public static void Send_Keys(WebElement element, String textToSend) {
        element.sendKeys(textToSend);
        System.out.println("Sent keys: " + textToSend +  " to " + element);
    }

    // Click on element
    public static void Click(WebElement element) {
        element.click();
        System.out.println("Clicked : " + element);
    }

    // Quit driver
    public static void Quit_Driver(WebDriver driver) {
        // Deactivate driver
        driver.quit();
        System.out.println("Driver is quit !");
    }

    // Close driver
    public static void Close_Driver(WebDriver driver) {
        driver.close();
        System.out.println("Driver is closed !");
    }

    // Access URL
    public static void Access_URL(WebDriver driver, String url){
        driver.get(url);
        System.out.println("Accessed to " + url);
    }

    // Full screen Displaying
    public static void Maximize_Window(WebDriver driver){
        driver.manage().window().maximize();
        System.out.println("Maximized window.");
    }

    // Print text
    public static void Print(String content){
        System.out.println(content);
    }

    // Get element's text
    public static String Get_Text( WebElement ele){
        return ele.getText();
    }

    // Compare text
    public static boolean Compare_Text(String actualText, String expectedText){
        if(actualText.equals(expectedText) ){
            return true;
        }else
            return false;
    }

    // Check element is existed
    public static boolean Element_Is_Existed(WebElement ele){
        if (ele.isDisplayed()) {
            return true;
        }else
        return false;
    }

    // Scroll page
    public static void Scroll_Page(WebDriver driver, String jsExecutor){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript(jsExecutor); // use scroll/window.scrollBy("0,250","")
    }

    // Get excel length
    public static void Get_Excel_Length(String excelFile, String sheetName, int rowNo) throws IOException {
        XSSFWorkbook excelPath = new XSSFWorkbook(new FileInputStream(excelFile));
        System.out.println("Filepath: " + excelFile);
        // Get sheet data
        XSSFSheet excelSheet = excelPath.getSheet(sheetName);
        System.out.println("Sheet Name: " + sheetName);
        // Get row data
        excelSheet.getPhysicalNumberOfRows();
        System.out.println("Number of rows:");
    }

    // Hover action
    public static void Hover_Element(WebDriver driver, WebElement ele){
        Actions action = new Actions(driver);
        action.moveToElement(ele).build().perform();
        System.out.println("Hovered on: " + ele);
    }

    // Logs Browser
    public static void Logs(){
        LogEntries log = driver.manage().logs().get(LogType.BROWSER);
        for(LogEntry log_child : log){
            System.out.println(new Date(log_child.getTimestamp())
                    + " " + log_child.getLevel()
                    + " " + log_child.getMessage()
                    + " " + log_child.toString());
        }
    }

    // Calculating time
    public static void Processing_Time(){
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
    }
}