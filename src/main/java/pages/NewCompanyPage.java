package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import framework.Data;
import framework.EventMethods;

public class NewCompanyPage extends EventMethods {

	private WebDriver driver;

	public NewCompanyPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(name = "image")
	private WebElement textcompanyLogo;

	@FindBy(xpath = "//div[text()='Create new Company']")
	private WebElement staticNewCompanyHeaderElement;

	public boolean checkNewCompanyPageDisplayed() {
		boolean isFound = false;
		if (staticNewCompanyHeaderElement != null && staticNewCompanyHeaderElement.isDisplayed()) {
			isFound = true;
		}

		return isFound;
	}

	public void uploadCompanyLogo(String imageName) {
		imageName = Data.DATA_FOLDER_PATH + "Images\\" + imageName;
		click(textcompanyLogo);
		shortWait();

		Screen screen = new Screen();
		try {
			Pattern inputFilePathElement = new Pattern(Data.OBJECT_IMAGE_PATH + "textFilePathOpenDialog.png");
			Pattern openButton = new Pattern(Data.OBJECT_IMAGE_PATH + "OpenButton.png");
			screen.type(inputFilePathElement, imageName);
			shortestWait();
			screen.click(openButton);
		} catch (FindFailed e) {
			e.printStackTrace();
		}

	}

	public void uploadCompanyLogo_robotClass(String imageName) {
		imageName = Data.DATA_FOLDER_PATH + "Images\\" + imageName;
		click(textcompanyLogo);
		shortestWait();

		try {

			Runtime.getRuntime().exec(
					"E:\\SeleniumFrameworks\\PageObjectModel\\src\\test\\resources\\AutoItScripts\\UploadImage.exe");

			Robot robot = new Robot();
			StringSelection stringSelection = new StringSelection(imageName);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			shortestWait();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (IOException | AWTException e) {
			e.printStackTrace();
		}

	}

}
