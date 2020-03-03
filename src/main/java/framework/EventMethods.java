package framework;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EventMethods {
	private WebDriver driver;

	public EventMethods(WebDriver driver) {
		this.driver = driver;
	}

	public void highlightElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) this.driver;
		jse.executeScript("arguments[0].setAttribute('style', 'border:2px solid red;')", element);
	}

	public boolean verifyElementExists(By by, int timeOutInSeconds) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			wait.pollingEvery(Duration.ofMillis(200));
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public void enter_value_in_text_field(By by, String input) {

	}

	protected void click(WebElement element) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				highlightElement(element);
				element.click();
			} else {
				Assert.assertTrue(false, "element : " + element.toString() + " is either not visible or not enabled.");
			}
		} catch (NoSuchElementException nse) {
			Assert.assertTrue(false, "element : " + element.toString() + " is not found in the page.");
		}

	}

	protected void shortWait() {

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void shortestWait() {

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
