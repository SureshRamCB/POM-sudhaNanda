package framework;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Configuration {

	public ReportUtil report = new ReportUtil();

	@BeforeSuite
	public void beforeSuite() {
		String appConfigFilePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\Config\\EnvInfo.properties";
		Data.appConfigInfo = UtilityMethods.getPropertiesData(appConfigFilePath);
	}

	@BeforeTest
	public void beforeTest(ITestContext testContext) {
		report.initializeReport(testContext.getCurrentXmlTest().getName());
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		UtilityMethods.closeBrowsers();
		report.createNodeForTestCase(method.getName());
	}

	@AfterTest
	public void afterTest() {
		report.finalizeReport();
	}
}
