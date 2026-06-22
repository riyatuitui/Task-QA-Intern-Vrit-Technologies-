package tests;

import Utilities.MailosaurService;
import base.BaseClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.OtpVerificationPage;
import pages.RegisterPage;
import pages.SetUpAccountPage;

import static java.lang.Thread.sleep;

public class SignUpTest extends BaseClass {
    String API_KEY = "FYaDRqCsmgseJPEVm9TI5kiAe4jjx3Di";
    String SERVER_ID = "odbofzt1";
    String SERVER_DOMAIN = SERVER_ID + ".mailosaur.net";
    // Create Mailosaur object
    MailosaurService mail = new MailosaurService(API_KEY, SERVER_ID);

    String firstName = "Test";
    String lastName = "User";
    String email = "anything"+ System.currentTimeMillis() + "@" + SERVER_DOMAIN;
    String phoneNumber = "9822222224";
    String password = "Pass@123";
    String confirmPassword = "Pass@123";
    String otp;

    @Test(priority = 1)
    public void signUpTest() {
        //Create HomePage object
        HomePage homePage = new HomePage(driver);
        // click get started button
        homePage.clickGetStartedBtn();

        // Create SignUpPage object
        RegisterPage signUpPage = new RegisterPage(driver);
        // click remember me checkbox
        signUpPage.clickCheckBox();

        // click continue button
        signUpPage.clickContinueButton();
    }
    @Test(priority = 2)
    public void setUpAccount() throws Exception {
        SetUpAccountPage setUpAccountPage = new SetUpAccountPage(driver);

        // enter first name
        setUpAccountPage.fillFirstName(firstName);
        // enter last name
        setUpAccountPage.fillLastName(lastName);

        setUpAccountPage.fillEmail(email);

        setUpAccountPage.fillPhoneNumber(phoneNumber);

        setUpAccountPage.fillPassword(password);
        setUpAccountPage.fillConfirmPassword(confirmPassword);

        setUpAccountPage.clickNextButton();

        // Fetch OTP AFTER email is sent
         otp = mail.getOtp(email);
        System.out.println(otp);
        OtpVerificationPage otpVerificationPage = new OtpVerificationPage(driver);
        otpVerificationPage.enterOtp(otp);
        sleep(5000);
        otpVerificationPage.clickVerify();
    }
}

