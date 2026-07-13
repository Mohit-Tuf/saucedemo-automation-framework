package com.automation.driver;

import com.automation.utils.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//DriverFactory manages the browser lifecycle
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //prevent instantiation
    private DriverFactory(){

    }

    public static void intializeDriver(){

        String browser = PropertyUtils.getProperty("browser");
        boolean headless = Boolean.parseBoolean(PropertyUtils.getProperty("headless"));

        if("chrome".equalsIgnoreCase(browser)){

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            if(headless){
                options.addArguments("--headless=new");
            }

            driver.set(new ChromeDriver(options)); // due to ThreadLocal it is not a WebDriver it is a ThreadLocal This Stores the driver for the current thread


        } else{
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

    }


    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){

        if(driver.get() != null){
            driver.get().quit();
            driver.remove();// call this because ThreadLocal still holds a reference to the old driver. Even though chrome is closed, the reference still exists Calling it cleans the current thread completely.
        }

    }


}
