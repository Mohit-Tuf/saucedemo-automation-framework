package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

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
        return isDisplayed(pageTitle);
    }

    public String getCompleteHeader() {
        return getText(completeHeader);
    }

    public String getCompleteMessage() {
        return getText(completeText);
    }

    public InventoryPage clickBackHome() {
        click(backHomeButton);
        return new InventoryPage(driver);
    }

}
