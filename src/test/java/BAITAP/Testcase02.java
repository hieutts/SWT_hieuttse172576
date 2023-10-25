package BAITAP;
/*

Test Steps:

1. Goto http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

4. Click on Sony Xperia mobile

5. Read the Sony Xperia mobile from detail page.

6. Compare Product value in list and details page should be equal ($100).

*/
import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

@Test
public class Testcase02 {
    public static void testTestcase02() {
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on �MOBILE� menu
            WebElement mobileElem = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
            mobileElem.click();
            //3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
             WebElement SonyOnMenu = driver.findElement(By.id("product-price-1"));
            int priceOfSonyOnMenu = Integer.parseInt(SonyOnMenu.getText());
            //4. Click on Sony Xperia mobile
            WebElement SonyXperia = driver.findElement(By.xpath("//a[normalize-space()='Sony Xperia']"));
            SonyXperia.click();
            //5. Read the Sony Xperia mobile from detail page.
            WebElement SonyOnDetail = driver.findElement(By.xpath("//div[@class='price-info']"));
            int priceOfSonyOnDetail= Integer.parseInt(SonyOnDetail.getText());
            //Compare Product value in list and details page should be equal ($100).
            Assert.assertEquals(priceOfSonyOnMenu, priceOfSonyOnDetail, "Product value in list and details page not equal");

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }

    }

