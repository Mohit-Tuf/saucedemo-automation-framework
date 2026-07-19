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

//end to end regression/smoke testing
//The complete purchase flow succeeds from login to returning to the inventory page.
public class EndToEndTest extends BaseTest {

    @Test(description = "Verify complete purchase flow")
    public void verifyCompletePurchaseFlow() {

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
                "End-to-end purchase flow failed."
        );
    }
}