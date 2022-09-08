package com.automation.vechical.application;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PriceAndMillagePage  extends BasicUtils {
    static WebDriver driver;
    public PriceAndMillagePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public String checkBMWCarsWithInPrinceAndMillage(){
        WebElement millageAdds = findByElement("xpath","//h2[@class='h1-responsive-experimental ads-count']");
        String millageAddNo = millageAdds.getText().split(" ",2)[0].replace(",","");
        System.out.println("BMW cars available in 60000miles and 9000-9100 range price "+millageAddNo);
        return millageAddNo;
    }

    public List<Integer> checkCarsMilesSection(){
        List<WebElement> milesValue = driver.findElements(By.xpath("//ul[contains(@class,'listing-attributes inline-list')]//li[2]//span[2]"));
        List<Integer> miles=new ArrayList<>();
        System.out.println("The Miles list Size is : "+milesValue.size());
        for(WebElement each_Miles_Value : milesValue){
            String stringMilesValue = each_Miles_Value.getText();
            System.out.println(stringMilesValue.trim().substring(0,6).replaceAll(",",""));
            miles.add(Integer.parseInt( stringMilesValue.trim().substring(0,6).replaceAll(",","")));
        }
        return miles;
    }
//public static List<Integer> checkCarsMilesSection() {
//    By myMillage = By.xpath("//article[@class='listing-maxi']/a/div[2]/ul/li[2]/span[2]");
//    List<WebElement> myMillageElements = driver.findElements(myMillage);
//    for (WebElement e : myMillageElements) {
//        int miles = Integer.parseInt(e.getText().split(" ")[0].replace(",", ""));
//        Assert.assertTrue(miles <= 60000, "Millage is less or equals to 60000");
//    }
//    return (List<Integer>) myMillage;
//}
//    public static List<Integer> checkCarsPriceRange() {
//        By myPrice = By.xpath("//strong[@class='h3-responsive']");
//        List<WebElement> myPriceElements = driver.findElements(myPrice);
//        for (WebElement a : myPriceElements) {
//            int price = Integer.parseInt(a.getText().replace("£", "").replace(",", ""));
//            Assert.assertTrue(price <= 9100 && price >= 9000, "Price is in between 9000 to 9100");
//            //System.out.println(price);
//        }
//        return (List<Integer>) myPrice;
 //   }
    public static List<Integer> checkCarsPriceRange(){
        List<WebElement> searchList = driver.findElements(By.xpath("//span[@class = 'listing-price']"));
        List<Integer> priceRange=new ArrayList<>();
        System.out.println("The Price List Size is : "+searchList.size());
        for(WebElement each_Price_Value : searchList){
            String stringPriceValue = each_Price_Value.getText().trim().substring(1,6);
            System.out.println(stringPriceValue.replaceAll(",",""));
            priceRange.add(Integer.parseInt(stringPriceValue.replaceAll(",","")));
        }
        return priceRange;
    }
    public PriceAndMillagePage clearMinPrice() throws IOException {
        clickOn("xpath://input[@id='min_price']");
        Actions actMin =new Actions(driver);
        actMin.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).sendKeys(Keys.DELETE).build().perform();
        return this;
    }
    public PriceAndMillagePage clearMaxPrice() throws IOException {
        clickOn("xpath://*[@id=\"refinement-panel\"]/form/div/section[1]/div/div[2]/span/input");
        Actions actMax = new Actions(driver);
        actMax.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).sendKeys(Keys.DELETE).build().perform();
        return this;
    }
    public Millage60000Page clickOnGOToClear() throws IOException {
        clickOn("xpath://div[@name='price_form']/div[3]/button");
        return new Millage60000Page(driver);
    }

}
