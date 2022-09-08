package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PaymentPage extends BasicUtils {
    WebDriver driver;
    public PaymentPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public BankWirePage clickOnBankWirePayment() throws IOException {
        clickOn("shopping.PaymentPage.btnBankWire");

        return new BankWirePage(this.driver);
    }
}
