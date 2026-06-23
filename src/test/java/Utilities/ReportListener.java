package Utilities;

import base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportListener implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String reportName;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Report" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);

        sparkReporter.config().setDocumentTitle("AutomationReport");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("computername", "Dell");
        extent.setSystemInfo("Environment", "QA");


        extent.setSystemInfo("User", "Riya");
        extent.setSystemInfo("Os", "Windows 11");
        extent.setSystemInfo("Browsername", "Chrome");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName() + " :: " + result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + "Get Successfully executed");

    }

    @Override
    public void onTestFailure(ITestResult result1) {
        test = extent.createTest(result1.getTestClass().getName() + "::" + result1.getMethod().getMethodName());
        test.assignCategory(result1.getMethod().getGroups());
        test.log(Status.FAIL, result1.getName() + "Got Failed");
        test.log(Status.INFO, result1.getThrowable().getMessage());

        System.out.println("Test failed: " + result1.getName());

        //  Get driver from test class
        Object testClass = result1.getInstance();
        WebDriver driver = ((base.BaseClass) testClass).driver;
        //  Take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("YYYY-MM-HH-mm-ss").format(new Date());

        String path = System.getProperty("user.dir") + "/screenshots/" + result1.getName() + timestamp+ ".png";
        try {
            FileUtils.copyFile(src, new File(path));
            System.out.println("Screenshot saved at: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(path);
    }
    @Override
    public void onTestSkipped(ITestResult result2){
        test = extent.createTest(result2.getTestClass().getName() + "::" + result2.getMethod().getMethodName());
        test.assignCategory(result2.getMethod().getGroups());
        test.log(Status.SKIP, result2.getName() + "Got Skipped");
        test.log(Status.INFO, result2.getThrowable().getMessage());
        System.out.println("Test failed: " + result2.getName());

    }
    @Override
    public void onFinish (ITestContext testContext){
        extent.flush();
        String pathOfExtendReport = System.getProperty("user.dir") + "\\reports\\" + reportName;
        File extendRport = new File(pathOfExtendReport);
        try {
            Desktop.getDesktop().browse(extendRport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
