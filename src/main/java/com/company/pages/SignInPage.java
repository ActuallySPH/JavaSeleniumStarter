package com.company.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SignInPage extends BasePage {
    @FindBy(css = ".login100-form-title")
    private WebElement signInPageHeader;

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public String verifySignInPageHeader() {
        waitUntilWebElementIsVisible(signInPageHeader);
        return getTextFromWebElement(signInPageHeader).trim();

    }

}
