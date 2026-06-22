package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SetUpAccountPage {
    WebDriver driver;

    public SetUpAccountPage(WebDriver driver) {
        this.driver = driver;

    }

    private By firstNameField = By.xpath("//input[@placeholder='Enter Your First Name']");


    private By lastNameField = By.xpath("//input[@placeholder='Enter Your Last Name']");

    private By emailField = By.xpath("//input[@placeholder='Enter Your Email Address']");

    private By phoneNumberField = By.xpath("//input[@name='phoneNumber']");

    private By passwordField = By.xpath("//input[@name='password']");

    private By confirmPasswordField = By.xpath("//input[@name='confirmPassword']");
    private By nextButton = By.xpath(" //button[normalize-space()='Next']");


    public void fillFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);

    }

    public void fillLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    public void fillEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    public void fillPhoneNumber(String phoneNumber){
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    public void fillPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public void fillConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
    }
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

}