package com.regression.tests.AutomationFunctionality;

import com.automation.application.shopping.*;
import com.automation.framework.reusables.BaseAsserts;
import com.automation.framework.reusables.BasicTests;
import org.testng.annotations.Test;

import java.io.IOException;

public class AutomationPracticeFunctionalityWithFrameWork extends BasicTests {

    @Test
    public void validateFunctionality() throws IOException {
        LandingPage landingPage = new LandingPage(browserManager.getDriver());
        LoginPage loginPage = landingPage
                .navigateToWebsite()
                .clickSigIn();
        MyAccountPage myAccountPage = loginPage
                .enterUserName("hey@abc.com")
                .enterPassword("Testing@1234")
                .clickSubmit();
        TshirtDetailsPage tshirtDetailsPage =myAccountPage
                .tshirt();
        tshirtDetailsPage
                .clickOnTshirt();
        browserManager.getDriver().switchTo().frame(0);
        OrderPage orderPage = tshirtDetailsPage
                .clickOnAddToCart()
                .checkProductAddedMessage()
                .clickOnProceedToCheckOut();
        Double value = orderPage.fetchingTotalPrice();
        BaseAsserts.ShouldBeTrue(value<20,"Total order value is less than $20" );
        AddressPage addressPage = orderPage
                .clickOnProceedToCheckOutOnSignPage();
        ShippingPage shippingPage = addressPage
                .clickOnProceedToCheckOutOnAddressPage();
        shippingPage.clickOnTerms();
        Double shippingTotal = shippingPage.checkDeliveryFee();
        BaseAsserts.ShouldBeTrue(shippingTotal<5,"Total shipping value is less than $5");
        PaymentPage paymentPage = shippingPage
                .clickOnProceedToCheckOutONShippingPage();
        BankWirePage bankWirePage = paymentPage
                .clickOnBankWirePayment();
        OrderConfirmationPage orderConfirmationPage = bankWirePage
                .clickOnConfirmMyOrder();
        orderConfirmationPage
                .checkOrderCompleteMessage();
        String amount =orderConfirmationPage.fetchAccountHolderAmount();
        System.out.println("Total amount = "+amount);
        String name = orderConfirmationPage.fetchAccountHolderName();
        System.out.println("Name of AccountHolder is "+name);
        String bank = orderConfirmationPage.fetchAccountHolderBank();
        System.out.println("Bank Name is "+bank);

    }
}
