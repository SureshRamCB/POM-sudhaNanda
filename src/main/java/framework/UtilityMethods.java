package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class UtilityMethods {

	/**
	 * To close all the running browsers for clean up task.Should be called before
	 * the driver is initialized
	 * 
	 * @param none
	 * @return none
	 */
	public static void closeBrowsers() {

		String[] allProcesses = { "chrome.exe", "firefox.exe", "MicrosoftEdge.exe", "IExplore.exe", "chromedriver.exe",
				"geckodriver.exe", "MicrosoftWebDriver.exe", "IEDriverServer.exe" };

		for (String processName : allProcesses) {

			try {
				Runtime.getRuntime().exec("taskkill  /F  /IM " + processName);
			} catch (IOException e) {
				System.out.println("process : " + processName + " could not be terminated.");
			}

		}

	}

	/**
	 * Reads all the keys and values of given properties file into HasMap
	 * 
	 * @param propertyFilePath path of the properties file where it is located.
	 * @return HashMap<String,String>
	 */

	public static HashMap<String, String> getPropertiesData(String propertyFilePath) {
		HashMap<String, String> propertyData = new HashMap<String, String>();
		try {
			FileInputStream fis = new FileInputStream(propertyFilePath);
			Properties properties = new Properties();
			properties.load(fis);
			Set<Object> allProps = properties.keySet();

			for (Object prop : allProps) {
				propertyData.put(prop.toString(), properties.getProperty(prop.toString()));
			}

		} catch (IOException ioe) {
			System.out.println("Exception while reading properties file : " + propertyFilePath);
			System.exit(0);
		}

		return propertyData;

	}

	public static String getTimeStamp() {
		String timeStamp = "";

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int hour = c.get(Calendar.HOUR);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		timeStamp = timeStamp + month + day + hour + minute + second;

		return timeStamp;
	}

	public static void createFolder(String foldPath) {
		File f = new File(foldPath);
		if (!f.exists())
			f.mkdirs();
	}

}
