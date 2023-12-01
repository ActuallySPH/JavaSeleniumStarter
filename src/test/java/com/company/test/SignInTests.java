package com.company.test;

import com.company.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTests extends BaseTest {

    @Test
    public void VerifySignInPageHeader() {
        SignInPage signInPage = new SignInPage(driver);
        Assert.assertEquals(signInPage.verifySignInPageHeader(), "Login");
    }
}
