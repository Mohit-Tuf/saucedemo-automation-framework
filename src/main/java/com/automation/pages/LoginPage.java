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


    public InventoryPage login(String username, String password){
        type(userNameInput, username);
        type(passwordInput, password);

        click(loginButton);

        return new InventoryPage(driver);
    }

}
