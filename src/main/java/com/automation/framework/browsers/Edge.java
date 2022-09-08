package com.automation.framework.browsers;

import com.automation.framework.configuration.ConfigurationLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Edge extends Browser{


    EdgeOptions edgeOptions;
    @Override
    public void setCapabilities() {
        edgeOptions= new EdgeOptions();
        setHeadless(ConfigurationLoader.configOptions.isHeadless());
        setRunOn(ConfigurationLoader.configOptions.getRunOn());
        Map<String,String> preferences = new HashMap<>();
        preferences.put("excludeSwitches","disable-popup-blocking");
        preferences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");//  chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));//  chromeOptions.setExperimentalOption("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");
        edgeOptions.setCapability("prefs",preferences);
    }

    @Override
    public WebDriver launchBrowser() throws MalformedURLException {

        setCapabilities();WebDriver driver = null;
        if(getRunOn().equalsIgnoreCase("local")){
            //WebDriverManager.chromedriver().setup();

            System.setProperty("webdriver.edge.driver",ConfigurationLoader.configOptions.getEdgeDriverPath());
            driver = new EdgeDriver(edgeOptions );
        }else {
            driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),edgeOptions);
        }
        return driver;
    }
}
