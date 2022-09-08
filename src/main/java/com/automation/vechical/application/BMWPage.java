package com.automation.vechical.application;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class BMWPage extends BasicUtils {
    WebDriver driver;
    public BMWPage(WebDriver driver) {
        super(driver);
        this.driver =driver;
    }
    public String checkingBMWCars(){
        WebElement adds = findByElement("xpath","//h2[@class='h1-responsive-experimental ads-count']");
        String addNo = adds.getText().split(" ",2)[0].replace(",","");
        System.out.println("Total BMW cars "+addNo);
        return addNo;
    }
    public BMWPage enterMinPrice() throws IOException {
       typeInto("xpath://input[@id='min_price']","9000");
        return this;
    }
    public BMWPage enterMaxPrice() throws IOException {
       typeInto("xpath://input[@name='max_price']","9100");
        return this;
    }
    public PriceRangePage clickOnGo() throws IOException {
        clickOn("xpath://div[@name='price_form']/div[3]/button");
        return new PriceRangePage(driver);
    }
    public String checkNoOfAdds(){
        WebElement finalAdds = findByElement("xpath","//h2[@class='h1-responsive-experimental ads-count']");
        String finalNoOfAdds = finalAdds.getText().split(" ",2)[0].replace(",","");
        System.out.println("Final no of BMW cars "+ finalNoOfAdds);
        return finalNoOfAdds;
    }
}
