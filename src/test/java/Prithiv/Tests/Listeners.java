package Prithiv.Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import prithiv.resorces.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {
	 ExtentTest test;
	ExtentReports extent = ExtentReportNG.getExtentReport();
	
	 ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
	  test = extent .createTest(result.getMethod().getMethodName());
	  extentTest.set(test);
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "PASSED");
		
	}
		
	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("diver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	String path = null;
	try {
		path = getScreenshot(result.getMethod().getMethodName(), driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
	
	}
}
