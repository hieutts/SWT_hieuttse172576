package BAITAP;
/* Verify can create an account in e-Commerce site and can share wishlist to other poeple using email.

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Click Create an Account link and fill New User information excluding the registered Email ID.

4. Click Register

5. Verify Registration is done. Expected account registration done.

6. Go to TV menu

7. Add product in your wish list - use product - LG LCD

8. Click SHARE WISHLIST

9. In next page enter Email and a message and click SHARE WISHLIST

10.Check wishlist is shared. Expected wishlist shared successfully.

 */
import POM.RegisterPage;
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
public class Testcase05 {
    public static void testTC05() {
        WebDriver driver= driverFactory.getChromeDriver();
        try {
            // 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            RegisterPage registerPage = new RegisterPage(driver);
            //2. Click on my account link
            WebElement accountBtn = driver.findElement(By.xpath("(//span[@class='label'][normalize-space()='Account'])[1]"));
            accountBtn.click();

            WebElement myAccountBtn = driver.findElement(By.xpath("(//a[@title='My Account'][normalize-space()='My Account'])[1]"));
            myAccountBtn.click();
            //3. Click Create an Account link and fill New User information excluding the registered Email ID.
            registerPage.ClickCreateAccountLink();
            registerPage.fillRegistrationForm("Hieu","Trung", "Tran", "hieu1234567891@fpt.com", "123456", "123456");
            registerPage.clickSignUpForNewsletter();
            //4. Click Register
            registerPage.clickRegisterButton();




            //5. Verify Registration is done. Expected account registration done.
            String exMess = "Thank you for registering with Main Website Store.";
//            WebElement acMess = driver.findElement(By.cssSelector("li[class='success-msg'] ul li span"));
            registerPage.waitForRegistrationCompletion(exMess);

            //6. Go to TV menu
            WebElement tvElem = driver.findElement(By.xpath("(//a[normalize-space()='TV'])[1]"));
            tvElem.click();

            //7. Add product in your wish list - use product - LG LCD
            WebElement LGElem = driver.findElement(By.xpath("(//a[@class='link-wishlist'][normalize-space()='Add to Wishlist'])[1]"));
            LGElem.click();

            //8. Click SHARE WISHLIST
            WebElement shareElem = driver.findElement(By.xpath("(//span[contains(text(),'Share Wishlist')])[1]"));
            shareElem.click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            //9. In next page enter Email and a message and click SHARE WISHLIST
            WebElement emailElem = driver.findElement(By.id("email_address"));
            String emailV = "test@example.com";
            emailElem.sendKeys(emailV);

            WebElement shareBtn = driver.findElement(By.xpath("//button[@title='Share Wishlist']"));
            shareBtn.click();

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            //10.Check wishlist is shared. Expected wishlist shared successfully.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            String exMessWL = "Your Wishlist has been shared.";
            WebElement acMessWL = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[normalize-space()='Your Wishlist has been shared.'])[1]"))) ;
            Assert.assertEquals(acMessWL.getText(), exMessWL, "Send WishList is not success.");

        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
