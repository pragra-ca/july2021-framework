package io.pragra.framework.listener;

import io.pragra.framework.drivermanager.DriverManager;
import io.pragra.framework.utils.CommonUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenShotListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        CommonUtils.captureScreenShot(DriverManager.getDriver(), result.getName(),true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        CommonUtils.captureScreenShot(DriverManager.getDriver(), result.getName(),false);
    }
}
