package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

// Listener class that generates Extent Report based on test execution
public class ExtentReportManager implements ITestListener {

	// Extent report objects
	public ExtentSparkReporter sparkReporter;   // Responsible for creating HTML report
	public ExtentReports extent;               // Main class to manage the entire report
	public ExtentTest test;                    // Used to log information for individual tests

	String repName; // Name of the generated report file

	// Runs before the suite/test execution starts
	public void onStart(ITestContext testContext) {

		// Create timestamp for report name (format: yyyy.MM.dd.HH.mm.ss)
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		// Dynamic report file name with timestamp
		repName = "Test-Report-" + timeStamp + ".html";

		// Initialize SparkReporter to generate HTML report at location \reports\
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		// Report configuration
		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of the report
		sparkReporter.config().setReportName("opencart Functional Testing");  // Report header
		sparkReporter.config().setTheme(Theme.DARK);                          // Theme: DARK or STANDARD

		// Create ExtentReports object and attach the reporter
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Add general system information to the report
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");

		// Fetch parameters from grid-docker.xml (os and browser)
		String os = testContext.getCurrentXmlTest().getParameter("OS");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		// Fetch included groups (if any) and display in report
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	// Runs when a test case passes
	public void onTestSuccess(ITestResult result) {
		// Create a new test entry in the report
		test = extent.createTest(result.getTestClass().getName());

		// Assign test method groups (categories) for better organization
		test.assignCategory(result.getMethod().getGroups());

		// Log PASS status with test method name
		test.log(Status.PASS,result.getName()+" got successfully executed");
	}

	// Runs when a test case fails
	public void onTestFailure(ITestResult result) {
		// Create a new test entry in the report
		test = extent.createTest(result.getTestClass().getName());

		// Assign test method groups
		test.assignCategory(result.getMethod().getGroups());

		// Log failure status and error message
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			// Capture screenshot using BaseClass utility
			String imgPath = new BaseClass().captureScreen(result.getName());

			// Attach screenshot in the report
			test.addScreenCaptureFromPath(imgPath);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// Runs when a test case is skipped
	public void onTestSkipped(ITestResult result) {
		// Create a new test entry
		test = extent.createTest(result.getTestClass().getName());

		// Assign test method groups
		test.assignCategory(result.getMethod().getGroups());

		// Log SKIP status with reason
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	// Runs after all test execution finishes
	public void onFinish(ITestContext testContext) {

		// Write everything to the report file
		extent.flush();

		// Get path of the generated report
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);

		try {
			// Open the report automatically in default browser
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}


		/*
		 * Code to send report via email (commented out here)
		 *
		 * try {
		 * 	  URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 *
		 * 	  // Create the email message
		 * 	  ImageHtmlEmail email = new ImageHtmlEmail();
		 * 	  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * 	  email.setHostName("smtp.googlemail.com");
		 * 	  email.setSmtpPort(465);
		 * 	  email.setAuthenticator(new DefaultAuthenticator("youremail@gmail.com","password"));
		 * 	  email.setSSLOnConnect(true);
		 * 	  email.setFrom("youremail@gmail.com"); //Sender
		 * 	  email.setSubject("Test Results");
		 * 	  email.setMsg("Please find Attached Report....");
		 * 	  email.addTo("receiveremail@gmail.com"); //Receiver
		 * 	  email.attach(url, "extent report", "please check report...");
		 * 	  email.send(); // send the email
		 * }
		 * catch(Exception e) { e.printStackTrace(); }
		 */

	}

}
