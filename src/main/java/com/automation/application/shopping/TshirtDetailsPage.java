package com.automation.application.shopping;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class TshirtDetailsPage extends BasicUtils {
    WebDriver driver;
    public TshirtDetailsPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public TshirtDetailsPage clickOnTshirt() throws IOException {
        clickOn("shopping.TshirtDetailsPage.btnTshirtDetails");
         //xpath://a[@class='login']xpath://img[@title='Faded Short Sleeve T-shirts']
        return new TshirtDetailsPage(this.driver);
    }
    public TshirtDetailsPage clickOnAddToCart() throws IOException {
        clickOn("shopping.TshirtDetailsPage.btnAddCart");

        return this;
    }
    public TshirtDetailsPage checkProductAddedMessage() throws IOException {
        checkingData("shopping.TshirtDetailsPage.checkProductAddedMsg","Product successfully added to your shopping cart");
        return this;
    }
    public OrderPage clickOnProceedToCheckOut() throws IOException {
        clickOn("shopping.TshirtDetailsPage.btnProceed");

        return new OrderPage(this.driver);
    }
}
