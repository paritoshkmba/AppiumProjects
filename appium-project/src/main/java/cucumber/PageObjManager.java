package cucumber;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import core.CoreFunctions;
import galatasaray.pageObjects.CommonPageElements;
import galatasaray.pageObjects.CountryCodeSearchPage;
import galatasaray.pageObjects.DatePicker;
import galatasaray.pageObjects.HomePage;
import galatasaray.pageObjects.SignInPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageObjManager extends CoreFunctions {


	public PageObjManager(AppiumDriver<WebElement> driver) {
		super(driver);
		this.appiumdriver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS)), this);
	}

	protected AppiumDriver<WebElement> appiumdriver;

	// Pages

	private HomePage HomePage;
	private SignInPage signInPage;
	private CommonPageElements common;
	private DatePicker datePicker;
	private CountryCodeSearchPage countryCodePage;

	public HomePage HomePage() {
		return (HomePage == null) ? HomePage = new HomePage(appiumdriver) : HomePage;
	}

	public SignInPage SignInPage() {
		return (signInPage == null) ? signInPage = new SignInPage(appiumdriver) : signInPage;
	}

	public CommonPageElements CommonPageElements() {
		return (common == null) ? common = new CommonPageElements(appiumdriver) : common;
	}
	
	public DatePicker DatePicker() {
		return (datePicker == null) ? datePicker = new DatePicker(appiumdriver) : datePicker;
	}
	
	public CountryCodeSearchPage CountryCodeSearchPage() {
		return (countryCodePage == null) ? countryCodePage = new CountryCodeSearchPage(appiumDriver) : countryCodePage;
	}

}
