package com.automation.base;

import com.automation.constants.FrameworkConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    protected Actions actions;

    public BasePage(WebDriver driver){

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);

    }

    protected void click(By locator){

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();

    }

    protected void type(By locator, String text){

        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(text);

    }

    protected void clear(By locator){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
    }

    protected String getText(By locator){

        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();

    }

    protected boolean isDisplayed(By locator){
//        return element.isDisplayed();// lets make it more safe
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            return element.isDisplayed();

        } catch (Exception e){
            return false;
        }

    }

}
