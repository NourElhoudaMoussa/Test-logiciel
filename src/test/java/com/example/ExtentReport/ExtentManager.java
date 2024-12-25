package com.example.ExtentReport;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent ;

    public static synchronized ExtentReports getInstance () {
        if (extent==null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = "target/extent/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath) ;
            reporter.config().setTheme(Theme.DARK);
            extent.setSystemInfo("user", "nour");
            reporter.config().setDocumentTitle("Test Report");
            extent=new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent ;
    }
}