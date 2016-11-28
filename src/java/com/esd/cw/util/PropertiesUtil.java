package com.esd.cw.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesUtil {

    private static final ResourceBundle properties = ResourceBundle.getBundle("resources/config");

    public static String getPropertyAsString(String key) {
        return properties.getString(key);
    }
}
