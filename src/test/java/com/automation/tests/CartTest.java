package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.constants.TestData;
import com.automation.driver.DriverFactory;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private CartPage cartPage;

    @BeforeMethod
    public void navigateToCart() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        cartPage = inventoryPage.openCart();
    }

    @Test(description = "Verify user can view added product in cart")
    public void verifyAddedProductIsDisplayedInCart() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page is not displayed."
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        Assert.assertTrue(
                cartPage.isCartPageDisplayed(),
                "Cart page is not displayed."
        );

        Assert.assertTrue(
                cartPage.isProductDisplayed(TestData.BACKPACK),
                "Expected product is not displayed in the cart."
        );
    }

    @Test(description = "Verify correct product price is displayed in cart")
    public void verifyProductPriceInCart() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        Assert.assertEquals(
                cartPage.getProductPrice(TestData.BACKPACK),
                TestData.BACKPACK_PRICE,
                "Incorrect product price displayed."
        );
    }

    @Test(description = "Verify user can remove product from cart")
    public void verifyRemoveProductFromCart() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        cartPage.removeProduct(TestData.BACKPACK);

        Assert.assertFalse(
                cartPage.isProductDisplayed(TestData.BACKPACK),
                "Product was not removed from the cart."
        );
    }

    @Test(description = "Verify Continue Shopping navigates back to inventory page")
    public void verifyContinueShoppingNavigation() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        InventoryPage returnedInventoryPage =
                cartPage.clickContinueShopping();

        Assert.assertTrue(
                returnedInventoryPage.isInventoryPageDisplayed(),
                "Inventory page is not displayed."
        );
    }

    @Test(description = "Verify Checkout navigates to checkout page")
    public void verifyCheckoutNavigation() {

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        CheckoutPage checkoutPage =
                cartPage.clickCheckout();

        Assert.assertTrue(
                checkoutPage.isCheckoutPageDisplayed(),
                "Checkout page is not displayed."
        );
    }

}

