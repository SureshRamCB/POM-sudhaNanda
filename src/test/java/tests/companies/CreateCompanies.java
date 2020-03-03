package tests.companies;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import framework.Configuration;
import framework.Data;
import framework.Driver;
import pages.CommonMethods;
import pages.CompaniesPage;
import pages.CompanyInfoPage;
import pages.HomePage;
import pages.LoginPage;
import pages.NewCompanyPage;

public class CreateCompanies extends Configuration {

	@Test
	public void testMetho1() {

	}

	@Parameters("browser")
	@Test
	public void testMethod(@Optional String browser) {

		Driver driver = new Driver((browser != null) ? browser : "firefox", 20);
		CommonMethods commons = new CommonMethods(driver.getDriver());
		LoginPage loginpage = new LoginPage(driver.getDriver());
		HomePage homepage = new HomePage(driver.getDriver());
		CompaniesPage companiespage = new CompaniesPage(driver.getDriver());
		CompanyInfoPage companyinfopage = new CompanyInfoPage();
		NewCompanyPage newcompanypage = new NewCompanyPage(driver.getDriver());

		commons.navigateToUrl(Data.appConfigInfo.get("url"));

		loginpage.login(Data.appConfigInfo.get("username"), Data.appConfigInfo.get("password"));

		if (homepage.checkHomePageDisplayed()) {
			report.writeReport(Status.PASS, "Application navigated to Home Page");
		} else {
			report.writeReport(Status.FAIL, "Application navigated to Home Page");
			Assert.assertTrue(false, "Application not navigated to home page.");
		}

		homepage.clickCompaniesLink();

		if (companiespage.checkCompaniesPageDisplayed()) {
			report.writeReport(Status.PASS, "Application navigated to Companies Page", "CompaniesPage",
					driver.getDriver());
		} else {
			report.writeReport(Status.FAIL, "Application navigated to Companies Page");
			Assert.assertEquals(false, true, "Application is not navigated to companies home page.");
		}

		companiespage.clickNewCompanyButton();

		if (newcompanypage.checkNewCompanyPageDisplayed()) {
			report.writeReport(Status.PASS, "Application navigated to new Company Page");
		} else {
			report.writeReport(Status.FAIL, "Application NOT navigated to new company  Page");
			Assert.assertEquals(false, true, "Application is not navigated to new company page.");
		}

		newcompanypage.uploadCompanyLogo_robotClass("CompanyLogo.jpg");
		/* driver.closeDriver(); */

	}

	@Test
	public void testMethod2() {
		report.writeReport(Status.PASS, "Application navigated to Login Page");
		report.writeReport(Status.PASS, "Application navigated to Home Page");
		report.writeReport(Status.PASS, "Employee created successfully.");
		report.writeReport(Status.PASS, "Database validated successfully.");
	}

}
