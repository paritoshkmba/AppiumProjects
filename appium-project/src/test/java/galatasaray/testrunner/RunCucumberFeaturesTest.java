package galatasaray.testrunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.openqa.selenium.InvalidArgumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.mdb.android.Android;

import core.AppiumInstance;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.ConfigReader;
import utilities.EmulatorControls;

@CucumberOptions(plugin = { 
		"pretty", "html:./reports/cucumber-reports/cucumber-pretty-html-report",
		"json:./reports/cucumber-reports/CucumberTestReport.json",
		"usage:./reports/cucumber-reports/cucumber-usage.json",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"
		}, 
monochrome = true, 
strict = true, 
dryRun = false, 
features = {
				"src/test/resources/features/galatasaray" }, 
glue = { "galatasaray.stepdefinition" }, 
tags = { "@home_4" })

public class RunCucumberFeaturesTest extends AbstractTestNGCucumberTests {

	static {
		System.setProperty("current.date.time", new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date()));
		System.setProperty("platformName", "Android");
	}
	
	@org.testng.annotations.BeforeClass
	public void setUpBeforeClass() {
		EmulatorControls.launchEmulator("emulator-5554");
	}


	@Parameters({ "systemPort", "appiumPort" })
	@BeforeMethod(alwaysRun = true)
	public void setUpBeforeTest(@Optional String systemPort, @Optional String appiumPort) {
		System.out.println("INSIDE BEFORE METHOD " + this.getClass().getCanonicalName());
		List<DeviceDetails> androidDevices = new Android().getDevices();

		System.out.println("deviceName: " + androidDevices.get(0).getDeviceName() + "\ndeviceUDID: "
				+ androidDevices.get(0).getUdid() + "\nplatformVersion: " + androidDevices.get(0).getOsVersion()
				+ " \nsystemPort: " + systemPort);

		AppiumInstance.getInstance().setUpAppiumDriver(androidDevices.get(0), systemPort, appiumPort);
	}

	@AfterMethod
	public void tearDownTest() {
		System.out.println("INSIDE AFTER METHOD " + this.getClass().getCanonicalName());

		/* removing uiautomator app */
		try {
			AppiumInstance.getAppiumDriver().removeApp("io.appium.uiautomator2.server");
			AppiumInstance.getAppiumDriver().removeApp("io.appium.uiautomator2.server.test");
			AppiumInstance.getAppiumDriver().removeApp(ConfigReader.getProperty("appPackage"));
		} catch (InvalidArgumentException e1) {
		}

		/* quitting appium driver */
		AppiumInstance.getInstance().quitAppiumDriver();
		try {
			Process p = Runtime.getRuntime().exec("adb kill-server");
			p.destroy();
		} catch (IOException e) {
		}
	}

}
