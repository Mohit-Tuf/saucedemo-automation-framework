package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.automation.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

public class CheckoutCompletePage extends BasePage {

    private static final Logger logger = LoggerUtils.getLogger(CheckoutCompletePage.class);

    private final By pageTitle =
            By.cssSelector("[data-test='title']");

    private final By completeHeader =
            By.cssSelector("[data-test='complete-header']");

    private final By completeText =
            By.cssSelector("[data-test='complete-text']");

    private final By backHomeButton =
            By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver){
        super(driver);
    }

    public boolean isCheckoutCompletePageDisplayed() {
        logger.info("Verifying Checkout Complete page is displayed");

        return isDisplayed(pageTitle);
    }

    public String getCompleteHeader() {
        logger.info("Reading order completion header");

        return getText(completeHeader);
    }

    public String getCompleteMessage() {
        logger.info("Reading order completion message");

        return getText(completeText);
    }

    public InventoryPage clickBackHome() {
        logger.info("Returning to Inventory page");

        click(backHomeButton);
        return new InventoryPage(driver);
    }

}
