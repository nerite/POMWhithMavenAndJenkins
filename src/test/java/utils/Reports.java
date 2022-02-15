package utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public ExtentTest test;
	public String testCaseName, testCaseDec, author, category, device;
	
	
	@BeforeSuite
	public void startReport() {
		spark = new ExtentSparkReporter("./reports/result.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
				
	}
	
	@BeforeClass
	public void startTestCase() {
		test = extent.createTest(testCaseName, testCaseDec);
		test.assignAuthor(author);
		test.assignCategory(category);
		test.assignDevice(device);
	}
	
	public void reportStep(String desc, String status) {
		if(status.equals("PASS")) {
			test.pass(desc);
		}else if(status.equals("FAIL")) {
			test.fail(desc);
			throw new RuntimeException();
		}
		
	}
	
	@AfterSuite
	public void endReport() {
		extent.flush();
	}

}
