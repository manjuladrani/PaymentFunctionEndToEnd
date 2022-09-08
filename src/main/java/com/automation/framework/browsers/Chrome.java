package com.automation.framework.browsers;

import com.automation.framework.configuration.ConfigurationLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Chrome extends Browser{
    ChromeOptions chromeOptions;
    @Override
    public void setCapabilities() {
        chromeOptions= new ChromeOptions();
        setHeadless(ConfigurationLoader.configOptions.isHeadless());
        setRunOn(ConfigurationLoader.configOptions.getRunOn());
        if (isHeadless()){
            chromeOptions.setHeadless(true);
        }
        Map<String,String> preferences = new HashMap<>();
        preferences.put("excludeSwitches","disable-popup-blocking");
        preferences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");//  chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));//  chromeOptions.setExperimentalOption("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");
        chromeOptions.setExperimentalOption("prefs",preferences);
    }

    @Override
    public WebDriver launchBrowser() throws MalformedURLException {
        setCapabilities();
        WebDriver driver = null;
        if(getRunOn().equalsIgnoreCase("local")){
            //WebDriverManager.chromedriver().setup();
            System.setProperty("webdriver.chrome.driver", ConfigurationLoader.configOptions.getChromeDriverPath());
            driver = new ChromeDriver(chromeOptions);
        }else {
            driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),chromeOptions);
        }
        return driver;
    }
}
