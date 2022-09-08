package com.automation.framework.configuration;

public class ConfigOptions {

    private String browserType;
    private int implicitWait;
    private int pageLoadWait;
    private int  explicitWait;
    private boolean takeScreenShot;
    private String chromeDriverPath;
    private String edgeDriverPath;
    private String safariDriverPath;

    public String getBrowserType() {
        return browserType;
    }
    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public void setChromeDriverPath(String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
    }

    public String getEdgeDriverPath() {
        return edgeDriverPath;
    }

    public void setEdgeDriverPath(String edgeDriverPath) {
        this.edgeDriverPath = edgeDriverPath;
    }

    public String getSafariDriverPath() {
        return safariDriverPath;
    }

    public void setSafariDriverPath(String safariDriverPath) {
        this.safariDriverPath = safariDriverPath;
    }


    private String runOn;
    private boolean headless;
    private String seleniumHubUrl;


    public String getBrowserType(String rowserType) {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public int getImplicitWait() {
        return implicitWait;
    }

    public void setImplicitWait(int implicitWait) {
        this.implicitWait = implicitWait;
    }

    public int getPageLoadWait() {
        return pageLoadWait;
    }

    public void setPageLoadWait(int pageLoadWait) {
        this.pageLoadWait = pageLoadWait;
    }

    public int getExplicitWait() {
        return explicitWait;
    }

    public void setExplicitWait(int explicitWait) {
        this.explicitWait = explicitWait;
    }

    public boolean isTakeScreenShot() {
        return takeScreenShot;
    }

    public void setTakeScreenShot(boolean takeScreenShot) {
        this.takeScreenShot = takeScreenShot;
    }



    public String getRunOn() {
        return runOn;
    }

    public void setRunOn(String runOn) {
        this.runOn = runOn;
    }

    public boolean isHeadless() {
        return headless;
    }

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public String getSeleniumHubUrl() {
        return seleniumHubUrl;
    }

    public void setSeleniumHubUrl(String seleniumHubUrl) {
        this.seleniumHubUrl = seleniumHubUrl;
    }


}
