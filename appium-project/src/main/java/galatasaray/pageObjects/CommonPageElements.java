package galatasaray.pageObjects;

import org.openqa.selenium.WebElement;

import businessException.CouldNotClickException;
import cucumber.PageObjManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CommonPageElements extends PageObjManager {

	public CommonPageElements(AppiumDriver<WebElement> driver) {
		super(driver);
	}

	@AndroidFindBy(id = "alertTitle")
	private WebElement alert;

	@AndroidFindBy(id = "android:id/message")
	private WebElement message;

	@AndroidFindBy(id = "button1")
	private WebElement accept;

	@AndroidFindBy(id = "bt_next")
	private WebElement btn_next;

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Evet')]")
	private WebElement btn_yes;

	@AndroidFindBy(id = "errorMessage")
	private WebElement error_message;

	public boolean isAlertDisplayed() {
		return waitTillElementDisplayed(alert, "alert");
	}

	public String getAlertText() {
		return getText(message, "alert message");
	}

	public void clickOK() {
		click(accept, "acceptbuttononalert");
	}

	public boolean isNextBtnDisplayed() {
		return waitTillElementDisplayed(btn_next, "next button");
	}

	public boolean isNextBtnEnabled() {
		return isElementEnabled(btn_next, "next button");
	}

	public boolean isYesBtnDisplayed() {
		return waitTillElementDisplayed(btn_yes, "yes button");
	}

	public void clickOnYesBtn() {
		if (isYesBtnDisplayed()) {
			click(btn_yes, "yes button");
		}
	}

	public void clickNextBtn() {
		if (isNextBtnDisplayed() && isNextBtnEnabled()) {
			click(btn_next, "next button");
		} else {
			throw new CouldNotClickException("next button is not clickable");
		}
	}

	public boolean isErrorMessageDisplayed() {
		return waitTillElementDisplayed(error_message, "error message");
	}

	public String getErrorMessageText() {
		if (isErrorMessageDisplayed())
			return getText(error_message, "error message");
		return "error message text could not be fetched";
	}

}
