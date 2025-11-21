package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import helper.Utility;

public class ExtentManager {

	private static ExtentReports extent;

	// Thread-safe singleton for ExtentReports
	public synchronized static ExtentReports getInstance() {
		if (extent == null) {
			extent = createInstance();
		}
		return extent;
	}

	private static ExtentReports createInstance() {
		String reportPath = System.getProperty("user.dir") + "/reports/Automation_" + Utility.getCurrentTime() + ".html";

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Automation Report");
		sparkReporter.config().setDocumentTitle("Sprint 1 Report");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		return extent;
	}
}
