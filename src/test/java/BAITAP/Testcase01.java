package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/
@Test
public class Testcase01 {
    public static void testTestcase01() {
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //Step 2. Verify Title of the page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement titleElem = driver.findElement(By.xpath("//h2[1]"));
            String actualTitle = titleElem.getText();
            String expectedTitle = "THIS IS DEMO SITE FOR   ";
            Assert.assertEquals(actualTitle, expectedTitle, "Title does not match the expected value");
            //Step 3. Click on -> MOBILE -> menu
            WebElement mobileElem = driver.findElement(By.xpath("//a[normalize-space()='Mobile']"));
            mobileElem.click();
            //Step 4. In the list of all mobile , select SORT BY -> dropdown as name
            WebElement dropdownElement = driver.findElement(By.xpath("(//select[@title='Sort By'])[1]"));
            Select selectOption = new Select(dropdownElement);
            selectOption.selectByVisibleText("Name");
            //Step 5. Verify all products are sorted by name
            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile= screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile,new File("E:\\FALL2023\\SWT\\selenium-webdriver-java-main\\src\\test\\java\\BAITAP\\testcase01.png") );

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }

}
