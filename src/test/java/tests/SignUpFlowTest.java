package tests;

import Utilities.MailosaurService;
import base.BaseClass;
import org.testng.annotations.Test;
import pages.*;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class SignUpFlowTest extends BaseClass {
    String API_KEY = "FYaDRqCsmgseJPEVm9TI5kiAe4jjx3Di";
    String SERVER_ID = "odbofzt1";
    String SERVER_DOMAIN = SERVER_ID + ".mailosaur.net";
    // Create Mailosaur object
    MailosaurService mail = new MailosaurService(API_KEY, SERVER_ID);

    String firstName = "Test";
    String lastName = "User";
    String email = "anything"+ System.currentTimeMillis() + "@" + SERVER_DOMAIN;
    String phoneNumber = "98"+ ThreadLocalRandom.current().nextInt(10000000, 99999999);
    String password = "Pass@123";
    String confirmPassword = "Pass@123";
    String otp;

    @Test(priority = 1)
    public void signUpTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickGetStartedBtn();
        RegisterPage signUpPage = new RegisterPage(driver);
        signUpPage.clickCheckBox();
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
        System.out.println("This is OTP:" + otp);
        OtpVerificationPage otpVerificationPage = new OtpVerificationPage(driver);
        otpVerificationPage.enterOtp(otp);
        sleep(5000);
        otpVerificationPage.clickVerify();
    }
    String agencyName = "Test Agency";
    String role = "QA";
    String agencyEmail = "testagency"+ System.currentTimeMillis() + "@" + "gmail.com";
    String website = "test.com";
    String address = "Bhaktapur";
    @Test(priority = 3)
    public void aagencyDetail(){
        AgencyPage agencyPage = new AgencyPage(driver);
        agencyPage.fillName(agencyName);
        agencyPage.fillRole(role);
        agencyPage.fillEmail(agencyEmail);
        agencyPage.fillWebsite(website);
        agencyPage.fillAddress(address);
        agencyPage.selectRegion();
        agencyPage.clickNextButton();
    }
    String numberOfStudent = "20";
    String focusArea = "Australia";
    String succuessMetrics = "99%";
    @Test(priority = 4)
    public void experienceDetail(){
        ExperiencePage experiencePage = new ExperiencePage(driver);
        experiencePage.fillExperience();
        experiencePage.fillStudentNumber(numberOfStudent);
        experiencePage.fillFocusArea(focusArea);
        experiencePage.fillSuccessMetrics(succuessMetrics);
        experiencePage.selectServiceProvided();
        experiencePage.selectServiceProvided2();
        experiencePage.clickNextButton();
    }
    String registrationNumber = "12345678910";
    String certificationDetail = "ICEF Certified Education Agent";
    @Test(priority = 5)
    public void verificationDetail(){
        VerificationPage verificationPage = new VerificationPage(driver);
        verificationPage.fillRegistrationNumber(registrationNumber);
        verificationPage.selectCountry();
        verificationPage.institutionCheckBox();
        verificationPage.fillCertification(certificationDetail);
        verificationPage.uploadFile();
        verificationPage.clickSubmitButton();
    }


}

