package MavenTest.GmailMaven;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestReport implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		
		DateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
		String time = formatTime.format(new Date());
		DateFormat formatDate = new SimpleDateFormat("DD/MM/YY");
		String date = formatDate.format(new Date());
		System.out.println("finished on: "+date+" at: "+time);

	}

	@Override
	public void onStart(ITestContext arg0) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

		System.out.println("Test Passed Partially: " + arg0.getName());

	}

	@Override
	public void onTestFailure(ITestResult arg0) {

		System.out.println("Test Failed: " + arg0.getName());

		try {
			new CaptureScreenShot().takeScreenShot(arg0.getName());
		} catch (IOException e) {
			System.out.println("error capturing screen");
		}

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {

		System.out.println("Test Skipped: " + arg0.getName());

	}

	@Override
	public void onTestStart(ITestResult arg0) {

		DateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
		String time = formatTime.format(new Date());
		DateFormat formatDate = new SimpleDateFormat("DD/MM/YY");
		String date = formatDate.format(new Date());
		System.out.println("\nTest Started: " + arg0.getName()+" on: "+date+" at: "+time);

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {

	
		System.out.println("Test Passed: " + arg0.getName());

	}

}
