package com.automation.framework.reusables;

import com.automation.framework.browsers.BrowserManager;
import com.automation.framework.configuration.ConfigurationLoader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BasicTests {


    public WebDriver driver;
    public BasicUtils basicUtils;
    public String currentWindowSession;
    public BrowserManager browserManager;
    //Step1:
    @BeforeSuite
    public void loadConfigurations() throws IOException {
        ConfigurationLoader configurationLoader = new ConfigurationLoader();
        configurationLoader.loadConfigurationsForFramework();
        browserManager = new BrowserManager();
    }

    @BeforeTest
    public void launchBrowser() throws Exception {
        browserManager.initializeBrowser();
        currentWindowSession = browserManager.getDriver().getWindowHandle();
//        this.driver = BasicUtils.launchBrowser(ConfigurationLoader.configOptions.getBrowserType());
//        basicUtils = new BasicUtils(driver);
//        //this.driver = browserManager.getDriver();
//        currentWindowSession =driver.getWindowHandle();
    }

    @AfterMethod
    public void proofsAfterEveryTest(Method method) throws IOException {
        browserManager.captureProof(method.getName());
        //BasicUtils.takeScreenShot(driver, method.getName());
    }

    @AfterTest
    public void closeBrowser(){
        browserManager.getDriver().quit();
        //driver.quit();
    }
}
