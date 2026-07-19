package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerUtils.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver){
        super(driver);
    }

    private final By userNameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    private final By errorMessage =
            By.cssSelector("h3[data-test='error']");


    public void enterUserName(String username){
        logger.info("Entering username");
        type(userNameInput, username);
    }

    public void enterPassword(String password){
        logger.info("Entering password");
        type(passwordInput, password);
    }

    public void clickLoginBtn(){
        logger.info("Clicking Login button");
        click(loginButton);
    }

    public String getErrorMessage() {
        logger.info("Reading login error message");
        return getText(errorMessage);
    }

    public boolean isLoginPageDisplayed(){
        logger.info("Verifying Login Page is displayed");
        return isDisplayed(userNameInput);
    }

    public InventoryPage login(String username, String password){
//        type(userNameInput, username);
//        type(passwordInput, password);
//        click(loginButton);
//        return new InventoryPage(driver);
        logger.info("Performing login");

        enterUserName(username);
        enterPassword(password);
        clickLoginBtn();

        logger.info("Login request submitted");

        return new InventoryPage(driver);

    }

}
