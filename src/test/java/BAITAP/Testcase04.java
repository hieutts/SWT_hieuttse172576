package BAITAP;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)

4. Click on �COMPARE� button. A popup window opens

5. Verify the pop-up window and check that the products are reflected in it

Heading "COMPARE PRODUCTS" with selected products in it.

6. Close the Popup Windows

*/
@Test
public class Testcase04 {
    public static void testTC04() {
        WebDriver driver= driverFactory.getChromeDriver();
        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on �MOBILE� menu
            WebElement mobileElem = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
            mobileElem.click();

            //3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)
            WebElement addToCompareSony = driver.findElement(By.xpath("(//a[@class='link-compare'][normalize-space()='Add to Compare'])[2]"));
            addToCompareSony.click();

            WebElement addToCompareIphone = driver.findElement(By.xpath("(//a[@class='link-compare'][normalize-space()='Add to Compare'])[3]"));
            addToCompareIphone.click();


            //4. Click on �COMPARE� button. A popup window opens
            WebElement compareElem = driver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]"));
            compareElem.click();
            WebElement sonyWeb = driver.findElement(By.xpath("(//a[@title='Sony Xperia'][normalize-space()='Sony Xperia'])[1]")) ;
            WebElement iphoneWeb = driver.findElement(By.xpath("(//a[@title='IPhone'][normalize-space()='IPhone'])[1]")) ;


            //5. Verify the pop-up window and check that the products are reflected in it
            //
            //Heading "COMPARE PRODUCTS" with selected products in it.
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            WebElement headingElem = driver.findElement(By.xpath("div[class='page-title title-buttons'] h1"));
            String exHeading = "COMPARE PRODUCTS";
            Assert.assertEquals(exHeading, headingElem.getText(), "Heading is not matched!");

            WebElement sonyPopup = driver.findElement(By.xpath("(//a[normalize-space()='Sony Xperia'])[1]"));
            WebElement iphonePopup = driver.findElement(By.xpath("(//a[normalize-space()='IPhone'])[1]"));

            Thread.sleep(5000);
            Assert.assertEquals(sonyWeb.getText(), sonyPopup.getText(), "Not matched product name");
            Assert.assertEquals(iphoneWeb.getText(),iphonePopup.getText(), "Not matched product name");



            //

            WebElement closeBtn = driver.findElement(By.xpath("//button[@title='Close Window']"));
            closeBtn.click();

        }catch ( Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
