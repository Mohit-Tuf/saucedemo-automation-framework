package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;



public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin(){

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login("standard_user",
                "secret_sauce");

        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(),
                "Login Failed. Inventory page is not displayed.");


    }



}
