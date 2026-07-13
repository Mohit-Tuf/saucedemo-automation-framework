package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void verifyApplicationLaunch(){
        String actualTitle = DriverFactory.getDriver().getTitle();
        Assert.assertEquals(actualTitle, "Swag Labs");
    }

}
