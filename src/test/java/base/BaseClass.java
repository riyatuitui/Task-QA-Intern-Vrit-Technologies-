package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {
public WebDriver driver;
@BeforeClass(alwaysRun = true)
public void setUp() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();

    driver.get("https://authorized-partner.vercel.app/");
}
@AfterClass(alwaysRun = true)
public void closeBrowser() {
    driver.quit();
}
}