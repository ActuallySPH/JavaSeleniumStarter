package com.company.test;

import com.company.pages.SignInPage;
import com.company.utilities.BrowserFactory;
import com.company.utilities.Screenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;

    @Parameters({"browserLanguage", "browserVersion", "browserName"})
    @BeforeSuite
    public void open(@Optional("") String browserLanguage, @Optional("") String browserVersion, String browserName, Method method) {
        try {
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.createLocalBrowserInstance(browserName, browserLanguage, browserVersion);
            String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "TestData" + File.separator + "UserData.xlsx";

            //driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }
    }

    @Parameters({"appiumVersion", "deviceName", "deviceOrientation", "platformVersion", "platformName", "browserLanguage", "browserVersion", "browserName"})
    @BeforeMethod
    public void openApplication(@Optional("") String appiumVersion, @Optional("") String deviceName, @Optional("") String deviceOrientation, @Optional("") String platformVersion, @Optional("") String platformName, @Optional("") String browserLanguage, @Optional("") String browserVersion, String browserName, Method method) {
        try {
            System.out.println("Started Executing : " + method.getName());
            driver.get(System.getProperty("WebAppURL"));
            SignInPage signInPage = new SignInPage(driver);
            Assert.assertEquals(signInPage.verifySignInPageHeader(), "Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result) {
        try {
            System.out.println("Completed Executing : " + method.getName());
            Screenshot screenshot = new Screenshot(driver);
            if (screenshot.checkMultipleScreenCaptured(result.getTestContext().getCurrentXmlTest().getName(), method.getName())) {
                screenshot.appendMultipleScreenCaptured(result.getTestContext().getCurrentXmlTest().getName() + "\\" + result.getTestClass().getName(), method.getName());
            } else {
                screenshot.captureScreen(result.getTestContext().getCurrentXmlTest().getName() + "\\" + result.getTestClass().getName(), method.getName());
            }
            driver.quit();
        } catch (Exception e) {
            driver.quit();
            e.printStackTrace();
        }
    }
}

