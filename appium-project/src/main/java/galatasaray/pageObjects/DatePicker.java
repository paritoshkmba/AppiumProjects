package galatasaray.pageObjects;

import org.openqa.selenium.WebElement;

import cucumber.PageObjManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DatePicker extends PageObjManager {

	public DatePicker(AppiumDriver<WebElement> driver) {
		super(driver);
	}

	@AndroidFindBy(id = "datePicker")
	private WebElement datePicker;

	@AndroidFindBy(id = "wheel_date_picker_day")
	private WebElement datePicker_day;

	@AndroidFindBy(id = "wheel_date_picker_month")
	private WebElement datePicker_month;

	@AndroidFindBy(id = "wheel_date_picker_year")
	private WebElement datePicker_year;

	public void selectDOB(String string) {
		// TODO Auto-generated method stub
		
		swipeByCoordinates(this.datePicker_day.getLocation().getX(), this.datePicker_day.getLocation().getY(),
				this.datePicker_day.getLocation().getX(), this.datePicker_day.getLocation().getY() - 300);

	}


	

}
