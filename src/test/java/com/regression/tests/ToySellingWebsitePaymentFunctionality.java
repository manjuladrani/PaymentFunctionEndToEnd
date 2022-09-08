package com.regression.tests;

import com.automation.framework.reusables.BasicTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class ToySellingWebsitePaymentFunctionality extends BasicTests {
    String cardNo, cvv, expDate, year, month;
    @Test
    public void PaymentGateWayFunctionality() throws InterruptedException, IOException {
        //Step 1: Launch URL "http://demo.guru99.com/payment-gateway/index.php”
        basicUtils.launchApplication("https://demo.guru99.com/payment-gateway/index.php");

        //Step 2: Click on Generate Card Number
        basicUtils.clickOn("xpath://a[contains(text(),'Generate Card Number')]");
        Thread.sleep(2000);

        //Step 3: Fetch CardNumber, CVV, Expiry date, Credit Limit
        basicUtils.switchSecondWindow(driver, false);
        WebElement cNo = basicUtils.findByElement("xpath","//h4[contains(text(),'Card Number')]");
        cardNo = cNo.getText().split(":-",2)[1];
        System.out.println("CreditCard Number"+cardNo);
        WebElement cvvNo = basicUtils.findByElement("xpath","//h4[contains(text(),'CVV')]");
        cvv = cvvNo.getText().split(":-",2)[1];
        System.out.println("CVV Number"+cvv);
        basicUtils.switchToMainPage();
        Thread.sleep(2000);
        WebElement expiry = basicUtils.findByElement("xpath", "//h4[contains(text(),'Exp')]");
        expDate = expiry.getText().split(":-",2)[1];
        System.out.println("Expiry Date"+expDate);
        String currentWindow = driver.getWindowHandle();
        basicUtils.switchBackToMainCloseAll(currentWindow);


        //Step 4: Click on "Cart"
        basicUtils.clickOn("xpath://a[contains(text(),'Cart')]");
        Thread.sleep(2000);

        //Step 6: Select 4 as quantity
        basicUtils.selectBy("ByIndex","3", "xpath://select");
        Thread.sleep(2000);

        //Step 7: Fetch the price of toy : "20$" in this case.
        WebElement price = driver.findElement(By.xpath("//h3[contains(text(),'Price: $20')]"));
        Assert.assertTrue(price.isDisplayed(), "Price: $20");
        Thread.sleep(2000);

        //Step 8: Click on Buy Now
        basicUtils.clickOn("xpath://input");
        Thread.sleep(2000);

        //Step 9: Enter Card details fetched in Step 3.
        basicUtils.typeInto("xpath://input[@id='card_nmuber']", String.valueOf(cardNo));
        Thread.sleep(2000);
        basicUtils.typeInto("xpath://select[@id='month']", expDate.split("/")[0]);
        Thread.sleep(2000);
        basicUtils.typeInto("xpath://select[@id='year']",expDate.split("/")[1]);
        Thread.sleep(2000);
        basicUtils.typeInto("xpath://input[@id='cvv_code']",String.valueOf(cvv));
        Thread.sleep(2000);

        //Step 10: Verify that the right amount is reflected on the "Pay" button. Example for 4 Qty : 20 * 4 = 80 $ should display
        System.out.println("Verifing Total amount to pay is 4*20 = 80 ");
        basicUtils.checkingData("xpath://input[@class='button special']", "Pay $80.00" );
        Thread.sleep(2000);

        //Step 11: Click on Pay Now
        basicUtils.clickOn("xpath://input[@class='button special']");
        Thread.sleep(2000);

        //Step 12: Verify Payment Successful message
        System.out.println("Verifing Payment Successful message is displayed or not ");
        basicUtils.checkingData("xpath://h2[contains(text(),'Payment successfull!')]", "Payment successfull!");
        Thread.sleep(2000);

        //Step 13: Fetch the Order ID
        WebElement order = basicUtils.findByElement("xpath","//h3/following::h3");
        String orderId = order.getText();
        System.out.println("Order ID : "+ order.getText());
        Thread.sleep(2000);

        //Step 14: Click on "Check Credit Card Limit"
        basicUtils.clickOn("xpath://a[contains(text(),'Check Credit Card Limit')]");
        Thread.sleep(5000);


        //Step 15: Enter Card number that you have fetched in Step 3 to check the credit limit
        basicUtils.typeInto("xpath://input[@id='card_nmuber']", String.valueOf(cardNo));
        Thread.sleep(2000);
        basicUtils.clickOn("xpath://input[@class='button special']");
        Thread.sleep(2000);

        //Step 16: Verify Credit card balance, it should be 100$ minus amount you purchased.
        System.out.println("Checking the remaing CreditCard balance is 100-80 = 20");
        basicUtils.checkingData("xpath://span/following::h4", "20");
        Thread.sleep(2000);

        //Step 17: Verify that the order ID is reflected in transaction table.
        System.out.println("Verifying the order Id is same or not");
        basicUtils.checkingData("xpath:/html/body/section/div/div/table/tbody/tr/td[6]/b/font", orderId);
        Thread.sleep(2000);
    }

}
