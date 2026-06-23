package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class VerificationPage {
    WebDriver driver;
    WebDriverWait wait;

    public VerificationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private By registrationNumberField = By.xpath("//input[@placeholder='Enter your registration number']");
    private By countryDropdown = By.xpath("//span[normalize-space()='Select Your Preferred Countries']/ancestor::button");
    private By countryField = By.xpath("//div[@class='flex cursor-pointer items-center justify-between p-2 space-y-1 hover:bg-accent']//span[text()='Nepal']");
    private By institutionCheckBox = By.xpath("(//button[@role='checkbox'])[1]");
    private By certificationField = By.xpath("//input[@placeholder='E.g., ICEF Certified Education Agent']");
    private By documentField = By.xpath("(//input[@type='file'])[1]");
    private By submittButton = By.xpath("//button[@type='submit']");


    public void fillRegistrationNumber(String registrationNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(registrationNumberField)).sendKeys(registrationNumber);
    }

    public void selectCountry() {
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(countryField)).click();
    }
    public void institutionCheckBox() {
        driver.findElement(institutionCheckBox).click();
    }
    public void fillCertification(String certification) {
        driver.findElement(certificationField).sendKeys(certification);
    }
    public void uploadFile(){
        File document = new File("src/test/resources/QAIntern _Task.pdf");
        driver.findElement(documentField).sendKeys(document.getAbsolutePath());
    }
    public void clickSubmitButton() {
        driver.findElement(submittButton).click();
    }
}

