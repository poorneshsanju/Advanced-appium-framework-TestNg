package com.automation.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CapabilitiesManager {
    private Properties properties;

    public CapabilitiesManager() {
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        try{
            FileInputStream fis = new FileInputStream("src/main/resources/config/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DesiredCapabilities getCapabilities(String platform) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Common capabilities
        capabilities.setCapability("automationName", properties.getProperty("automationName"));
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("deviceName", properties.getProperty(platform + ".deviceName"));
//        capabilities.setCapability("newCommandTimeout", properties.getProperty("newCommandTimeout"));

        if (platform.equalsIgnoreCase("Android")) {
            setAndroidCapabilities(capabilities);
        } else if (platform.equalsIgnoreCase("iOS")) {
            setIOSCapabilities(capabilities);
        }
        return capabilities;
    }

    private void setAndroidCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability("appPackage", properties.getProperty("android.appPackage"));
        capabilities.setCapability("appActivity", properties.getProperty("android.appActivity"));
    }

    private void setIOSCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability("bundleId", properties.getProperty("ios.bundleId"));
        capabilities.setCapability("xcodeOrgId", properties.getProperty("ios.xcodeOrgId"));
        capabilities.setCapability("xcodeSigningId", "iphone Developer");
    }
}
