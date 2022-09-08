package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class OrderPage extends BasicUtils {
    WebDriver driver;
    public OrderPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public double fetchingTotalPrice() throws IOException {
        WebElement total = waitAndUntilVisibleAndReturnElement("shopping.OrderPage.fetchingTotalPrice", 60);
        String totalValue = total.getText().replace("$", "");
        System.out.println("Total value of the Order = " + totalValue);
        double value = Double.parseDouble(totalValue);
        return value;
    }

    public AddressPage clickOnProceedToCheckOutOnSignPage() throws IOException {
        clickOn("shopping.OrderPage.btnProceedSignInPage");
        return new AddressPage(this.driver);
    }
}
