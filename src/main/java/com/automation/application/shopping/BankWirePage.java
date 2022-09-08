package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BankWirePage extends BasicUtils {
    WebDriver driver;
    public BankWirePage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public OrderConfirmationPage clickOnConfirmMyOrder() throws IOException {
        clickOn("shopping.BankWirePage.btnConfirmMyOrder");

        return new OrderConfirmationPage(this.driver);
    }
}