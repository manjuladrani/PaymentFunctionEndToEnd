package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class OrderConfirmationPage extends BasicUtils {
    WebDriver driver;
    public OrderConfirmationPage(WebDriver driver){

        super(driver);
        this.driver=driver;
    }
    public OrderConfirmationPage checkOrderCompleteMessage() throws IOException {
        checkingData("shopping.OrderConfirmationPage.checkOrderCompleteMsg","Your order on My Store is complete.");
        return this;
    }
    public String fetchAccountHolderAmount() throws IOException {
        WebElement amount = waitAndUntilVisibleAndReturnElement("shopping.OrderConfirmationPage.fetchAccountHolderAmount",60);
               // findByElement("xpath","//span[@class='price']");
        String totalAmount = amount.getText();
        System.out.println("Total amount = "+totalAmount);
        return totalAmount;
    }
    public String fetchAccountHolderName() throws IOException {
        WebElement name = waitAndUntilVisibleAndReturnElement("shopping.OrderConfirmationPage.fetchAccountHolderName",60);
               // findByElement("xpath", "//div[@class='box']/strong[1]");
        String nameOfAccount = name.getText();
        return nameOfAccount;
    }
    public String fetchAccountHolderBank() throws IOException {
        WebElement bank = waitAndUntilVisibleAndReturnElement("shopping.OrderConfirmationPage.fetchAccountHolderBank",60);
                //findByElement("xpath", "//div[@class='box']/strong[3]");
        String nameOfBank = bank.getText();
        return nameOfBank;
    }

}
