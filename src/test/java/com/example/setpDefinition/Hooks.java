package com.example.setpDefinition;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.example.ExtentReport.ExtentManager;
import com.example.base.TestBase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private static ExtentReports extentReports;
    private static ExtentTest _scenario;

    @Before
    public void setUp(Scenario scenario) {
        // Initialize Extent Reports
        extentReports = ExtentManager.getInstance();

        // Start a test for the current scenario
        _scenario = extentReports.createTest(scenario.getName());

        // Initialize WebDriver if necessary
        if (TestBase.getDriver() == null) {
            TestBase.setUp();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        // Log test status in Extent Reports
        if (scenario.isFailed()) {
            // Capture screenshot for failed scenario
            String screenshotPath = TestBase.captureScreenshot(scenario.getName());
            Hooks._scenario.fail("Test Failed: ",MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }       
        // Flush Extent Reports
        extentReports.flush();
        TestBase.tearDown();
    }

    public static ExtentTest getExtentTest() {
        return _scenario;
    }
}
