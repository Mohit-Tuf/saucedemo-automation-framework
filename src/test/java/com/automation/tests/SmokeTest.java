package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.constants.TestData;
import com.automation.driver.DriverFactory;
import com.automation.listeners.RetryAnalyzer;
import com.automation.pages.LoginPage;
import com.automation.utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void verifyApplicationLaunch(){
        String actualTitle = DriverFactory.getDriver().getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs");
    }

    @Test
    public void testScreenshot() {

//        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
//
//        loginPage.login(
//                TestData.STANDARD_USER,
//                TestData.PASSWORD
//        );
//
//        // manually taking screen shots to remove this we use TestListner
//        ScreenshotUtils.captureScreenshot("test-screenshot");

        Assert.fail("Intentional failure for screenshot testing"); // to check listener working or not
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRetryMechanism() {

        Assert.fail("Intentional failure for retry testing");
    }

}
