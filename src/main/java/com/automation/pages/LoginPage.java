package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    private final By userNameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    private final By errorMessage =
            By.cssSelector("h3[data-test='error']");


    public void enterUserName(String username){
        type(userNameInput, username);
    }

    public void enterPassword(String password){
        type(passwordInput, password);
    }

    public void clickLoginBtn(){
        click(loginButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isLoginPageDisplayed(){
        return isDisplayed(userNameInput);
    }

    public InventoryPage login(String username, String password){
//        type(userNameInput, username);
//        type(passwordInput, password);
//        click(loginButton);
//        return new InventoryPage(driver);

        enterUserName(username);
        enterPassword(password);
        clickLoginBtn();

        return new InventoryPage(driver);

    }

}
