package com.automation.utils;

import com.automation.constants.FrameworkConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extentReports;

    private ExtentReportManager() {

    }

    public static ExtentReports getExtentReports() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(
                            FrameworkConstants.REPORT_PATH
                                    + "ExtentReport.html"
                    );

            extentReports =
                    new ExtentReports();

            extentReports.attachReporter(sparkReporter);
        }

        return extentReports;
    }

}
