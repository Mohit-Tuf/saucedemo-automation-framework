package com.automation.dataproviders;

import com.automation.constants.TestData;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {

        return new Object[][]{
                {
                        TestData.STANDARD_USER,
                        TestData.PASSWORD
                },
                {
                        TestData.PROBLEM_USER,
                        TestData.PASSWORD
                },
                {
                        TestData.PERFORMANCE_USER,
                        TestData.PASSWORD
                }
        };
    }
}