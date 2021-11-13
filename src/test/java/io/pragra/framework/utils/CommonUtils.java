package io.pragra.framework.utils;

import io.pragra.framework.config.Config;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    private static void createScreenShotDirs(){
        Path pass = Paths.get(Config.getProperty("screenshot.pass.dir"));
        Path fail = Paths.get(Config.getProperty("screenshot.fail.dir"));
        try {
            Files.createDirectories(pass);
            Files.createDirectories(fail);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Path generateScreenshotFileName(String testName, boolean pass) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( Config.getProperty("timestamp") );
        String format = simpleDateFormat.format(new Date());
        String fileName = Config.getProperty("screenshot.file.pattern");
        fileName = fileName.replace("testname?", testName);
        fileName = fileName.replace("timestamp?", format);
        if (pass) {
            return Paths.get(Config.getProperty("screenshot.pass.dir"),fileName);
        }
        return Paths.get(Config.getProperty("screenshot.fail.dir"),fileName);
    }

    public static String captureScreenShot(WebDriver driver, String testCaseName, boolean pass){
        createScreenShotDirs();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path fileName = generateScreenshotFileName(testCaseName, pass);
        try {
            Files.copy(screenshot.toPath(),fileName );
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return fileName.toString();
    }


}
