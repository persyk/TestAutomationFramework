package utility;

import com.relevantcodes.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EnvironmentSetup {
    private static int DEFAULT_WAIT = 5;

    private static String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

    private static ExtentReports extentReports = new ExtentReports("\\Report\\name_of_report_" + currentDate + ".html"); //set path to the report

    public static String getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(String currentDate) {
        EnvironmentSetup.currentDate = currentDate;
    }

    public static ExtentReports getExtents() {
        if (extentReports != null) {
            return extentReports;
        } else {
            return new ExtentReports("\\Report\\name_of_report_" + currentDate + ".html");
        }
    }


    @DataProvider(name = "testdata")
    public final Object[][] data() throws Exception {
        Map<String, String[]> dataSet = new TestDataParser().getData();
        for (Map.Entry<String, String[]> entry : dataSet.entrySet()) {
            for (int i = 0; i < entry.getValue().length; i++) {
                String[] mass = entry.getValue();
            }
        }
        return new Object[][]{{dataSet}};
    }


    public final WebDriver initWebDriver() {
        Map<String, String[]> dataSet = new TestDataParser().getData();
        System.setProperty("wdm.gitHubTokenName", "driverManager");
        System.setProperty("wdm.gitHubTokenSecret", "d9409dde972dc4ebbdde01cee8d4f657b11f734c");
        String browser = dataSet.get("browser")[0];
        System.out.println(browser);
        WebDriver result = null;
        if (browser.equalsIgnoreCase("chrome")) {

            ChromeDriverManager.getInstance().setup();
            result = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("ie")) {
            InternetExplorerDriverManager.getInstance().setup();
            DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); //also need to switch off Protected Mode for all zones in IE
            result = new InternetExplorerDriver(ieCapabilities);

            /*
            * I updated iexplore.exe and explorer.exe DWORD values with 0 in registry:
                HKEY_CURRENT_USER\Software\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_HTTP_USERNAME_PASSWORD_DISABLE
                HKEY_CURRENT_USER\Software\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_HTTP_USERNAME_PASSWORD_DISABLE
                so IE support basic https login
             */
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxDriverManager.getInstance().setup();
            result = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeDriverManager.getInstance().setup();
            result = new EdgeDriver();
        }
        result.manage().window().maximize();
        result.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        return result;
    }

}