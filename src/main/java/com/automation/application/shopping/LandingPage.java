package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LandingPage extends BasicUtils {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public LandingPage navigateToWebsite(){
        launchApplication(" http://automationpractice.com/index.php");
        return this;
    }
    public LoginPage clickSigIn() throws IOException {
        clickOn("shopping.LandingPage.btnSignIn");
        return new LoginPage(this.driver);
    }
}
