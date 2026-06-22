package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    //locator of login
    private By loginButton = By.xpath("//a[@class='px-4 flex items-center gap-2']");
    //Locator of Get strted
    private By getStartedBtn = By.xpath("//button[text()='Get Started']");


    public void clickLoginButton() {
        driver.findElement(loginButton).click();
        }
    public void clickGetStartedBtn() {
        driver.findElement(getStartedBtn).click();
    }
}
