package com.automation.utils;

import com.automation.constants.FrameworkConstants;
import com.automation.driver.DriverFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtils {

    private static final Logger logger = LoggerUtils.getLogger(ScreenshotUtils.class);

    private ScreenshotUtils(){

    }

    public static String captureScreenshot(String screenshotName){
        logger.info("Capturing screenshot: {}", screenshotName);

        TakesScreenshot takesScreenshot = (TakesScreenshot) DriverFactory.getDriver();

        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

//        String destinationPath = FrameworkConstants.SCREENSHOT_PATH + screenshotName + ".png";
//
//        File destinationFile = new File(destinationPath);
//
//        logger.info("Screenshot saved at: {}", destinationPath);
//
//        return destinationPath;

        Path screenshotDirectory = Paths.get(FrameworkConstants.SCREENSHOT_PATH);

        try{
            Files.createDirectories(screenshotDirectory);
            Path destinationPath = screenshotDirectory.resolve(screenshotName + ".png");
            Files.copy(sourceFile.toPath(), destinationPath);

            logger.info("Screenshot saved at: {}", destinationPath);
            return destinationPath.toString();



        } catch (IOException e) {
            logger.error("Failed to save screenshot: {}", screenshotName, e);
            return null;
        }

    }



}
