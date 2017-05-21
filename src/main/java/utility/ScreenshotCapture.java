package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenshotCapture {
    public static String captureScreenshot(final WebDriver driver, final String screenshotName) {

        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(Calendar.getInstance().getTime());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = "\\Report\\" + timeStamp + screenshotName + ".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);

            return dest;
        } catch (Exception e) {

            System.err
             .println("Exception while taking screenshot " + e.getMessage());
        }
        return screenshotName;
    }
}
