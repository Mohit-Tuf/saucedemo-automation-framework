package com.automation.base;
import com.automation.driver.DriverFactory;
import com.automation.listeners.RetryListener;
import com.automation.listeners.TestListener;
import com.automation.utils.LoggerUtils;
import com.automation.utils.PropertyUtils;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Listeners(TestListener.class)
public class BaseTest {

   private final static Logger logger = LoggerUtils.getLogger(BaseTest.class);

    @BeforeMethod
    public void setup(){

        logger.info("========== Test Execution Started ==========");

        DriverFactory.initializeDriver(); //-> new WebDriver()

        if(Boolean.parseBoolean(PropertyUtils.getProperty("maximize"))){
            logger.info("Maximizing browser window");
            DriverFactory.getDriver().manage().window().maximize(); // driver.manage().window().maximize();
        }

        String url = PropertyUtils.getProperty("url");
        logger.info("Navigating to: {}", url);
        DriverFactory.getDriver()
                .get(PropertyUtils.getProperty("url")); //driver.get("www.google.com");

    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        logger.info("Closing browser session");

        DriverFactory.quitDriver();//driver.quit();

        logger.info("========== Test Execution Finished ==========");
    }


}
