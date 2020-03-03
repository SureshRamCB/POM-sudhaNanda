package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import framework.EventMethods;

public class LoginPage extends EventMethods {
	private WebDriver driver;

	@FindBy(name = "email")
	private WebElement inputUserName;

	@FindBy(name = "password")
	private WebElement inputPassword;

	@FindBy(xpath = "//div[text()='Login']")
	private WebElement buttonLogin;

	@FindAll({ @FindBy(name = "email"), @FindBy(name = "password"), @FindBy(tagName = "input") })
	List<WebElement> elemCol;

	@FindBys({ @FindBy(xpath = "//div[@class='ui stacked segment']"), @FindBy(tagName = "i") })
	List<WebElement> elemCol1;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void login(String userName, String passWord) {
		inputUserName.sendKeys(userName);
		inputPassword.sendKeys(passWord);
		System.out.println("Total elements : " + elemCol.size());
		buttonLogin.click();
	}

}
