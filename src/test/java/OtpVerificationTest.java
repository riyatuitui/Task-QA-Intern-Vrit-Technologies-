import com.mailosaur.MailosaurClient;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class OtpVerificationTest {

    // ======= CONFIGURATION =======
    // Mailosaur API key used to access the mailbox service
    private static final String API_KEY = "FYaDRqCsmgseJPEVm9TI5kiAe4jjx3Di";

    // Mailosaur server ID where test emails are received
    private static final String SERVER_ID = "odbofzt1";

    // Construct the Mailosaur domain dynamically
    private static final String SERVER_DOMAIN = SERVER_ID + ".mailosaur.net";
    // ================================================================

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // 1. Create a completely unique email address for this registration test run
            String testEmail = "anything" + System.currentTimeMillis() + "@" + SERVER_DOMAIN;
            System.out.println("Generated Test Email Address: " + testEmail);

            // 2. Open website
            driver.get("https://authorized-partner.vercel.app/register");

            // 3. Complete form inputs
            driver.findElement(By.xpath("//button[@id='remember']")).click();
            driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();

            sleep(4000);

            driver.findElement(By.xpath("//input[@placeholder='Enter Your First Name']")).sendKeys("John");
            driver.findElement(By.xpath("//input[@placeholder='Enter Your Last Name']")).sendKeys("Giri");
            driver.findElement(By.xpath("//input[@placeholder='Enter Your Email Address']")).sendKeys(testEmail);
            driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("9889589901");

            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Password#!31");
            driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys("Password#!31");
            driver.findElement(By.xpath(" //button[normalize-space()='Next']")).click();

            sleep(4000);

            System.out.println("Form submitted! Waiting for Mailosaur to capture the OTP email...");

            // =========================================================
            // MAILOSAUR EMAIL RETRIEVAL
            // =========================================================

            // Create Mailosaur client instance
            // 4. Initialize Mailosaur Client to fetch incoming message
            MailosaurClient client = new MailosaurClient(API_KEY);

            // Specify Mailosaur server
            MessageSearchParams params = new MessageSearchParams();
            params.withServer(SERVER_ID);

            // Search for emails sent to generated address
            SearchCriteria criteria = new SearchCriteria();
            criteria.withSentTo(testEmail);

            // Wait for and retrieve incoming email
            Message message = client.messages().get(params, criteria);
            System.out.println("Email caught successfully! Subject line: " + message.subject());

            sleep(4000);
            // 5. Extract Text body content FIRST : // Get plain text email body
            String emailBodyText = message.text().body();

            // Print debug info BEFORE extracting OTP so it doesn't crash invisibly
            System.out.println("\n--- DEBUG: ACTUAL EMAIL TEXT CONTENT ---");
            System.out.println(emailBodyText);
            System.out.println("----------------------------------------\n");

            // 6. Extract the 6-digit number
            String otpCode = extractOTP(emailBodyText);
            System.out.println("Extracted Verification Code: " + otpCode);


            // 7. Selenium steps: Type the code back into your Web UI
            System.out.println("Locating OTP input element...");

            // Locate using the explicit 'data-input-otp' attribute
            By otpInputLocator = By.xpath("//input[@data-input-otp='true']");

            // Wait briefly or find the element directly
            var otpInputElement = driver.findElement(otpInputLocator);

            // Clear anything if present, click it, and send the code
            otpInputElement.click();
            otpInputElement.sendKeys(otpCode);

            System.out.println("OTP successfully typed into the UI!");

            driver.findElement(By.xpath("//button[normalize-space()='Verify Code']")).click();
            sleep(4000);


            driver.findElement(By.xpath("//input[@placeholder='Enter Agency Name']")).sendKeys("John Organization");
            driver.findElement(By.xpath("//input[@placeholder='Enter Your Role in Agency']")).sendKeys("QA");
            driver.findElement(By.xpath("//input[@placeholder='Enter Your Agency Email Address']")).sendKeys("johnorg@gmail.com");
            driver.findElement(By.xpath("//input[@placeholder='Enter Your Agency Website']")).sendKeys("john.company.com");

            driver.findElement(By.xpath("//input[@placeholder='Enter Your Agency Address']")).sendKeys("Kathmandu,Nepal");


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Open the dropdown
            WebElement regionDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@role='combobox' and contains(.,'Select Your Region of Operation')]")
                    )
            );
            regionDropdown.click();

            // Select Nepal from the dropdown options
            WebElement nepalOption = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[text()='Nepal']")
                    )
            );
            nepalOption.click();


            driver.findElement(By.xpath("//button[text()='Next']")).click();


        } catch (Exception e) {
            System.err.println("Automation Test Failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close browser execution context
            //driver.quit();
        }
    }

    /**
     * Finds any 6-digit verification code inside the text email body.
     */

    private static String extractOTP(String emailBody) {
        // Looks for a word boundary, exactly 6 digits, and another word boundary
        // Regular expression:
        // \b      = word boundary
        // \d{6}   = exactly 6 digits
        // (...)   = capture the digits as Group 1
        // \b      = ending word boundary
        String regex = "\\b(\\d{6})\\b";

        // Compile the regex pattern for matching
        Pattern pattern = Pattern.compile(regex);

        // Look for the first occurrence of a 6-digit number
        Matcher matcher = pattern.matcher(emailBody);

        if (matcher.find()) {
            // Return the captured 6-digit OTP
            // Group 1 corresponds to (\d{6})
            return matcher.group(1);
        }
        // No 6-digit OTP found in the email body
        throw new RuntimeException("Could not locate any standalone 6-digit OTP code in the email body text!");
    }
}