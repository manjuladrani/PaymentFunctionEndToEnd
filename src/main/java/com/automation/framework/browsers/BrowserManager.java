package com.automation.framework.browsers;

import com.automation.framework.configuration.ConfigurationLoader;
import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrowserManager {

    WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }

    public void initializeBrowser() throws Exception {
        Browser browser = null;
        if(BrowserType.EDGE.toString().equalsIgnoreCase(ConfigurationLoader.configOptions.getBrowserType())){
            browser = new Edge();
        } else if (BrowserType.CHROME.toString().equalsIgnoreCase(ConfigurationLoader.configOptions.getBrowserType())) {
            browser = new Chrome();
        } else if (BrowserType.SAFARI.toString().equalsIgnoreCase(ConfigurationLoader.configOptions.getBrowserType())) {
            browser = new Safari();
        }else {
            throw new UnsupportedOperationException("Invalid Browser  value provided in config file, Browser is not supported");
        }
        this.driver = browser.launchBrowser();
        this.driver.manage().window().maximize();//maximize browser
        this.driver.manage().timeouts().pageLoadTimeout(ConfigurationLoader.configOptions.getPageLoadWait(), TimeUnit.SECONDS);//page load wait, wait for 3os to load page completely
        this.driver.manage().timeouts().implicitlyWait(ConfigurationLoader.configOptions.getImplicitWait(), TimeUnit.SECONDS);//default element load wait, that before falling the script due to no element error,wait for 30

    }
    public void captureProof(String testName) throws IOException {
        BasicUtils baseUtils = new BasicUtils(getDriver());
        baseUtils.takeScreenShot(testName);
    }
}
