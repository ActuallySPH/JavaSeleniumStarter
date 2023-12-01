package com.company.test;

import com.company.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePageTests extends BaseTest {

    @Test
    public void VerifyHomePageHeader() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.verifyHomePageHeader(), "The metrics displayed are representative sample data. To see personalized metrics upload sales history. Upload sales history", "Home page header assertion failed");
    }
}
