package com.automation.listeners;
import com.automation.utils.ExtentReportManager;
import com.automation.utils.LoggerUtils;
import com.automation.utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements ITestListener{

    private static Logger logger = LoggerUtils.getLogger(TestListener.class);

    private ExtentReports extentReports = ExtentReportManager.getExtentReports();
//    private ExtentTest extentTest; //not thread safe then we use ThreadLocal<ExtentTest>
    private static final ThreadLocal<ExtentTest> extentTest =
        new ThreadLocal<>();

    @Override
    public void onTestFailure(ITestResult result){
        String testName = result.getName();

//        logger.error("Test failed: {}", testName);
//
//        String screenshotPath = ScreenshotUtils.captureScreenshot(testName);
//
//        logger.info("Failure screenshot captured at: {}", screenshotPath);

        String className = result.getTestClass()
                                .getRealClass()
                                .getSimpleName();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String screenshotName = className + "_" + testName + "_" + timestamp;

        logger.error("Test failed: {}", screenshotName);

        String screenshotPath = ScreenshotUtils.captureScreenshot(screenshotName);

//        extentTest.fail(result.getThrowable());//extentTest-only
        extentTest.get().fail(result.getThrowable());

        try{
//            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.get().addScreenCaptureFromPath(screenshotPath);
        }
        catch (Exception e){
            logger.error("Failed to attach screenshot to report", e);
        }

        logger.info(
                "Failure screenshot captured at: {}",
                screenshotPath
        );

        extentTest.remove();

    }

    @Override
    public void onTestStart(ITestResult result) {

//        extentReports =
//                ExtentReportManager.getExtentReports();

//        extentTest =
//                extentReports.createTest(
//                        result.getMethod().getMethodName()
//                );
        extentTest.set(
                extentReports.createTest(
                        result.getMethod().getMethodName()
                )
        );

        logger.info(
                "Extent test created: {}",
                result.getName()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result){

//        extentTest.pass("Test passes successfully");
        extentTest.get().pass("Test passes successfully");

        logger.info("Test passed: {}", result.getName());

        extentTest.remove();

    }

    @Override
    public void onTestSkipped(ITestResult result){
//        extentTest.skip("Test was skipped");
        extentTest.get().skip("Test was skipped");

        logger.warn("Test skipped: {}", result.getName());
        extentTest.remove();
    }

    @Override
    public void onFinish(ITestContext context){
        extentReports.flush();

        logger.info("Extent Report generated successfully");
    }

}
