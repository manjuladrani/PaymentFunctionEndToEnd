package com.automation.framework.browsers;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public abstract class Browser {

    private boolean headless;

    public boolean isHeadless() {
        return headless;
    }

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public String getRunOn() {
        return runOn;
    }

    public void setRunOn(String runOn) {
        this.runOn = runOn;
    }

    private String runOn;


   public abstract void setCapabilities();
    public abstract WebDriver launchBrowser() throws Exception;

}

