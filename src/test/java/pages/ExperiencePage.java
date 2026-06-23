package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExperiencePage {
    WebDriver driver;
    WebDriverWait wait;
    public ExperiencePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By experienceField = By.xpath("//button[@role='combobox']");
    private By experinceDropdown = By.xpath("//*[text()='8 years']");
    private By studentNumberField = By.xpath("//input[@placeholder='Enter an approximate number.']");
    private By focusAreaField = By.xpath("//input[@placeholder='E.g., Undergraduate admissions to Canada.']");
    private By sucessMetricField = By.xpath("//input[@placeholder='E.g., 90% ']");
    private By serviceCheckBox1 = By.xpath("//button[@role='checkbox']");
    private By serviceCheckBox2 = By.xpath("//button[@role='checkbox' and @data-state='unchecked']");

    private By nextButton = By.xpath("//button[text()='Next']");

    public void fillExperience(){
        wait.until(ExpectedConditions.elementToBeClickable(experienceField)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(experinceDropdown)).click();
    }
    public void fillStudentNumber(String studentNumber){
        driver.findElement(studentNumberField).sendKeys(studentNumber);
    }
    public void fillFocusArea(String focusArea) {
        driver.findElement(focusAreaField).sendKeys(focusArea);
    }
    public void fillSuccessMetrics(String successMetric) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sucessMetricField)).sendKeys(successMetric);

    }
    public void selectServiceProvided() {
        driver.findElement(serviceCheckBox1).click();
    }
    public void selectServiceProvided2() {
        driver.findElement(serviceCheckBox2).click();
    }
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

}
