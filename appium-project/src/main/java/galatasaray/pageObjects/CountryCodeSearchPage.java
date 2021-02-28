package galatasaray.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;

import businessException.CouldNotEnterTextException;
import cucumber.PageObjManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CountryCodeSearchPage extends PageObjManager {

	public CountryCodeSearchPage(AppiumDriver<WebElement> driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='İptal Et']")
	private WebElement btn_Cancel;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Ara']")
	private WebElement input_Search;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout//android.widget.TextView")
	private List<WebElement> searchView;

	public void enterSearchKeyword(String keyword) {
		if (waitTillElementDisplayed(input_Search, "input search"))
			enterKeys(input_Search, keyword);
		else
			throw new CouldNotEnterTextException("could not enter text on input search");
	}
	
	public void clickOnSearchResults(String keyword) {
		
		WebElement element = searchView.stream()
							.filter(i -> getText(i, "search keyword").equals(keyword))
							.findAny()
							.get();
		
		click(element, keyword);
	}

}
