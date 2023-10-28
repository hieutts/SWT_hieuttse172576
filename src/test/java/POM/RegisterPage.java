package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.stream.Stream;

public class RegisterPage {
    WebDriver driver;
            By myAccountLink = By.xpath("//a[@title='Create an Account']");
            By myFirstName = By.id("firstname");
            By myMiddleName = By.id("middlename");
            By myLastName = By.id("lastname");
            By myEmail = By.id("email_address");
            By myPassword= By.id("password");
            By myConfirm = By.id("confirmation");
            By signUpForNewLetter = By.xpath("(//label[normalize-space()='Sign Up for Newsletter'])[1]");
            By registerBtn = By.xpath("//button[@title='Register']");
            public RegisterPage(WebDriver driver){
                    this.driver = driver;
            }


            public void ClickCreateAccountLink() {
                    driver.findElement(myAccountLink).click();
            }
        public void fillRegistrationForm(String firstName, String middleName, String lastName, String email, String password, String confirm) {
                WebElement firstNameElement = driver.findElement(myFirstName);
                WebElement middleNameElement = driver.findElement(myMiddleName);
                WebElement lastNameElement = driver.findElement(myLastName);
                WebElement emailElement = driver.findElement(myEmail);
                WebElement passwordElement = driver.findElement(myPassword);
                WebElement confirmElement = driver.findElement(myConfirm);

                firstNameElement.sendKeys(firstName);
                middleNameElement.sendKeys(middleName);
                lastNameElement.sendKeys(lastName);
                emailElement.sendKeys(email);
                passwordElement.sendKeys(password);
                confirmElement.sendKeys(confirm);
        }
        public void clickSignUpForNewsletter() {
                driver.findElement(signUpForNewLetter).click();
        }


        public void clickRegisterButton() {
                driver.findElement(registerBtn).click();
        }
        public void waitForRegistrationCompletion(String exMess) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
             WebElement acMess =   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li[class='success-msg'] ul li span")));
            Assert.assertEquals(exMess, acMess.getText(), "Register is not successfully!");
        }

}
