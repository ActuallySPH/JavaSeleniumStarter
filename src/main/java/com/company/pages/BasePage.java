package com.company.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    protected WebDriver driver;

    BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForJsLoad(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
            wait.until(pageLoadCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForJqueryLoad(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            ExpectedCondition<Boolean> pageLoadCondition = driver1 -> {
                try {
                    return ((Long) ((JavascriptExecutor) driver1).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            };
            wait.until(pageLoadCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilWebElementIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
    }

    public String getTextFromWebElement(WebElement ele) {
        waitUntilWebElementIsVisible(ele);
        return ele.getText();
    }

}