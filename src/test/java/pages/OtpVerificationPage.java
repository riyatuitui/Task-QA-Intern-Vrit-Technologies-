package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OtpVerificationPage {
    WebDriver driver;

    public OtpVerificationPage(WebDriver driver) {
        this.driver = driver;
    }
    private By otpInput = By.xpath("//input[@data-input-otp='true']");

    private By verifyBtn = By.xpath("//button[normalize-space()='Verify Code']");



    public void enterOtp(String otp) {
        driver.findElement(otpInput).sendKeys(otp);
    }

    public void clickVerify() {
        driver.findElement(verifyBtn).click();
    }
}
