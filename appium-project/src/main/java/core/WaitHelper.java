package core;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import utilities.ConfigReader;

public class WaitHelper {

	private static Logger log = Logger.getLogger(WaitHelper.class);
	protected AppiumDriver<WebElement> appiumDriver;
	protected static final long TIMEOUT_IN_SECONDS = Long.parseLong(ConfigReader.getProperty("timeout"));
	private WebDriverWait wait;

	public WaitHelper(AppiumDriver<WebElement> driver) {

		this.appiumDriver = driver;
		wait = new WebDriverWait(this.appiumDriver, TIMEOUT_IN_SECONDS);
	}

	public WebElement WaitTillElementIsClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public boolean waitTillElementDisplayed(WebElement element, String name) {
		log.info("Waiting for " + name + " to display");

		for (int i = 0; i < TIMEOUT_IN_SECONDS; i++) {
			if (element.isDisplayed()) {
				log.info(name + " is displayed");
				return true;
			} else {
				waitForSecs(1);
			}

		}
		return false;
	}


	public void waitTillActivity(int timeout, String desiredActivity) {
		long startTime = System.currentTimeMillis();
		log.info("waiting for main activity to load");
		while (System.currentTimeMillis() - startTime < timeout)
			if (((AndroidDriver<WebElement>) appiumDriver).currentActivity().equals(desiredActivity))
				break;
	}

	private void waitForSecs(int secs) {
		try {
			log.info("waiting for " + secs + " seconds");
			Thread.sleep(secs * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
