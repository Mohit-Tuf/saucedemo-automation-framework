package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.constants.TestData;
import com.automation.driver.DriverFactory;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutOverviewPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutOverviewTest extends BaseTest {

    @Test(description = "Verify checkout overview details")
    public void verifyCheckoutOverviewDetails() {

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

        Assert.assertTrue(
                overviewPage.isCheckoutOverviewPageDisplayed(),
                "Checkout Overview page is not displayed."
        );

        Assert.assertEquals(
                overviewPage.getProductName(TestData.BACKPACK),
                TestData.BACKPACK,
                "Incorrect product name displayed."
        );

        Assert.assertEquals(
                overviewPage.getProductPrice(TestData.BACKPACK),
                TestData.BACKPACK_PRICE,
                "Incorrect product price displayed."
        );

        Assert.assertEquals(
                overviewPage.getPaymentInformation(),
                TestData.PAYMENT_INFORMATION,
                "Incorrect payment information."
        );

        Assert.assertEquals(
                overviewPage.getShippingInformation(),
                TestData.SHIPPING_INFORMATION,
                "Incorrect shipping information."
        );

        double expectedTotal =
                overviewPage.getItemTotalValue() +
                        overviewPage.getTaxValue();

        Assert.assertEquals(
                overviewPage.getTotalValue(),
                expectedTotal,
                0.01,
                "Incorrect total amount displayed."
        );
    }
}