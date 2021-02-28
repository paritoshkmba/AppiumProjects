package galatasaray.pageObjects;

import org.openqa.selenium.WebElement;

import businessException.CouldNotClickException;
import cucumber.PageObjManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SignInPage extends PageObjManager {

	public SignInPage(AppiumDriver<WebElement> driver) {
		super(driver);
	}

	@AndroidFindBy(id = "et_phone")
	private WebElement input_phone;

	@AndroidFindBy(id = "et_otp")
	private WebElement input_OTP;

	@AndroidFindBy(id = "countryCode")
	private WebElement input_countryCode;

	public boolean isPhoneNoInputDisplayed() {
		return waitTillElementDisplayed(input_phone, "inputphoneno");
	}

	public boolean isOTPInputDisplayed() {
		return waitTillElementDisplayed(input_OTP, "inputOTP");
	}

	public void enterPhoneNo(String phoneNo) {
		if (isPhoneNoInputDisplayed()) {
			click(input_phone, "input phone no");
			enterNumericKeysOnAndroid(phoneNo);
		}
	}

	public void enterOTP(String otp) {
		if (isOTPInputDisplayed()) {
			click(input_OTP, "input OTP no");
			enterNumericKeysOnAndroid(otp);
		}
	}

	public void clickOnCountryCode() {
//		navigateBack();
		hideKeyboard();

		if (waitTillElementDisplayed(input_countryCode, "country code"))
			click(input_countryCode, "country code");
		else {
			throw new CouldNotClickException("Country code is not visible");
		}
	}

	public String getSelectedCountryCode() {
		return getText(input_countryCode, "country code");
	}

}
