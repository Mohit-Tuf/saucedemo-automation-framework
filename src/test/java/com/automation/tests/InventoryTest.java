package com.automation.tests;

import com.automation.base.BasePage;
import com.automation.base.BaseTest;
import com.automation.constants.TestData;
import com.automation.driver.DriverFactory;
import com.automation.pages.CartPage;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {

    @Test(description = "Verify user can add a single product to cart")
    public void verifyAddSingleProductToCart(){

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page is not displayed.");

        inventoryPage.addProductToCart(TestData.BACKPACK);

        Assert.assertEquals(inventoryPage.getCartBadgeCount(),
                1,
                "Cart badge count is incorrect.");

    }

    @Test(description = "Verify user can add multiple products to cart")
    public void verifyMultipleProductsToCart(){

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page is not displayed.");

        inventoryPage.addProductToCart(TestData.BACKPACK);
        inventoryPage.addProductToCart(TestData.BIKE_LIGHT);

        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 2, "Cart badge count is incorrect");


    }

    @Test(description = "Verify user can remove product from cart")
    public void verifyRemoveProductFromCart(){
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page is not displayed.");

        inventoryPage.addProductToCart(TestData.BACKPACK);

        Assert.assertEquals(inventoryPage.getCartBadgeCount(),1, "Cart badge count is incorrect after adding product.");

        inventoryPage.removeProductFromCart(TestData.BACKPACK);

        Assert.assertEquals(inventoryPage.getCartBadgeCount(),0, "Cart badge should disappear after removing the product.");
    }

    @Test(description = "Verify user can navigate to cart page")
    public  void verifyOpenCart(){

        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        InventoryPage inventoryPage = loginPage.login(TestData.STANDARD_USER, TestData.PASSWORD);

        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory Page is not displayed");

        inventoryPage.addProductToCart(TestData.BACKPACK);

        CartPage cartPage = inventoryPage.openCart();

        Assert.assertTrue(cartPage.isCartPageDisplayed(),
                "Cart page is not displayed");
    }

}
