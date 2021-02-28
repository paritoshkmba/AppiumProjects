package core;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CoreFunctions extends WaitHelper {


	public CoreFunctions(AppiumDriver<WebElement> driver) {
		super(driver);
	}

	public static Logger log = Logger.getLogger(CoreFunctions.class);	

	public void click(WebElement element, String elementName) {

		log.info("Clicking on " + elementName);
		ExtentCucumberAdapter.addTestStepLog("Clicking on " + elementName);
		try {
			WaitTillElementIsClickable(element).click();
			log.info("Successfully clicked on " + elementName);
			ExtentCucumberAdapter.addTestStepLog("Successfully clicked on " + elementName);
		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			log.info("Could not click on " + elementName);
			ExtentCucumberAdapter
					.addTestStepLog("Could not click on " + elementName + " exception occured " + e.getMessage());
			Assert.fail("Could not click on " + elementName + " due to " + e.getMessage());
		}
	}

	/**
	 * returns element text
	 * 
	 * @param element
	 * @param elementName
	 * @return {@link String}
	 */
	public String getText(WebElement element, String elementName) {

		log.info("Getting Text for " + elementName);
		ExtentCucumberAdapter.addTestStepLog("Getting Text for " + elementName);

		String text = "";
		try {
			text = element.getText().trim();
			log.info("Text is:" + text);
			ExtentCucumberAdapter.addTestStepLog("Text is:" + text);

		} catch (Exception e) {
			log.info("Could not get text || exception occured " + e.getMessage());
			e.printStackTrace();
		}
		return text;
	}

	public boolean isElementSelected(WebElement element, String name) {

		String status;
		try {
			log.info("Verifying if " + name + " is selected");
			ExtentCucumberAdapter.addTestStepLog("Verifying if " + name + " is selected");
			status = ((MobileElement) element).getAttribute("selected");

			if (status.equalsIgnoreCase("true")) {
				log.info(name + " is selected");
				ExtentCucumberAdapter.addTestStepLog(name + " is selected");
				return true;
			} else {
				log.info("" + name + " is not selected");
				ExtentCucumberAdapter.addTestStepLog(name + " is not selected");
				return false;
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isElementEnabled(WebElement element, String name) {

		String status;
		try {
			log.info("Verifying if " + name + " is enabled");
			ExtentCucumberAdapter.addTestStepLog("Verifying if " + name + " is enabled");
			status = ((MobileElement) element).getAttribute("enabled");

			if (status.equalsIgnoreCase("true")) {
				log.info(name + " is enabled");
				ExtentCucumberAdapter.addTestStepLog(name + " is enabled");
				return true;
			} else {
				log.info("" + name + " is not enabled");
				ExtentCucumberAdapter.addTestStepLog(name + " is not enabled");
				return false;
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param scenarioName
	 * @return outputFileLocation
	 */
	public synchronized String takeFailedScreenshot(String scenarioName) {

		scenarioName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "");

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String outputFileLocation = System.getProperty("user.dir") + "\\screenshots\\FailedScreenshots\\" + scenarioName
				+ "_" + dateFormat.format(new Date()) + ".png";
		try {
			log.info("Taking Failed Screenshot ");
			File srcFile = ((TakesScreenshot) AppiumInstance.getAppiumDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(outputFileLocation));
			log.info("Failed Screenshot saved | output file : " + outputFileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputFileLocation;
	}
	

	public void enterKeys(WebElement element, String keysToSend) {
		element.sendKeys(keysToSend);
	}

	public void enterNumericKeysOnAndroid(String keysToSend) {
		for (char temp : keysToSend.toCharArray())
			enterKeys(Integer.parseInt(String.valueOf(temp)));
	}

	private void enterKeys(int keys) {

		switch (keys) {
		case 1:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
			break;
		case 2:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			break;
		case 3:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_3));
			break;
		case 4:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			break;
		case 5:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_5));
			break;
		case 6:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_6));
			break;
		case 7:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_7));
			break;
		case 8:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_8));
			break;
		case 9:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_9));
			break;
		case 0:
			((AndroidDriver<WebElement>) appiumDriver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
			break;

		default:
			log.info("Invalid Keys " + keys);
			break;
		}

	}

	public void swipeByCoordinates(int xStart, int yStart, int xEnd, int yEnd) {

		try {
			log.info("scrolling window by coordinates : " + "xStart:" + xStart + ",yStart:" + yStart + ",xEnd:" + xEnd
					+ ",yEnd:" + yEnd);
			new TouchAction(AppiumInstance.getAppiumDriver()).press(new PointOption().withCoordinates(xStart, yStart))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
					.moveTo(new PointOption().withCoordinates(xEnd, yEnd)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void hideKeyboard() {
		
		if (((AndroidDriver<WebElement>) appiumDriver).isKeyboardShown()) {
			((AndroidDriver<WebElement>) appiumDriver).hideKeyboard();
		}
	}
	
	public void navigateBack() {
		
		((AndroidDriver<WebElement>) appiumDriver).navigate().back();
	}

}
