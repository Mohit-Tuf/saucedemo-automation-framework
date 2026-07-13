package com.automation.utils;

import com.automation.constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read values from config.properties.
 */
public class PropertyUtils {

    private static final Properties properties = new Properties();

    static{
        try(FileInputStream file = new FileInputStream(
                FrameworkConstants.CONFIG_FILE_PATH)){

            properties.load(file);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Prevent object creation
    private PropertyUtils(){

    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}
