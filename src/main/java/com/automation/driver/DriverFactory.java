package com.automation.driver;

import com.automation.utils.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import com.automation.utils.LoggerUtils;

//DriverFactory manages the browser lifecycle
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final Logger logger = LoggerUtils.getLogger(DriverFactory.class);

    //prevent instantiation
    private DriverFactory(){

    }

    public static void initializeDriver(){

        logger.info("Initializing WebDriver");
        String browser = PropertyUtils.getProperty("browser");
        boolean headless = Boolean.parseBoolean(PropertyUtils.getProperty("headless"));

        logger.info("Selected browser: {}", browser);
        logger.info("Headless mode: {}", headless);


        if("chrome".equalsIgnoreCase(browser)){

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();


            options.addArguments("--guest");

            Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-save-password-bubble");

            if(headless){
                options.addArguments("--headless=new");
            }

            logger.info("Launching Chrome browser");

            driver.set(new ChromeDriver(options)); // due to ThreadLocal it is not a WebDriver it is a ThreadLocal This Stores the driver for the current thread

            logger.info("Chrome browser launched successfully");

        } else{
            logger.error("Unsupported browser: {}", browser);
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

    }


    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){

        if(driver.get() != null){
            logger.info("Closing browser");
            driver.get().quit();
            driver.remove();// call this because ThreadLocal still holds a reference to the old driver. Even though chrome is closed, the reference still exists Calling it cleans the current thread completely.
            logger.info("WebDriver removed from ThreadLocal");
        }

    }


}
