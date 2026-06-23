package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AgencyPage {
WebDriver driver;
WebDriverWait wait;
public AgencyPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}
private By nameField = By.xpath("//input[@placeholder='Enter Agency Name']");
private By roleField = By.xpath("//input[@placeholder='Enter Your Role in Agency']");
private By emailField = By.xpath("//input[@placeholder='Enter Your Agency Email Address']");
private By websiteField = By.xpath("//input[@placeholder='Enter Your Agency Website']");
private By addressField = By.xpath("//input[@placeholder='Enter Your Agency Address']");
private By regionField = By.xpath("//button[@role='combobox']");
private By regionSearchField = By.xpath("//input[@placeholder='Search...']");
private By regionName = By.xpath("(//div[@class=\"flex cursor-pointer items-center justify-between p-2 space-y-1 hover:bg-accent\"])[1]");

private By nextButton = By.xpath("//button[@type=\"submit\"]");


public void fillName(String name) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
}
public void fillRole(String role) {
    driver.findElement(roleField).sendKeys(role);
}
public void fillEmail(String email) {
    driver.findElement(emailField).sendKeys(email);
}
public void fillWebsite(String website) {
    driver.findElement(websiteField).sendKeys(website);
}
public void fillAddress(String address) {
    driver.findElement(addressField).sendKeys(address);
}
public void selectRegion(){
    driver.findElement(regionField).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(regionSearchField)).sendKeys("Test");
    wait.until(ExpectedConditions.elementToBeClickable(regionName)).click();
}
public void clickNextButton() {
    driver.findElement(nextButton).click();
}
}