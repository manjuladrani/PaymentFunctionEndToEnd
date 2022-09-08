package com.automation.framework.browsers;

import com.automation.framework.configuration.ConfigurationLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Safari extends Browser{
    SafariOptions safariOptions;
    @Override
    public void setCapabilities() {
        safariOptions= new SafariOptions();
        setHeadless(ConfigurationLoader.configOptions.isHeadless());
        setRunOn(ConfigurationLoader.configOptions.getRunOn());
        Map<String,String> preferences = new HashMap<>();
        preferences.put("excludeSwitches","disable-popup-blocking");
        preferences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");//  chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));//  chromeOptions.setExperimentalOption("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");
        safariOptions.setCapability("prefs",preferences);
    }

    @Override
    public WebDriver launchBrowser() throws MalformedURLException {

        setCapabilities();WebDriver driver = null;
        if(getRunOn().equalsIgnoreCase("local")){
            WebDriverManager.chromedriver().setup();
            driver = new SafariDriver();
        }else {
            driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),safariOptions);
        }
        return driver;
    }
}
