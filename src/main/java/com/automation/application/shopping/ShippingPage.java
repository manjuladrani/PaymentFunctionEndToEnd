package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ShippingPage extends BasicUtils {
    WebDriver driver;
    public ShippingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public ShippingPage clickOnTerms() throws IOException {
        clickOn("shopping.ShippingPage.btnClickOnTerms");
        return this;
    }
    public Double checkDeliveryFee() throws IOException {
        WebElement shipping = waitAndUntilVisibleAndReturnElement("shopping.ShippingPage.checkDeliveryFee",60);
               // findByElement("xpath","//div[@class='delivery_option_price']");
        String totalShipping = shipping.getText().replace("$","");
        double shippingTotal = Double.parseDouble(totalShipping);
        return shippingTotal;
    }
    public PaymentPage clickOnProceedToCheckOutONShippingPage() throws IOException {
        clickOn("shopping.ShippingPagePage.btnProceedShippingPage");

        return new PaymentPage(this.driver);
    }
}
