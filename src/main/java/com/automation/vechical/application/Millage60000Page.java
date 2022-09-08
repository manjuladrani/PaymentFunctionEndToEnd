package com.automation.vechical.application;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Millage60000Page extends BasicUtils {
    WebDriver driver;
    public Millage60000Page(WebDriver driver) {
        super(driver);
        this.driver =driver;
    }

    public BMWPage clickOnAll() throws IOException {
        clickOn("xpath://aside[@id='refinement-panel']/form/div/section[4]/ul/li[1]/a");
        return new BMWPage(driver);
    }

}
