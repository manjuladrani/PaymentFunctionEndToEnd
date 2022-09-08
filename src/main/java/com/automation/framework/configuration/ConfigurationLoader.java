package com.automation.framework.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationLoader {

    public static ConfigOptions configOptions;
    public Properties properties;
    public void  readConfigFileProperties() throws IOException {
        properties = new Properties();
        File configFile = new File(System.getProperty("user.dir")+"/src/main/resources/configs/appConfig.properties");
        properties.load(new FileInputStream(configFile));

    }

    public String getProperty(String name){
        return properties.getProperty(name);
    }

    public void loadConfigurationsForFramework() throws IOException {

        configOptions = new ConfigOptions();
        readConfigFileProperties();
        configOptions.setBrowserType(getProperty("browserType"));
        configOptions.setImplicitWait(Integer.parseInt(getProperty("implicitWait")));
        configOptions.setExplicitWait(Integer.parseInt(getProperty("explicitWait")));
        configOptions.setPageLoadWait(Integer.parseInt(getProperty("pageLoadWait")));
        configOptions.setTakeScreenShot(Boolean.parseBoolean(getProperty("takeScreenShot")));
        configOptions.setChromeDriverPath(getProperty("chromeDriverPath"));
        configOptions.setEdgeDriverPath(getProperty("edgeDriverPath"));
        configOptions.setSafariDriverPath(getProperty("safariDriverPath"));
        configOptions.setRunOn(getProperty("runOn"));
        configOptions.setHeadless(Boolean.parseBoolean(getProperty("headless")));
        configOptions.setSeleniumHubUrl(getProperty("seleniumHubUrl"));

    }

    public static void main(String[] args) throws IOException {
        ConfigurationLoader reader = new ConfigurationLoader();
        reader.loadConfigurationsForFramework();
        System.out.println(configOptions.getBrowserType());
        System.out.println(configOptions.getRunOn());
    }
}
