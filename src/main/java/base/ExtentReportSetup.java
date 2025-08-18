package base;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportSetup {
	static ExtentReports extent;//Declares a static instance of ExtentReports.
	public static ExtentReports ExtentSetup() {//Defines a static method to configure Extent Reports.
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");//Gets the current date and time in dd-MM-yyyy HH.mm.ss format.
		Date date = new Date();
		String actualDate = sdf.format(date);
		//Creates a dynamic report file name based on the timestamp.
		String reportPath = System.getProperty("user.dir") + "/ExecutionReports/ExecutionReport_" + actualDate + ".html";
		//Creates a new Spark Reporter for HTML-based test reports.
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		//Configures:
		sparkReporter.config().setDocumentTitle("Automation Execution");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Report Name");
		sparkReporter.config().setTimelineEnabled(true);
		//Returns the ExtentReports instance.
		return extent;
		}
}