package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.EventMethods;

public class CompaniesPage extends EventMethods {
	private WebDriver driver;

	public CompaniesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//div[text()='Companies']")
	private WebElement staticCompaniesHeaderElement;

	@FindBy(xpath = "//button[text()='New']")
	private WebElement buttonNewCompany;

	public boolean checkCompaniesPageDisplayed() {
		boolean isFound = false;
		try {
			if (staticCompaniesHeaderElement != null && staticCompaniesHeaderElement.isDisplayed()) {
				isFound = true;
			}
		} catch (NoSuchElementException nse) {
			isFound = false;
		}

		return isFound;
	}

	public void clickNewCompanyButton() {
		click(buttonNewCompany);

	}

}
