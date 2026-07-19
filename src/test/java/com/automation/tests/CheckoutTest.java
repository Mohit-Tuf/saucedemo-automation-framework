package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.constants.TestData;
import com.automation.driver.DriverFactory;
import com.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(description = "Verify successful checkout information submission")
    public void verifySuccessfulCheckoutInformation() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        CheckoutOverviewPage checkoutOverviewPage =
                checkoutPage.fillCheckoutInformation(
                        "Mohit",
                        "Singh",
                        "110098"
                );

        Assert.assertTrue(
                checkoutOverviewPage.isCheckoutOverviewPageDisplayed(),
                "Checkout Overview page is not displayed."
        );
    }

    @Test(description = "Verify error message when First Name is empty")
    public void verifyEmptyFirstNameValidation() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        checkoutPage.enterLastName(TestData.LAST_NAME);
        checkoutPage.enterPostalCode(TestData.POSTAL_CODE);
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: First Name is required",
                "Incorrect validation message displayed."
        );
    }

    @Test(description = "Verify error message when Last Name is empty")
    public void verifyEmptyLastNameValidation(){
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        checkoutPage.enterFirstName(TestData.FIRST_NAME);
        checkoutPage.enterPostalCode(TestData.POSTAL_CODE);
        checkoutPage.clickContinue();

        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required",
                "Incorrect validation message displayed");

    }

    @Test(description = "Verify error message when Postal Code is empty")
    public void verifyEmptyPostalCodeValidation(){
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        checkoutPage.enterFirstName(TestData.FIRST_NAME);
        checkoutPage.enterLastName(TestData.LAST_NAME);
        checkoutPage.clickContinue();

        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required",
                "Incorrect validation message displayed");

    }

    @Test(description = "Verify Cancel button navigates back to Cart page")
    public void verifyCancelCheckout() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        CartPage returnedCartPage = checkoutPage.clickCancel();

        Assert.assertTrue(
                returnedCartPage.isCartPageDisplayed(),
                "Cart page is not displayed."
        );
    }

}
