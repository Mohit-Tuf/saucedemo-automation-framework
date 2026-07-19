package com.automation.pages;

import com.automation.base.BasePage;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

//    private final By pageTitle = By.className("title");
//    public boolean isCheckoutPageDisplayed(){
//        return isDisplayed(pageTitle);
//    }

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
        type(firstNameTextBox, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameTextBox, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeTextBox, postalCode);
    }

    public CheckoutOverviewPage clickContinue() {
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public CartPage clickCancel() {
        click(cancelButton);
        return new CartPage(driver);
    }

    public CheckoutOverviewPage fillCheckoutInformation(String firstName,
                                                        String lastName,
                                                        String postalCode) {

        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);

        return clickContinue();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isCheckoutPageDisplayed() {
        return isDisplayed(firstNameTextBox);
    }



}
