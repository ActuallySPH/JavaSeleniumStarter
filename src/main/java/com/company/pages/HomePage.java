package com.company.pages;

import com.company.utilities.Screenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Reporter;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "")
    private WebElement homePageContents;

    @FindBy(css = "")
    private List<WebElement> homePageHeader;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public boolean verifyHomePage() {
        waitForJsLoad(driver);
        waitForJqueryLoad(driver);
        waitUntilWebElementIsVisible(homePageContents);
        return homePageContents.isDisplayed();
    }


    public void getMultipleScreenCapture(String methodName, int i) {
        if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platformName") == null) {
            Screenshot screenshot = new Screenshot(driver);
            screenshot.captureScreen(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getName() + "\\MultipleScreens\\" + methodName + "\\", "item" + i);
        }

    }

    public String verifyHomePageHeader() {
        return getTextFromWebElement(homePageHeader.get(0));
    }

}
