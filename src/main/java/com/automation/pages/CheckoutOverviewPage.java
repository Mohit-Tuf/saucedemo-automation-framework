package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private final By pageTitle = By.cssSelector("[data-test='title']");

    private final By paymentInformation =
            By.cssSelector("[data-test='payment-info-value']");

    private final By shippingInformation =
            By.cssSelector("[data-test='shipping-info-value']");

    private final By itemTotal =
            By.cssSelector("[data-test='subtotal-label']");

    private final By tax =
            By.cssSelector("[data-test='tax-label']");

    private final By total =
            By.cssSelector("[data-test='total-label']");

    private final By finishButton = By.id("finish");

    private final By cancelButton = By.id("cancel");

    // Dynamic locators

    private final String productNameXpath =
            "//div[@data-test='inventory-item-name' and normalize-space(text())='%s']";

    private final String productPriceXpath =
            "//div[@data-test='inventory-item-name' and normalize-space(text())='%s']" +
                    "/ancestor::div[contains(@class,'cart_item')]" +
                    "//div[@class='inventory_item_price']";

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

//    public boolean isCheckoutOverviewPageDisplayed() {
//        return getText(pageTitle)
//                .equalsIgnoreCase("Checkout: Overview");
//    }

    private By getProductNameLocator(String productName) {
        return By.xpath(String.format(productNameXpath, productName));
    }

    private By getProductPriceLocator(String productName) {
        return By.xpath(String.format(productPriceXpath, productName));
    }

    public boolean isCheckoutOverviewPageDisplayed() {
        return isDisplayed(pageTitle);
    }

    public boolean isProductDisplayed(String productName) {
        return isDisplayed(getProductNameLocator(productName));
    }

    public String getProductName(String productName) {
        return getText(getProductNameLocator(productName));
    }

    public String getProductPrice(String productName) {
        return getText(getProductPriceLocator(productName));
    }

    public String getPaymentInformation() {
        return getText(paymentInformation);
    }

    public String getShippingInformation() {
        return getText(shippingInformation);
    }

    public String getItemTotal() {
        return getText(itemTotal);
    }

    public String getTax() {
        return getText(tax);
    }

    public String getTotal() {
        return getText(total);
    }

    public double getItemTotalValue() {
        return Double.parseDouble(
                getItemTotal()
                        .replace("Item total: $", "")
        );
    }

    public double getTaxValue() {
        return Double.parseDouble(
                getTax()
                        .replace("Tax: $", "")
        );
    }

    public double getTotalValue() {
        return Double.parseDouble(
                getTotal()
                        .replace("Total: $", "")
        );
    }

    public CheckoutCompletePage clickFinish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public InventoryPage clickCancel() {
        click(cancelButton);
        return new InventoryPage(driver);
    }
}