package qatarairways.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String filePath = System.getProperty("user.dir")+"\\\\reports\\\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setReportName("Web UI Automation");
		reporter.config().setDocumentTitle("Web UI Test Extent Report");
		
		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Newton");
		return report;
		
	}

}
