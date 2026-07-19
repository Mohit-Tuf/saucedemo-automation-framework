package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

public class CheckoutPage extends BasePage {

//    private final By pageTitle = By.className("title");
//    public boolean isCheckoutPageDisplayed(){
//        return isDisplayed(pageTitle);
//    }

    private static final Logger logger = LoggerUtils.getLogger(CheckoutPage.class);

    private final By firstNameTextBox = By.id("first-name");
    private final By lastNameTextBox = By.id("last-name");
    private final By postalCodeTextBox = By.id("postal-code");

    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");

    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        logger.info("Entering first name");

        type(firstNameTextBox, firstName);
    }

    public void enterLastName(String lastName) {
        logger.info("Entering last name");

        type(lastNameTextBox, lastName);
    }

    public void enterPostalCode(String postalCode) {
        logger.info("Entering postal code");

        type(postalCodeTextBox, postalCode);
    }

    public CheckoutOverviewPage clickContinue() {
        logger.info("Proceeding to Checkout Overview");

        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public CartPage clickCancel() {
        logger.info("Cancelling checkout and returning to Cart");

        click(cancelButton);
        return new CartPage(driver);
    }

    public CheckoutOverviewPage fillCheckoutInformation(String firstName,
                                                        String lastName,
                                                        String postalCode) {
        logger.info("Filling checkout information");

        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);

        return clickContinue();
    }

    public String getErrorMessage() {
        logger.info("Reading checkout validation error message");

        return getText(errorMessage);
    }

    public boolean isCheckoutPageDisplayed() {
        logger.info("Verifying Checkout Information page is displayed");

        return isDisplayed(firstNameTextBox);
    }



}
