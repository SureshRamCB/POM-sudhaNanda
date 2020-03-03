package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.EventMethods;

public class HomePage extends EventMethods {
	private WebDriver driver;

	@FindBy(xpath = "//a/span[text()='Home']")
	private WebElement linkHome;

	@FindBy(xpath = "//a/span[text()='Companies']")
	private WebElement linkCompanies;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public boolean checkHomePageDisplayed() {
		boolean isFound = false;
		if (linkHome != null && linkHome.isDisplayed()) {
			isFound = true;
		}

		return isFound;
	}

	public void clickCompaniesLink() {
		click(linkCompanies);

	}
}
