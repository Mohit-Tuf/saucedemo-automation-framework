package com.automation.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public final class LoggerUtils {

    private LoggerUtils(){

    }

    public static Logger getLogger(Class<?> clazz){
        return LogManager.getLogger(clazz);
    }

}
