package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By pageTitle = By.className("title");

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public boolean isInventoryPageDisplayed(){
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
        click(getProductActionButton(productName));
    }

    public void removeProductFromCart(String productName){
        click(getProductActionButton(productName));
    }

    public int getCartBadgeCount() {

        if (!isElementPresent(shoppingCartBadge)) {
            return 0;
        }

        return Integer.parseInt(getText(shoppingCartBadge));
    }

    public CartPage openCart(){
        click(shoppingCart);

        return new CartPage(driver);
    }

}
