package framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	private WebDriver driver;

	public Driver(String browserName, int implicitTimeOut) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			this.driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			this.driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			this.driver = new EdgeDriver();
			break;

		case "ie":
			WebDriverManager.iedriver().setup();
			this.driver = new InternetExplorerDriver();
			break;

		default:
			Assert.assertTrue(false, "The browser : " + browserName
					+ " is invalid. Browser must be given in ('chrome','firefox','ie','edge)");
			break;
		}

		this.driver.manage().timeouts().implicitlyWait(implicitTimeOut, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public void closeDriver() {
		try {
			driver.quit();
		} catch (WebDriverException e) {

		}
	}
}
