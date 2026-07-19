package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.automation.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

public class CheckoutOverviewPage extends BasePage {

    private static final Logger logger = LoggerUtils.getLogger(CheckoutOverviewPage.class);

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
        logger.info("Verifying Checkout Overview page is displayed");

        return isDisplayed(pageTitle);
    }

    public boolean isProductDisplayed(String productName) {
        logger.info("Verifying product is displayed on Checkout Overview: {}", productName);

        return isDisplayed(getProductNameLocator(productName));
    }

    public String getProductName(String productName) {
        logger.info("Reading product name: {}", productName);

        return getText(getProductNameLocator(productName));
    }

    public String getProductPrice(String productName) {

        String price = getText(getProductPriceLocator(productName));

        logger.info("Product '{}' price: {}", productName, price);

        return getText(getProductPriceLocator(productName));
    }

    public String getPaymentInformation() {
        logger.info("Reading payment information");

        return getText(paymentInformation);
    }

    public String getShippingInformation() {
        logger.info("Reading shipping information");

        return getText(shippingInformation);
    }

    public String getItemTotal() {
        logger.info("Reading item total");

        return getText(itemTotal);
    }

    public String getTax() {
        logger.info("Reading tax");

        return getText(tax);
    }

    public String getTotal() {
        logger.info("Reading order total");

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
        logger.info("Completing the order");

        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public InventoryPage clickCancel() {
        logger.info("Cancelling checkout and returning to Inventory");

        click(cancelButton);
        return new InventoryPage(driver);
    }
}