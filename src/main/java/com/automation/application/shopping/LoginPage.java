package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginPage extends BasicUtils {
    WebDriver driver;
    LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public LoginPage enterUserName(String username) throws IOException {
        typeInto("shopping.LoginPage.tbxUserName",username);
        return this;
    }
    public LoginPage enterPassword(String password) throws IOException {
        typeInto("shopping.LoginPage.tbxPassWord",password);
        return this;
    }
    public MyAccountPage clickSubmit() throws IOException {
        clickOn("shopping.LoginPage.btnSubmit");
        return new MyAccountPage(this.driver);
    }
}
