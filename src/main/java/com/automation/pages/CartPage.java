package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

public class CartPage extends BasePage {

    private static final Logger logger = LoggerUtils.getLogger(CartPage.class);

    private final By cartList = By.className("cart_list");

    private final By continueShoppingBtn = By.id("continue-shopping");

    private final By checkoutBtn = By.id("checkout");

    // Dynamic Locators
    private final String cartProductXpath =
            "//div[@data-test='inventory-item-name' and normalize-space(text())='%s']";

    private final String removeButtonXpath =
            "//div[@data-test='inventory-item-name' and normalize-space(text())='%s']" +
                    "/ancestor::div[contains(@class,'cart_item')]//button";

    private final String productPriceXpath =
            "//div[@data-test='inventory-item-name' and normalize-space(text())='%s']" +
                    "/ancestor::div[contains(@class,'cart_item')]" +
                    "//div[@class='inventory_item_price']";

    public CartPage(WebDriver driver){
        super(driver);
    }

    private By getCartProductLocator(String productName){
        return By.xpath(String.format(cartProductXpath, productName));

    }

    private By getRemoveButtonLocator(String productName){
        return By.xpath(String.format(removeButtonXpath, productName));

    }

    private By getProductPriceLocator(String productName){
        return By.xpath(String.format(productPriceXpath, productName));

    }

    public boolean isCartPageDisplayed(){
        logger.info("Verifying Cart page is displayed");

        return isDisplayed(cartList);
    }

    public boolean isProductDisplayed(String productName){
        logger.info("Verifying product is displayed in cart: {}", productName);

        return isDisplayed(getCartProductLocator(productName));
    }

    public String getProductPrice(String productName){

        String price = getText(getProductPriceLocator(productName));
        logger.info("Product '{}' price: {}", productName, price);

        return getText(getProductPriceLocator(productName));
    }

    public void removeProduct(String productName){
        logger.info("Removing product from cart: {}", productName);

        click(getRemoveButtonLocator(productName));
    }

    public InventoryPage clickContinueShopping(){

        logger.info("Clicking Continue Shopping");

        click(continueShoppingBtn);
        return new InventoryPage(driver);
    }

    public CheckoutPage clickCheckout(){

        logger.info("Proceeding to Checkout");

        click(checkoutBtn);
        return new CheckoutPage(driver);
    }



}
