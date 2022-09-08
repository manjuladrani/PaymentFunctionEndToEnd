package com.automation.vechical.application;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class HomePage extends BasicUtils {
    WebDriver driver;

    public HomePage (WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    public HomePage navigateToWebsite(){
        launchApplication("https://www.gumtree.com/");
        return this;
    }
    public HomePage typeBMW() throws IOException {
        typeInto("xpath://input[@name='q']","BMW");
        return this;
    }
    public BMWPage clickOnSearch() throws IOException {
       clickOn("xpath://button[@class='button button--primary search-button']/child::span[@class='icon icon--magnifying-glass css-0 eom5h670']");
        return new BMWPage(this.driver);
    }

}
