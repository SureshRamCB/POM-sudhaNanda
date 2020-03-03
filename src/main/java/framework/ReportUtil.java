package framework;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

public class ReportUtil {
	private ExtentReports reports;
	private ExtentTest reportNode;

	public void initializeReport(String testName) {

		UtilityMethods.createFolder(Data.REPORT_FOLDER_PATH);

		String resultFilePath = Data.REPORT_FOLDER_PATH + "\\" + testName + "_" + UtilityMethods.getTimeStamp()
				+ ".html";

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(resultFilePath);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setReportName("Execution results for Test : " + testName);
		htmlReporter.config().enableTimeline(true);

		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
	}

	public void createNodeForTestCase(String testCaseName) {

		reportNode = reports.createTest(testCaseName);

	}

	public void writeReport(Status status, String message) {

		switch (status) {
		case PASS:
			reportNode.log(Status.PASS, message);
			break;
		case FAIL:
			reportNode.log(Status.FAIL, message);
			break;

		case WARNING:
			reportNode.log(Status.WARNING, message);
			break;

		case INFO:
			reportNode.log(Status.INFO, message);
			break;

		default:
			reportNode.log(Status.SKIP, message);
			break;
		}
	}

	public void writeReport(Status status, String message, String imageName, WebDriver driver) {
		try {
			switch (status) {
			case PASS:

				reportNode.log(Status.PASS, message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(captureImage(driver, imageName)).build());

				break;
			case FAIL:
				reportNode.log(Status.FAIL, message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(captureImage(driver, imageName)).build());
				break;

			case WARNING:
				reportNode.log(Status.WARNING, message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(captureImage(driver, imageName)).build());
				break;

			case INFO:
				reportNode.log(Status.INFO, message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(captureImage(driver, imageName)).build());
				break;

			default:
				reportNode.log(Status.SKIP, message, MediaEntityBuilder
						.createScreenCaptureFromBase64String(captureImage(driver, imageName)).build());
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void finalizeReport() {
		reports.flush();
	}

	public String captureImage(WebDriver driver, String imageName) {
		UtilityMethods.createFolder(System.getProperty("user.dir") + "\\Screenshots");
		imageName = imageName + UtilityMethods.getTimeStamp() + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);
		String base64String = ts.getScreenshotAs(OutputType.BASE64);
		try {
			Files.move(src, new File(System.getProperty("user.dir") + "\\Screenshots\\" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return base64String;

	}

}
