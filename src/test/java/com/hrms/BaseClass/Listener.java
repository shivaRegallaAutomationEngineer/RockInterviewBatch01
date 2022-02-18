package com.hrms.BaseClass;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.hrms.utils.CommonMethods;

public class Listener implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {
		// this listener executes when @Test method starts
		System.out.println("Test Started " + result.getName());
		BaseClass.test = BaseClass.report.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed " + result.getName());
		BaseClass.test.pass("Test Case pass " + result.getName());
		try {
			
			BaseClass.test.addScreencastFromPath(CommonMethods.takeScreenShot("passed/" + result.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed " + result.getName());
		BaseClass.test.fail("Test Case Failed " + result.getName());
		try {
			BaseClass.test.addScreenCaptureFromPath(CommonMethods.takeScreenShot("failed/" + result.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}
}
