package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
WebDriver driver;
WebDriverWait wait;

public RegisterPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
}
//Locator of remember me
private By checkBox = By.id("remember");
//Locator of Continue
    private By continueButton = By.xpath("//button[normalize-space()='Continue']");


    //Action
    public void clickCheckBox() {
        wait.until(ExpectedConditions.elementToBeClickable(checkBox)).click();
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }
}
