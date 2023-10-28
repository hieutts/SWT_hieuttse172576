package BAITAP;
/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

"The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty

 */
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Test
public class Testcase03 {
    public static void testTC03() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on �MOBILE� menu
            WebElement mobileElem = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
            mobileElem.click();

            //3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile
            WebElement addToCardForSony = driver.findElement((By.xpath("(//span[contains(text(),'Add to Cart')])[2]")));
            addToCardForSony.click();

            // switching to new window
//            for (String handle : driver.getWindowHandles()) {
//                driver.switchTo().window(handle);
//            }

//            4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed
//
//            "The requested quantity for "Sony Xperia" is not available.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement QTYElem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input[title='Qty']"))));

            QTYElem.clear();
            QTYElem.sendKeys("1000");

            WebElement updateElem = driver.findElement(By.cssSelector("button[title='Update']"));
            updateElem.click();

            //5. Verify the error message
            String exError = "The requested quantity for \"Sony Xperia\" is not available.";
            WebElement acError = driver.findElement(By.xpath("//p[@class='item-msg error']"));
            Assert.assertEquals(acError.getText(), exError, "Not matched error message");
            //6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
            WebElement emptyCardLink = driver.findElement(By.cssSelector("button[id='empty_cart_button'] span span"));
            emptyCardLink.click();
            String exMesEmpty = "SHOPPING CART IS EMPTY";
            WebElement acMesEmpty = driver.findElement(By.cssSelector("div[class='page-title'] h1"));
            Assert.assertEquals(exMesEmpty, acMesEmpty);
            //7. Verify cart is empty
            WebElement checkElem = driver.findElement(By.cssSelector("div[class='main-container col1-layout'] p:nth-child(1)"));
            String exValue = "You have no items in your shopping cart.";
            Assert.assertEquals(exValue, checkElem);


        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}
