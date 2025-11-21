package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import browserFactory.BrowserFactory;
import helper.Utility;

public class ExtentTestNGITestListener implements ITestListener {

	// Singleton ExtentReports instance
	private static final ExtentReports extent = ExtentManager.getInstance();

	// Thread-safe ExtentTest instance for parallel execution
	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String description = result.getMethod().getDescription();

		ExtentTest test = (description != null) ?
				extent.createTest(testName, description) : extent.createTest(testName);

		testThread.set(test);
		test.info("Test Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		WebDriver driver = BrowserFactory.getBrowserInstance();
		try {
			if (driver != null) {
				String base64 = Utility.captureScreenshotInBase64(driver);
				testThread.get().pass("Test Passed",
						MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
			} else {
				testThread.get().pass("Test Passed (No WebDriver instance)");
			}
		} catch (Exception e) {
			testThread.get().pass("Test Passed (Screenshot not captured: " + e.getMessage() + ")");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = BrowserFactory.getBrowserInstance();
		try {
			if (driver != null) {
				String base64 = Utility.captureScreenshotInBase64(driver);
				testThread.get().fail(result.getThrowable(),
						MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
			} else {
				testThread.get().fail(result.getThrowable());
			}
		} catch (Exception e) {
			testThread.get().fail("Test Failed (Screenshot not captured: " + e.getMessage() + ")");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		WebDriver driver = BrowserFactory.getBrowserInstance();
		try {
			if (driver != null) {
				String base64 = Utility.captureScreenshotInBase64(driver);
				testThread.get().skip("Test Skipped: " + result.getThrowable(),
						MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
			} else {
				testThread.get().skip("Test Skipped: " + result.getThrowable());
			}
		} catch (Exception e) {
			testThread.get().skip("Test Skipped (Screenshot not captured: " + e.getMessage() + ")");
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Optional: implement if needed
	}

	@Override
	public void onStart(ITestContext context) {
		// Optional: implement if needed
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush(); // Write report to file
	}
}
