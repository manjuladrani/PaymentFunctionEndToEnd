package com.regression.tests;

import com.automation.framework.reusables.BasicTests;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class VehicleDealership extends BasicTests {
    @Test
    public void VehicleWebsiteSearchFunctionality() throws InterruptedException, ParseException, IOException {
        //Navigate to https://www.gumtree.com/
        basicUtils.launchApplication(" https://www.gumtree.com/");
        //In the search box, search for “BMW” car
        basicUtils.typeInto("xpath://input[@name='q']","BMW");
        basicUtils.clickOn("xpath://button[@class='button button--primary search-button']/child::span[@class='icon icon--magnifying-glass css-0 eom5h670']");
        Thread.sleep(2000);
        //Fetch total ads at the left side below. “Home/Motors/Cars” link
        WebElement adds = basicUtils.findByElement("xpath","//h2[@class='h1-responsive-experimental ads-count']");
        String addNo = adds.getText().split(" ",2)[0].replace(",","");
        System.out.println("Total BMW cars "+addNo);
        //Now under the filter section, add min price as 9000 and max price as 9100 and click Go
        basicUtils.typeInto("xpath://input[@id='min_price']","9000");
        Thread.sleep(2000);
        basicUtils.typeInto("xpath://input[@name='max_price']","9100");
        Thread.sleep(2000);
        basicUtils.clickOn("xpath://div[@name='price_form']/div[3]/button");
        Thread.sleep(5000);
        //Now check that reduced total ads is getting displayed, the total adds should be 51 (mentioned in step 3).
        WebElement priceAdds = basicUtils.findByElement("xpath","//h2[@class='h1-responsive-experimental ads-count']");
        String priceAddNo = priceAdds.getText().split(" ",2)[0].replace(",","");
        System.out.println("No of BMW cars b/n 9000 to 9100 price "+priceAddNo);
        Thread.sleep(2000);
        //Now click on Mileage Upto 60000 miles and validate that only 9 adds are being displayed
        basicUtils.clickOn("xpath://a[contains(text(),'60,000 miles')]");
        Thread.sleep(2000);
        WebElement millageAdds = basicUtils.findByElement("xpath","//h2[@class='h1-responsive-experimental ads-count']");
        String millageAddNo = millageAdds.getText().split(" ",2)[0].replace(",","");
        System.out.println("BMW cars available in 60000miles and 9000-9100 range price "+millageAddNo);
        Thread.sleep(2000);
        //Now, in the search result section, check all the cars are between price range (9000, 9100) and no car should have mileage above 60000 miles.
        By myMillage = By.xpath("//article[@class='listing-maxi']/a/div[2]/ul/li[2]/span[2]");
        List<WebElement> myMillageElements = driver.findElements(myMillage);
        for (WebElement e: myMillageElements){
            int millage = Integer.parseInt(e.getText().split(" ")[0].replace(",",""));
            Assert.assertTrue( millage<= 60000, "Millage is less or equals to 60000");
        }

        By myPrice = By.xpath("//strong[@class='h3-responsive']");
        List<WebElement> myPriceElements = driver.findElements(myPrice);
        for (WebElement a: myPriceElements){
            int price = Integer.parseInt(a.getText().replace("£","").replace(",",""));
                Assert.assertTrue(price <= 9100 && price >=9000, "Price is in between 9000 to 9100");
            //System.out.println(price);
        }

        basicUtils.clickOn("xpath://input[@id='min_price']");
        Actions actMin =new Actions(driver);
        actMin.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).sendKeys(Keys.DELETE).build().perform();
        Thread.sleep(2000);
        basicUtils.clickOn("xpath://*[@id=\"refinement-panel\"]/form/div/section[1]/div/div[2]/span/input");
        Actions actMax = new Actions(driver);
        actMax.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).sendKeys(Keys.DELETE).build().perform();
        Thread.sleep(2000);
        basicUtils.clickOn("xpath://div[@name='price_form']/div[3]/button");
        Thread.sleep(5000);
        basicUtils.clickOn("xpath://aside[@id='refinement-panel']/form/div/section[4]/ul/li[1]/a");
        Thread.sleep(2000);
        WebElement finalAdds = basicUtils.findByElement("xpath","//h2[@class='h1-responsive-experimental ads-count']");
        String finalNoOfAdds = finalAdds.getText().split(" ",2)[0].replace(",","");
        System.out.println("Final no of BMW cars "+ finalNoOfAdds);
        Assert.assertEquals(addNo,finalNoOfAdds,"Both values are same");
    }
}
