package org.example.properties;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {


    public static void setAPIProperty(String property, String value) {
        Properties prop = new Properties();
        try {
            String configFilePath = "src/test/resources/api.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            prop.load(propsInput);
            propsInput.close();
            FileOutputStream propsOutput = new FileOutputStream(configFilePath);
            prop.setProperty(property, value);
            prop.store(propsOutput,null);
            propsOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String readAPIProperty(String property) {
        Properties prop = new Properties();
        try {
            String configFilePath = "src/test/resources/api.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            prop.load(propsInput);
            propsInput.close();
            return prop.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }




}
