import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utility.EnvironmentSetup;


public class ExampleTest {
    private WebDriver driver;
    private ExtentReports report;
    private ExtentTest logger;

    @BeforeClass
    public void startBrowser() {
        driver = new EnvironmentSetup().initWebDriver();
    }

    @BeforeTest
    public void setup(ITestContext ctx) {
        TestRunner runner = (TestRunner) ctx;
        runner.setOutputDirectory("/test-output");
    }

    @BeforeMethod
    public void setup() {
        report = EnvironmentSetup.getExtents();
    }

    @Test(dataProvider = "testdata")
    public void testMethod(){
        logger = report.startTest("Check ").assignCategory("Logon Tests"); //your test should have a uniq name
        //make your tests here
        logger.log(LogStatus.PASS, "Validation is verified"); //log success
    }
}
