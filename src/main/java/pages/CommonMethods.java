package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import framework.EventMethods;

public class CommonMethods extends EventMethods {

	private WebDriver driver;

	public CommonMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void navigateToUrl(String url) {

		driver.get(url);

		if (!verifyElementExists(By.name("email"), 20)) {
			Assert.assertTrue(false, "Application is not navigated to Login page.");
		}

	}

}
