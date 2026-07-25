package com.automation.listeners;

import com.automation.utils.LoggerUtils;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    private static final int MAX_RETRY_COUNT = 2;

    private static final Logger logger = LoggerUtils.getLogger(RetryAnalyzer.class);

    @Override
    public boolean retry(ITestResult result) {

        if(retryCount < MAX_RETRY_COUNT){

            retryCount++;

            logger.warn(
                    "Retrying test: {} | Attempt: {} of {}",
                    result.getName(),
                    retryCount,
                    MAX_RETRY_COUNT
            );

            return true;
        }

        logger.error(
                "Test failed after {} retries: {}",
                MAX_RETRY_COUNT,
                result.getName()
        );
        return false;
    }
}
