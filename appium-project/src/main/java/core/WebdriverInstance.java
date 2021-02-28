package core;

import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;

public class WebdriverInstance {
	
	private WebdriverInstance() {}

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final Logger log = Logger.getLogger(WebdriverInstance.class);

	// browserstack

	private static final String AUTOMATE_USERNAME = ConfigReader.getProperty("browserStackUsername");
	private static final String AUTOMATE_ACCESS_KEY = ConfigReader.getProperty("browserStackPassword");
	private static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";

	public static WebDriver setupDriver(String browserName) throws Exception {

		if (browserName == null) {
			throw new Exception("Browser name is null");
		}

		switch (browserName) {

		case BrowserType.FIREFOX:
			log.info("Opening the browser : Firefox");
			
			//set firefox profile
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile myProfile = profile.getProfile("selenium");
			FirefoxOptions firefoxoptions = new FirefoxOptions();
			firefoxoptions.setProfile(myProfile);
			
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver(firefoxoptions));
			break;
		case BrowserType.CHROME:
			log.info("Opening the browser : Chrome");
			
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addArguments("--disable-notifications");
			chromeoptions.addArguments("--start-maximized");
			
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(chromeoptions));
			break;
		case BrowserType.SAFARI:
			log.info("Opening the browser : Safari");
			// TODO
			break;
		case BrowserType.EDGE:
			log.info("Opening the browser : Edge");
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			break;

		default:
			throw new Exception("Browser name is not correct");
		}

		return driver.get();
	}

	public static WebDriver setupBrowserStackDriver(String platform, String browserName) throws Exception {
		log.info("Setting up BrowserStack Driver on " + browserName);
		if (browserName == null) {
			throw new Exception("Browser name is null");
		}
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os_version", "10");
		caps.setCapability("resolution", "1920x1080");
		caps.setCapability("browser", browserName);
		caps.setCapability("browser_version", "latest");
		caps.setCapability("os", "Windows");
		caps.setCapability("name", "quality's Cross Browser Test");
		caps.setCapability("browserstack.debug", true);
		driver.set(new RemoteWebDriver(new URL(URL), caps));
		return driver.get();
	}

	public static WebDriver getDriver() {
		System.out.println("getting driver" + driver.get());
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			log.info("Closing the browser");
			driver.get().quit();
		}
		driver.remove();
	}

}
