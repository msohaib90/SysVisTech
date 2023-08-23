package org.example;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

public class Main {

    static ExtentReports report;
    static ExtentTest test0;
    static ExtentTest test1;
    static ExtentTest test2;
    static ExtentTest test3;
    static WebDriver driver;
    static String SCpath = "D:\\Automation\\SysVisTech\\Automation_Challenge\\Screenshots";

    @BeforeClass
    public void startTest() {
        report = new ExtentReports(System.getProperty("D:\\Automation\\SysVisTech\\Automation_Challenge\\") + "ExtentReportResults.html");
        test0 = report.startTest("1. Test Title");
        test1 = report.startTest("2. Login Using Valid Credentials");
        test2 = report.startTest("3. Locked-Out-User Unable to Login");
        test3 = report.startTest("4. Add Product To Shopping Cart");

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }


    @Test
    public void testCase_0() {
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, expectedTitle, "Title doesn't match");

        if (actualTitle.equals(expectedTitle)) {
            test0.log(LogStatus.PASS, "Status: PASS");
            test0.log(LogStatus.INFO, "Title: " + actualTitle);
            test0.log(LogStatus.INFO, "Navigated to the specified URL");
            test0.log(LogStatus.INFO, "Current URL: " + driver.getCurrentUrl());

            String ExpectedResult = "Swag Labs";
            String ActualResult = driver.getTitle();

            Assert.assertEquals(ExpectedResult,ActualResult);

            try {
                takeSnapShot(driver, SCpath + "\\title.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            test0.log(LogStatus.INFO, test0.addScreenCapture(SCpath + "\\title.png"));
        }
        else {
            test0.log(LogStatus.FAIL, "Test Failed");
        }

        softAssert.assertAll();
    }

    @Test
    public void testCase_1() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name")));
            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));

            usernameInput.sendKeys("standard_user");
            passwordInput.sendKeys("secret_sauce");
            String ActualResult = usernameInput.getAttribute("value");
            String ActualResult1 = passwordInput.getAttribute("value");

            try {
                takeSnapShot(driver, SCpath + "\\Username.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            WebElement LoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login-button']")));
            LoginButton.click();

            test1.log(LogStatus.PASS, "Status: PASS");
            test1.log(LogStatus.INFO, "Valid Username & Password Signing Successful");
            test1.log(LogStatus.INFO, "Login Using Valid Credentials Successfully");

            String ExpectedResult = "standard_user";
            String ExpectedResult1 = "secret_sauce";

            Assert.assertEquals(ActualResult+ActualResult1,ExpectedResult+ExpectedResult1);

            try {
                takeSnapShot(driver, SCpath + "\\Login_Button.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            test1.log(LogStatus.INFO,"Before Login"+ test1.addScreenCapture(SCpath + "\\Username.png"));
            test1.log(LogStatus.INFO,"After Login"+ test1.addScreenCapture(SCpath + "\\Login_Button.png"));
        }
        catch (TimeoutException e) {
            e.printStackTrace();
            test1.log(LogStatus.FAIL, "Test Failed");
        }

        driver.close();
    }

    @Test
    public void testCase_2() {
        try {
            driver = new ChromeDriver();
            driver.get("https://www.saucedemo.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name")));
            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));

            usernameInput.sendKeys("locked_out_user");
            passwordInput.sendKeys("secret_sauce");

            WebElement LoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login-button']")));
            LoginButton.click();

            String A1 = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/h3[1]")).getText();
            System.out.println("Error Message for Locked User: "+A1);

            test2.log(LogStatus.PASS, "Status: PASS");
            test2.log(LogStatus.INFO, "Locked-Out-User Unable to Sign-In Successful");
            test2.log(LogStatus.INFO, "Locked-Out-User Status Message: Epic sadface: Sorry, this user has been locked out.");

            String ExpectedResult = "Epic sadface: Sorry, this user has been locked out.";

            String ActualResult = A1;

            Assert.assertEquals(ExpectedResult, ActualResult);

            try {
                takeSnapShot(driver, SCpath + "\\locked_out_user.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            test2.log(LogStatus.INFO,"Locked User Error Message"+ test2.addScreenCapture(SCpath + "\\locked_out_user.png"));
        }
        catch (TimeoutException e) {
            e.printStackTrace();
            test2.log(LogStatus.FAIL, "Test Failed");
        }

        driver.close();
    }


    @Test
    public void testCase_3() {
        try {
            driver = new ChromeDriver();
            driver.get("https://www.saucedemo.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name")));
            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));

            usernameInput.sendKeys("standard_user");
            passwordInput.sendKeys("secret_sauce");

            //Login Button Click
            WebElement LoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login-button']")));
            LoginButton.click();

            try {
                takeSnapShot(driver, SCpath + "\\Product_Catalog.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            //Select Product
            WebElement SelectProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Test.allTheThings() T-Shirt (Red)']")));
            WebElement SelectProduct1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Test.allTheThings() T-Shirt (Red)']")));

            String productName = SelectProduct1.getText();
            System.out.println("Value of ProductName in Home Page: "+productName);

            SelectProduct.click();

            try {
                takeSnapShot(driver, SCpath + "\\SelectedProduct.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            //Add Product
            WebElement AddProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']")));
            AddProduct.click();

            try {
                takeSnapShot(driver, SCpath + "\\AddProduct.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            //View Cart
            WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='shopping_cart_link']")));
            viewCart.click();

            //Assertion
            WebElement verifyCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='inventory_item_name']")));
            String verifyProduct = verifyCart.getText();
            System.out.println("Value of ProductName in Cart View: "+verifyProduct);

            String ExpectedResult1 = "Test.allTheThings() T-Shirt (Red)";
            String ExpectedResult2 = "Test.allTheThings() T-Shirt (Red)";
            String ActualResult1 = productName;
            String ActualResult2 = productName;

            Assert.assertEquals(ActualResult1+ActualResult2, ExpectedResult1+ExpectedResult2);

            try {
                takeSnapShot(driver, SCpath + "\\viewCart.png");
            }
            catch (Exception e) {
                e.printStackTrace();
            }


            test3.log(LogStatus.PASS, "Status: PASS");
            test3.log(LogStatus.INFO, "Login Successful with Standard User");
            test3.log(LogStatus.INFO, "Product Catalog Page");
            test3.log(LogStatus.INFO, "Select Product from Catalog");
            test3.log(LogStatus.INFO, "Add Product To Cart from Catalog Page");
            test3.log(LogStatus.INFO, "View Product in Cart Selected from Catalog Page");

            test3.log(LogStatus.INFO,"Home Page"+ test3.addScreenCapture(SCpath + "\\Product_Catalog.png"));
            test3.log(LogStatus.INFO,"Product Selection"+ test3.addScreenCapture(SCpath + "\\SelectedProduct.png"));
            test3.log(LogStatus.INFO,"Product Added To Cart"+ test3.addScreenCapture(SCpath + "\\AddProduct.png"));
            test3.log(LogStatus.INFO,"View Cart"+ test3.addScreenCapture(SCpath + "\\viewCart.png"));
        }

        catch (TimeoutException e) {
            e.printStackTrace();
            test3.log(LogStatus.FAIL, "Test Failed");
        }
    }


    @AfterClass
    public void endTest() {
        report.endTest(test0);
        report.endTest(test1);
        report.endTest(test2);
        report.endTest(test3);
        report.flush();
        driver.close();
    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        org.apache.commons.io.FileUtils.copyFile(SrcFile, DestFile);
    }
}
