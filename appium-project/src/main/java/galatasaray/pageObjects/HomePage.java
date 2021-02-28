package galatasaray.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import businessException.CouldNotClickException;
import cucumber.PageObjManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends PageObjManager {

	public HomePage(AppiumDriver<WebElement> driver) {
		super(driver);
	}

	@AndroidFindBy(id = "avatar")
	private WebElement profilePicture;

	@AndroidFindBy(id = "title")
	private WebElement profileName;

	@AndroidFindBy(id = "progress")
	private WebElement progressBar;

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'logInButton') and contains(@text,'Oturum Aç')]")
	private WebElement btn_login;

	@AndroidFindBy(id = "text")
	private List<WebElement> textOnMessageCard;

	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[contains(@resource-id, 'answers')]//android.widget.Button")
	private List<WebElement> autoMessgaeCard;

	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[contains(@resource-id, 'recyclerView_players')]//following::android.widget.TextView[contains(@resource-id, 'name')]")
	private List<WebElement> nameOfPlayers;

	@AndroidFindBy(id = "firstNameText")
	private WebElement inputFirstName;

	@AndroidFindBy(id = "lastNameText")
	private WebElement inputLastName;

	@AndroidFindBy(id = "gender_picker_container")
	private WebElement gender;

	public boolean isProfilePictureDisplayed() {
		return waitTillElementDisplayed(profilePicture, "profilepicture");
	}

	public boolean isProfileNameDisplayed() {
		return waitTillElementDisplayed(profileName, "profilename");
	}

	public boolean isLoginBtnDisplayed() {
		return waitTillElementDisplayed(btn_login, "loginbutton");
	}

	public void clickOnLoginBtn() {

		click(btn_login, "loginbutton");

	}

	public boolean isAutoMessageCardDisplayed() {
		if (autoMessgaeCard.isEmpty())
			return false;
		return true;
	}

	public void clickOnMessageCard(String message) {
		if (isAutoMessageCardDisplayed()) {
			autoMessgaeCard.stream().filter(i -> i.getText().contains(message)).findAny().get().click();
		}
	}

	public boolean isTextDisplayedAsCardMessage(String text) {

		waitTillElementDisplayed(textOnMessageCard.get(0), "textOnMessageCard");

		List<WebElement> list = textOnMessageCard.stream().filter(i -> i.getText().contains(text))
				.collect(Collectors.toList());

		if (list.isEmpty())
			return false;
		return true;
	}

	public void selectPlayer(String playerName) {

		nameOfPlayers.stream().filter(i -> i.getText().contains(playerName)).findAny().get().click();
	}

	public void enterFirstAndLastName(String firstName, String lastName) {

		if (waitTillElementDisplayed(inputFirstName, "firstName")
				&& waitTillElementDisplayed(inputLastName, "lastName")) {

			enterKeys(inputFirstName, firstName);
			enterKeys(inputLastName, lastName);

		}
	}

	public boolean isGenderDisplayed() {
		return waitTillElementDisplayed(gender, "gender");
	}

	public void selectGender(String gender) {
		// TODO

		swipeByCoordinates(this.gender.getLocation().getX(), this.gender.getLocation().getY(),
				this.gender.getLocation().getX(), this.gender.getLocation().getY() - 300);
	}

}
