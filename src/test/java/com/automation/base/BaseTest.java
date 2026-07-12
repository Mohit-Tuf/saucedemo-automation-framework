package com.automation.base;
import com.automation.driver.DriverFactory;
import com.automation.utils.PropertyUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class BaseTest {

    @BeforeMethod
    public void setup(){

        DriverFactory.intializeDriver(); //-> new WebDriver()

        if(Boolean.parseBoolean(PropertyUtils.getProperty("maximize"))){
            DriverFactory.getDriver().manage().window().maximize(); // driver.manage().window().maximize();
        }

        DriverFactory.getDriver()
                .get(PropertyUtils.getProperty("url")); //driver.get("www.google.com");

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        DriverFactory.quitDriver();//driver.quit();
    }


}
