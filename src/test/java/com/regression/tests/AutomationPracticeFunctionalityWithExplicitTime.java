package com.regression.tests;

import com.automation.framework.reusables.BaseAsserts;
import com.automation.framework.reusables.BasicTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;

public class AutomationPracticeFunctionalityWithExplicitTime extends BasicTests {

    @Test
    public void EndToEndBuyOrderFunctionality() throws InterruptedException, IOException {

        //1. Open link http://automationpractice.com/index.php
        basicUtils.launchApplication(" http://automationpractice.com/index.php");
        //2. Login to the website using email : "hey@abc.com" and password: "Testing@1234"
        basicUtils.clickOn( "xpath://a[@class='login']");
        basicUtils.typeInto("xpath://input[@id='email']","hey@abc.com");
        basicUtils.typeInto("xpath://input[@id='passwd']","Testing@1234");
        basicUtils.clickOn("xpath://button[@id='SubmitLogin']");
        //3. Click on sub menu 'T-shirts'.
        basicUtils.clickOn("xpath://div[@id=\"block_top_menu\"]/ul/li[3]/a");
        //4. Click on Result Image : "Faded Short Sleeve T-shirts"
        basicUtils.clickOn("xpath://img[@title='Faded Short Sleeve T-shirts']");
        //5. Click Add to cart
        driver.switchTo().frame(0);
        basicUtils.clickOn("xpath://span[contains(text(),'Add to cart')]");
        //6. Verify that the product is added to cart by verifying the message "Product successfully added to your shopping cart"
        basicUtils.checkingData("xpath://h2/parent::div[@class='layer_cart_product col-xs-12 col-md-6']","Product successfully added to your shopping cart");
        //7. Click on Proceed to checkout
        basicUtils.clickOn("xpath://span[contains(text(),'Proceed to checkout')]");
        //8. On the shopping cart summary screen, fetch the total price.
        WebElement total = basicUtils.waitAndUntilVisibleAndReturnElement("xpath://span[@id='total_price']",60);
               // findByElement("xpath","//span[@id='total_price']");
        String totalValue = total.getText().replace("$","");
        System.out.println("Total value of the Order = "+totalValue);
        //9. Verify the price doesn't exceed total of 20 dollars.
        double value = Double.parseDouble(totalValue);
        //Assert.assertTrue(value< 20,"Total order value is less than $20");
        BaseAsserts.ShouldBeTrue(value<20,"Total order value is less than $20" );
        //10. click Proceed to Checkout
        basicUtils.clickOn("xpath://span[contains(text(),'Proceed to checkout')]/parent::a[@class='button btn btn-default standard-checkout button-medium']");
        basicUtils.clickOn("xpath://span[contains(text(),'Proceed to checkout')]/ancestor::button[@class='button btn btn-default button-medium']");
        //11. Select terms of service (You can do it by simply clicking on the text - terms of service)
        basicUtils.clickOn("xpath://div[@id='uniform-cgv']");
        //12. Fetch the delivery fees, and verify that delivery fees is not more than 5 dollars
        WebElement shipping = basicUtils.findByElement("xpath","//div[@class='delivery_option_price']");
        String totalShipping = shipping.getText().replace("$","");
        double shippingTotal = Double.parseDouble(totalShipping);
        BaseAsserts.ShouldBeTrue(shippingTotal<5,"Total shipping value is less than $5");
        //Assert.assertTrue(shippingTotal < 5,"Total shipping value is less than $5");
        //13. Proceed to checkout
        basicUtils.clickOn("xpath://button[@class='button btn btn-default standard-checkout button-medium']");
        //14. Select Pay by bank wire
        basicUtils.clickOn("xpath://a[@class='bankwire']");
        //15. Click on "I Confirm my order"
        basicUtils.clickOn("xpath://button[@class='button btn btn-default button-medium']");
        //16. Verify successful order confirmation by validating the message "Your order on My Store is complete."
        basicUtils.checkingData("xpath://strong[contains(text(),'Your order on My Store is complete.')]","Your order on My Store is complete.");
        //17. Fetch the wire details: the total amount, name of account owner, name of bank
        WebElement amount = basicUtils.findByElement("xpath","//span[@class='price']");
        String totalAmount = amount.getText();
        System.out.println("Total amount = "+totalAmount);
        WebElement name = basicUtils.findByElement("xpath", "//div[@class='box']/strong[1]");
        String nameOfAccount = name.getText();
        System.out.println("Name of account holder "+ nameOfAccount);
        WebElement bank = basicUtils.findByElement("xpath", "//div[@class='box']/strong[3]");
        String nameOfBank = bank.getText();
        System.out.println("Name of the Bank "+ nameOfBank);
    }
}
