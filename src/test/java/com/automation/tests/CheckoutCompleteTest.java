package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.constants.TestData;
import com.automation.driver.DriverFactory;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutCompletePage;
import com.automation.pages.CheckoutOverviewPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutCompleteTest extends BaseTest {

    @Test(description = "Verify successful order completion")
    public void verifySuccessfulOrderCompletion() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        CheckoutOverviewPage overviewPage =
                checkoutPage.fillCheckoutInformation(
                        TestData.FIRST_NAME,
                        TestData.LAST_NAME,
                        TestData.POSTAL_CODE
                );

        CheckoutCompletePage completePage =
                overviewPage.clickFinish();

        Assert.assertTrue(
                completePage.isCheckoutCompletePageDisplayed(),
                "Checkout Complete page is not displayed."
        );

        Assert.assertEquals(
                completePage.getCompleteHeader(),
                TestData.COMPLETE_HEADER,
                "Incorrect success header displayed."
        );

        Assert.assertEquals(
                completePage.getCompleteMessage(),
                TestData.COMPLETE_MESSAGE,
                "Incorrect success message displayed."
        );
    }

    @Test(description = "Verify Back Home button navigates to Inventory page")
    public void verifyBackHomeNavigation() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        CheckoutOverviewPage overviewPage =
                checkoutPage.fillCheckoutInformation(
                        TestData.FIRST_NAME,
                        TestData.LAST_NAME,
                        TestData.POSTAL_CODE
                );

        CheckoutCompletePage completePage =
                overviewPage.clickFinish();

        InventoryPage returnedInventoryPage =
                completePage.clickBackHome();

        Assert.assertTrue(
                returnedInventoryPage.isInventoryPageDisplayed(),
                "Inventory page is not displayed."
        );
    }
}