package com.automation.pages;

import com.automation.base.BasePage;
import com.automation.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private static final Logger logger = LoggerUtils.getLogger(InventoryPage.class);

    private final By pageTitle = By.className("title");

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public boolean isInventoryPageDisplayed(){

        logger.info("Verifying Inventory page is displayed");

        return isDisplayed(pageTitle);
    }

    //for pure inventory page
    private final By inventoryList = By.className("inventory_list");

    private final By shoppingCart = By.className("shopping_cart_link");

    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    //addToCartButtonXpath
    private final String addToCartButtonXpath =
            "//div[@class='inventory_item_name ' and text()='%s']" +
                    "/ancestor::div[@class='inventory_item']//button";

    private By getProductActionButton(String productName) {
        return By.xpath(String.format(addToCartButtonXpath, productName));
    }

    public void addProductToCart(String productName) {

        logger.info("Adding product to cart: {}", productName);

        click(getProductActionButton(productName));
    }

    public void removeProductFromCart(String productName){

        logger.info("Removing product from cart: {}", productName);

        click(getProductActionButton(productName));
    }

    public int getCartBadgeCount() {

        if (!isElementPresent(shoppingCartBadge)) {

            logger.info("Cart badge not displayed. Cart is empty.");

            return 0;
        }

        int badgeCount = Integer.parseInt(getText(shoppingCartBadge));

        logger.info("Cart badge count: {}", badgeCount);
        return Integer.parseInt(getText(shoppingCartBadge));
    }

    public CartPage openCart(){
        logger.info("Opening shopping cart");

        click(shoppingCart);

        return new CartPage(driver);
    }

}
