package com.regression.tests.AutomationFunctionality;

import com.automation.framework.reusables.BaseAsserts;
import com.automation.framework.reusables.BasicTests;
import com.automation.vechical.application.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class VehicalDealershipWithFrameWork extends BasicTests {

    @Test
    public void validateVehicleFunctionality() throws IOException {
        HomePage homePage = new HomePage(driver);
        BMWPage bmwPage = homePage
                .navigateToWebsite()
                .typeBMW()
                .clickOnSearch();
        bmwPage.checkingBMWCars();
        PriceRangePage priceRangePage = bmwPage
                .enterMinPrice()
                .enterMaxPrice()
                .clickOnGo();
        priceRangePage.checkBMWCarsInPriceRange();
        PriceAndMillagePage priceAndMillagePage = priceRangePage
                .clickOn60000Miles();
        priceAndMillagePage
                .checkBMWCarsWithInPrinceAndMillage();

        List<Integer> miles= (List<Integer>) priceAndMillagePage.checkCarsMilesSection();
        List<Integer> filteredMiles=  miles.stream().filter(item -> item > 60000)
                .collect(Collectors.toList());
        BaseAsserts.ShouldBeEqual(filteredMiles.size(),0);

        List<Integer> priceRange=  PriceAndMillagePage.checkCarsPriceRange();
        List<Integer> filterPriceRange= priceRange.stream().filter(item -> item >= 9000 && item <=9100 )
                .collect(Collectors.toList());
        BaseAsserts.ShouldBeEqual(filterPriceRange.size(),priceRange.size());

        Millage60000Page millage60000Page = priceAndMillagePage
                .clearMinPrice()
                .clearMaxPrice()
                .clickOnGOToClear();

        BMWPage bmwPage1 = millage60000Page
                .clickOnAll();
        bmwPage1.checkNoOfAdds();
        BaseAsserts.ShouldBeEqual( bmwPage.checkingBMWCars(), bmwPage.checkingBMWCars(),"Both values are not same");
    }
}
