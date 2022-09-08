package com.automation.framework.reusables;

import com.automation.framework.configuration.ConfigurationLoader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class BasicUtils {
    WebDriver driver;
    static int waitTime = ConfigurationLoader.configOptions.getExplicitWait();

    public BasicUtils(WebDriver driver) {
        this.driver = driver;
    }

//    public static WebDriver launchBrowser(String browser) throws MalformedURLException {
//        WebDriver driver = null;
//        if (browser.equalsIgnoreCase("Safari")) {
//            SafariOptions safarioptions = new SafariOptions();
//            Map<String,String> preferences = new HashMap<>();
//            preferences.put("excludeSwitches","disable-popup-blockin");
//            preferences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");
//            if (ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("local")) {
//                driver = new SafariDriver(safarioptions);
//            }else if (ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("remote")) {
//
//                driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),safarioptions);
//            }
//
//
//        } else if (browser.equalsIgnoreCase("Edge")) {
//            EdgeOptions edgeOptions = new EdgeOptions();
//            Map<String,String> preferences = new HashMap<>();
//            preferences.put("excludeSwitches","disable-popup-blockin");
//            preferences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");
//            edgeOptions.setCapability("prefs",preferences);
//            if (ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("local")) {
//                System.setProperty("webdriver.edge.driver",ConfigurationLoader.configOptions.getEdgeDriverPath());
//               // WebDriverManager.edgedriver().setup();
//                driver = new EdgeDriver();
//            }else if (ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("remote")) {
//                driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),edgeOptions);
//            }
//        } else if (browser.equalsIgnoreCase("chrome")) {
//            ChromeOptions chromeOptions = new ChromeOptions();
//            Map<String,String> preferences = new HashMap<>();
//            preferences.put("excludeSwitches","disable-popup-blocking");
//            preferences.put("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");
//          //  chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
//          //  chromeOptions.setExperimentalOption("download.default_directory",System.getProperty("user.dir")+"/src/test/resources/downloads");
//            chromeOptions.setExperimentalOption("prefs",preferences);
//            if (ConfigurationLoader.configOptions.isHeadless()){
//                chromeOptions.setHeadless(true);
//            }
//            if (ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("local")) {
//                //System.setProperty("webdriver.chrome.driver", ConfigurationLoader.configOptions.getChromeDriverPath());
//                chromeOptions.addArguments("start-maximized");
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//            } else if (ConfigurationLoader.configOptions.getRunOn().equalsIgnoreCase("remote")) {
//
//                driver = new RemoteWebDriver(new URL(ConfigurationLoader.configOptions.getSeleniumHubUrl()),chromeOptions);
//            }
//        }
//        //driver.manage().window().maximize();//maximize browser
//        driver.manage().timeouts().pageLoadTimeout(waitTime, TimeUnit.SECONDS);//page load wait, wait for 3os to load page completely
//        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);//default element load wait, that before falling the script due to no element error,wait for 30
//
//        return driver;
//    }

    public void launchApplication(String url) {
        driver.get(url);
    }

    public void takeScreenShot(String testName) throws IOException {
        if(ConfigurationLoader.configOptions.isTakeScreenShot()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/src/test/resources/screenshots/screenshot-" + testName + ".png"));
        }
    }
    public String getObjectRepositoryLocator(String locatorName) throws IOException {
        Properties properties= new Properties();
        properties.load(new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/ObjectRepository/OR.properties")));
        return properties.getProperty(locatorName);
    }

    public WebElement findByElement(String locatorBy, String locatorValue) {
        WebElement element = null;
        switch (locatorBy) {
            case "id":
                element = driver.findElement(By.id(locatorValue));
            case "name":
                element = driver.findElement(By.name(locatorValue));
                break;
            case "tag":
                element = driver.findElement(By.tagName(locatorValue));
                break;
            case "link":
                element = driver.findElement(By.linkText(locatorValue));
                break;
            case "plt":
                element = driver.findElement(By.partialLinkText(locatorValue));
                break;
            case "css":
                element = driver.findElement(By.cssSelector(locatorValue));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(locatorValue));
                break;
        }
        scrollToElement(element);
        return element;
    }

    public By findBy(String locateBy, String locatorValue) {
        By by = null;
        switch (locateBy) {
            case "xpath":
                by = By.xpath(locatorValue);
                break;
            case "is":
                by = By.id(locatorValue);
                break;
            case "name":
                by = By.name(locatorValue);
                break;
            case "css":
                by = By.cssSelector(locatorValue);
                break;
            case "class":
                by = By.className(locatorValue);
                break;
            case "tag":
                by = By.tagName(locatorValue);
                break;
            case "plt":
                by = By.partialLinkText(locatorValue);
                break;
            case "link":
                by = By.linkText(locatorValue);
                break;
        }
        return by;
    }

    public void clickOn(String locatorName) throws IOException {
        waitAndUntilElementClickableAndClick(locatorName,waitTime);
    }

    public void selectBy(String howToSelect, String listValue, String locatorName) throws IOException {
        //String elementHow = getObjectRepositoryLocator(locatorName);
        WebElement elementToSelect = waitAndUntilVisibleAndReturnElement(locatorName,waitTime);
        Select selectObj = new Select(elementToSelect);
        if (howToSelect.equalsIgnoreCase("ByValue")) {
            selectObj.selectByValue(listValue);
        } else if (howToSelect.equalsIgnoreCase("ByVisibleTest")) {
            selectObj.selectByVisibleText(listValue);
        } else if (howToSelect.equalsIgnoreCase("ByIndex")) {
            selectObj.selectByIndex(Integer.parseInt(listValue));
        }
    }

    public void switchToMainPage() {
        driver.switchTo().defaultContent();
    }

    public void switchSecondWindow(WebDriver driver, boolean switchBackToMain) {
        String mainWindow = driver.getWindowHandle();
        Set<String> setOfWindows = driver.getWindowHandles();
        //List<String> listOfWindows = new ArrayList<String>(setOfWindows);
        if (!switchBackToMain) {
            for (String handles : setOfWindows) {
                System.out.println(handles);
                if (!handles.equals(mainWindow)) {
                    driver.switchTo().window(handles);
                }
            }
        } else {
            driver.switchTo().window(mainWindow);
        }

    }

    public void switchBackToMainCloseAll(String currentWindow) {
        driver.switchTo().window(currentWindow);
        Set<String> allWindowsOpen = driver.getWindowHandles();
        List<String> windows = new ArrayList<String>(allWindowsOpen);
        for (int i = 0; i < windows.size(); i++) {
            if (!windows.get(i).equals(currentWindow)) {
                driver.switchTo().window(windows.get(i)).close();
            }
        }
        driver.switchTo().window(currentWindow);
    }


    public void typeInto(String locatorName, String elementValue) throws IOException {

        waitUntilElementVisibleAndTypeInto(locatorName,waitTime,elementValue);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void checkingData(String elementPath, String compareValue) throws IOException {
        WebElement elementToClick = waitAndUntilVisibleAndReturnElement(elementPath,waitTime);
        Assert.assertTrue(elementToClick.isDisplayed(), compareValue);
        System.out.println(" Both are same values, that is " + compareValue);

    }

    public void waitForUntilElementVisible(WebElement element, int noOfSeconds) {
        Wait wait = new WebDriverWait(driver, noOfSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitAndUntilVisibleAndReturnElement(String locatorName, int noOfSeconds) throws IOException {
        String elementHow = getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":", 2)[0];
        String howValue = elementHow.split(":", 2)[1];
        By by = findBy(how, howValue);
        WebDriverWait wait = new WebDriverWait(driver, noOfSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }

    public WebElement waitAndUntilElementClickableAndClick(String locatorName, int noOfSeconds) throws IOException {
        String elementHow = getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":", 2)[0];
        String howValue = elementHow.split(":", 2)[1];
        By by = findBy(how, howValue);
        WebDriverWait wait = new WebDriverWait(driver, noOfSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElement(element);
        element.click();
        return element;
    }

    public WebElement waitUntilElementVisibleAndTypeInto(String locatorName, int noOfSeconds, String textToType) throws IOException {
        String elementHow = getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":", 2)[0];
        String howValue = elementHow.split(":", 2)[1];
        By by = findBy(how, howValue);
        WebDriverWait wait = new WebDriverWait(driver, noOfSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElement(element);
        element.click();
        element.clear();
        element.sendKeys(textToType);
        return element;
    }

    public String waitUntilElementVisibleAndGetText(String locatorName, int noOfSeconds, String textToType) throws IOException {
        String elementHow = getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":", 2)[0];
        String howValue = elementHow.split(":", 2)[1];
        By by = findBy(how, howValue);
        WebDriverWait wait = new WebDriverWait(driver, noOfSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElement(element);
        return element.getText();
    }
    public boolean waitUntilElementDisappears(String locatorName, int noOfSeconds, String textToType) throws IOException {
        String elementHow = getObjectRepositoryLocator(locatorName);
        String how = elementHow.split(":", 2)[0];
        String howValue = elementHow.split(":", 2)[1];
        By by = findBy(how, howValue);
        WebDriverWait wait = new WebDriverWait(driver, noOfSeconds);
        boolean status = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        return status;
    }

}