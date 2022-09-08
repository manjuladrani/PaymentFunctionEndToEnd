package com.automation.vechical.application;

import com.automation.framework.reusables.BasicUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class PriceRangePage extends BasicUtils {
    WebDriver driver;
    public PriceRangePage(WebDriver driver) {
        super(driver);
        this.driver =driver;
    }
    public String checkBMWCarsInPriceRange(){
        WebElement priceAdds = findByElement("xpath","//h2[@class='h1-responsive-experimental ads-count']");
        String priceAddNo = priceAdds.getText().split(" ",2)[0].replace(",","");
        System.out.println("No of BMW cars b/n 9000 to 9100 price "+ priceAddNo);
        return priceAddNo;
    }
    public PriceAndMillagePage clickOn60000Miles() throws IOException {
        clickOn("xpath://a[contains(text(),'60,000 miles')]");
        return new PriceAndMillagePage(driver);

    }

}
