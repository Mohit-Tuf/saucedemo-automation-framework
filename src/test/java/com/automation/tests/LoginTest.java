package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.constants.TestData;
import com.automation.driver.DriverFactory;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.pages.components.NavigationMenu;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;



public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin(){

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

//        InventoryPage inventoryPage = loginPage.login("standard_user",
//                "secret_sauce");
        InventoryPage inventoryPage = loginPage.login(TestData.STANDARD_USER,
                TestData.PASSWORD);

        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(),
                "Login Failed. Inventory page is not displayed.");


    }

    @Test
    public void verifyInvalidLogin(){

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

//        loginPage.enterUserName("invalid_user");
//        loginPage.enterPassword("invalid_password");

        loginPage.enterUserName(TestData.INVALID_USERNAME);
        loginPage.enterPassword(TestData.INVALID_PASSWORD);
        loginPage.clickLoginBtn();

        String expectedError =
                "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(loginPage.getErrorMessage(), expectedError, "Incorrect error message displayed.");

    }

    @Test
    public void verifyLockedUserLogin(){

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        loginPage.enterUserName(TestData.LOCKED_USER);
        loginPage.enterPassword(TestData.PASSWORD);
        loginPage.clickLoginBtn();

        Assert.assertEquals(loginPage.getErrorMessage(), TestData.LOCKED_USER_ERROR, "Locked user error message is incorrect.");

    }

    @Test
    public void verifyLogout(){

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());


        InventoryPage inventoryPage = loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        NavigationMenu navigationMenu = new NavigationMenu(DriverFactory.getDriver());



        LoginPage loggedOutPage = navigationMenu.logout();

        Assert.assertTrue(loggedOutPage.isLoginPageDisplayed(), "Logout failed. Login page is not displayed.");

    }



}
