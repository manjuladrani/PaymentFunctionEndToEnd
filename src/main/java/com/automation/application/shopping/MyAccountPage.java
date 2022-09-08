package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class MyAccountPage extends BasicUtils {
    WebDriver driver;
    public MyAccountPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public TshirtDetailsPage tshirt() throws IOException {
        clickOn("shopping.MyAccountPage.btnTshirt");
        return new TshirtDetailsPage(this.driver);
    }
}
