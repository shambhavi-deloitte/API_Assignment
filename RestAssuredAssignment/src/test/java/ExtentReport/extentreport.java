package ExtentReport;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class extentreport{

    static ExtentTest test1;
    static com.relevantcodes.extentreports.ExtentReports report;
    @BeforeTest
    public void initializeReport(){
        report = new ExtentReports(System.getProperty("RestAssuredAssignment.dir")+"ReportToDO.html");
        test1 = report.startTest("test");
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE) {

            test1.log(LogStatus.FAIL, (result.getName()+" Failed"));
        }
        else if(result.getStatus() == ITestResult.SUCCESS){
            test1.log(LogStatus.PASS, (result.getName()+" Successful"));
        }
        else {
            test1.log(LogStatus.SKIP, (result.getName()+" Skipped"));
        }
    }

    @AfterClass
    public static void endTest()
    {
        report.endTest(test1);
        report.flush();
    }
}