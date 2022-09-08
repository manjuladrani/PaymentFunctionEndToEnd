package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AddressPage extends BasicUtils {
    WebDriver driver;
    public AddressPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public ShippingPage clickOnProceedToCheckOutOnAddressPage() throws IOException {
        clickOn("shopping.AddressPage.btnProceedAddressPage");

        return new ShippingPage(this.driver);
    }
}
