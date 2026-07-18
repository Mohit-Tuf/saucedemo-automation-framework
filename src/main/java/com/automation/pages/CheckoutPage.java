package com.automation.pages;

import com.automation.base.BasePage;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By pageTitle = By.className("title");

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public boolean isCheckoutPageDisplayed(){
        return isDisplayed(pageTitle);
    }

}
