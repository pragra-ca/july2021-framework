package io.pragra.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.pragra.framework.config.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HTMLReports {
    private static HTMLReports instance;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    private HTMLReports() {
        init();
        extentReports = new ExtentReports();
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy_HHmmSS");
        String fileName = Config.getProperty("report.filename").replace("ddMMyyyy_HHmmSS", format.format(new Date()));
        String reportFile = Paths.get(Config.getProperty("report.location"),fileName ).toString();
        extentReports.attachReporter(new ExtentSparkReporter(reportFile));
    }

    private synchronized void init() {
        try {
            Files.createDirectories(Paths.get(Config.getProperty("report.location")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static ExtentTest createTest(String testName){
        if( instance == null ){
            instance = new HTMLReports();
        }
        return instance.extentReports.createTest(testName);
    }

    public synchronized static void closeTest(){
        if(instance != null) {
            instance.extentReports.flush();
            instance = null;
        }
    }


}
