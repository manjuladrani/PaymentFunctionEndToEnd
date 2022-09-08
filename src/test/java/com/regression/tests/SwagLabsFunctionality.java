package com.regression.tests;

import com.automation.framework.reusables.BasicTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class SwagLabsFunctionality extends BasicTests {

   double sum=0;


    @Test
    public void ValidatePlacementFunctionality() throws InterruptedException, ParseException, IOException {

        //Step 1. Login to https://www.saucedemo.com/
        basicUtils.launchApplication("https://www.saucedemo.com/");
        Thread.sleep(2000);
        //Step 2. Enter username "standard_user"
        basicUtils.typeInto("id:user-name", "standard_user");
        Thread.sleep(2000);
        //Step 3. Enter password "secret_sauce"
        basicUtils.typeInto("id:password","secret_sauce");
        Thread.sleep(2000);
        //Step 4. Click Login
        basicUtils.clickOn("id:login-button");
        Thread.sleep(2000);
        //Step 5. Add to cart "Sauce Labs Backpack"
        basicUtils.clickOn("id:add-to-cart-sauce-labs-backpack");
        Thread.sleep(2000);
        //Step Verify the cart Icon is updated and shows the Item number. For ex if you add Bagpack, it would display number "1" on cart icon. (checkpoint 1)
        basicUtils.checkingData("xpath://span[@class='shopping_cart_badge']","1");
        System.out.println("Cart is showing 1 item");
        Thread.sleep(2000);
        //Step 7. Add to cart "Sauce Labs Fleece Jacket"
        basicUtils.clickOn("id:add-to-cart-sauce-labs-fleece-jacket");
        Thread.sleep(2000);
        //Step 8. Verify the cart is updated with the number of items in total (on cart icon). (checkpoint 2)
        basicUtils.checkingData("xpath://span[@class='shopping_cart_badge']","2");
        Thread.sleep(2000);
        //Step 9. Click on Cart icon to check out
        basicUtils.clickOn("xpath://span[@class='shopping_cart_badge']");
        Thread.sleep(2000);
        //Step 10. Verify your items (checkpoint 3)
        basicUtils.checkingData("xpath://div[contains(text(),'Sauce Labs Backpack')]", "Sauce Labs Backpack");
        basicUtils.checkingData("xpath://div[contains(text(),'Sauce Labs Fleece Jacket')]","Sauce Labs Fleece Jacket");
        Thread.sleep(2000);
        //Step 11. Click On Checkout button
        basicUtils.clickOn("id:checkout");
        Thread.sleep(2000);
        //Step 12. Enter your checkout information and move to next page.
        basicUtils.typeInto("xpath://input[@id='first-name']", "Manjula");
        Thread.sleep(2000);
        basicUtils.typeInto("xpath://input[@id='last-name']","Rani");
        Thread.sleep(2000);
        basicUtils.typeInto("xpath://input[@id='postal-code']","K2B7T5");
        Thread.sleep(2000);
        basicUtils.clickOn("id:continue");
        Thread.sleep(2000);
        //Step 13. Verify that the shipping information is "FREE PONY EXPRESS DELIVERY!" , payment information is "SauceCard #31337" and
        //item total (excluding tax) is the total of all items that you placed on bag. (checkpoint 4)
        basicUtils.checkingData("xpath://div[contains(text(),'FREE PONY EXPRESS DELIVERY!')]","FREE PONY EXPRESS DELIVERY!");
        Thread.sleep(2000);
        basicUtils.checkingData("xpath://div[contains(text(),'SauceCard #31337')]","SauceCard #31337");
        Thread.sleep(2000);
        By mySelector = By.xpath("//div[@class='inventory_item_price']");
        List<WebElement> myElements = driver.findElements(mySelector);
        for (WebElement e: myElements){
            NumberFormat format = NumberFormat.getCurrencyInstance();
            double number = (double) format.parse(e.getText());
            System.out.println(number);
            sum += number;
        }
        System.out.println("sum = "+ sum);
        WebElement element = basicUtils.findByElement("xpath","//div[@class='summary_subtotal_label']");
        String totalWithoutTax = element.getText().split(":",2)[1].replace("$","").trim();
        double total = Double.parseDouble(totalWithoutTax);
        System.out.println("totalWithoutTax = "+ total);
        Assert.assertEquals( sum, total, "item total (excluding tax) is the total of all items that you placed on bag.");
        //Step 14. click on Finish
        basicUtils.clickOn("id:finish");
        Thread.sleep(2000);
        //Step 15. Verify Thank you message is displayed on screen (checkpoint 5).
        basicUtils.checkingData("xpath://h2[contains(text(),'THANK YOU FOR YOUR ORDER')]","THANK YOU FOR YOUR ORDER");
        Thread.sleep(2000);
        //Step 16. Close the browser.
        driver.close();
    }

}
