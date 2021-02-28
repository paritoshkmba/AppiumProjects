package galatasaray.stepdefinition;

import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

import com.google.common.net.MediaType;

import core.AppiumInstance;
import core.CoreFunctions;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before
	public void beforeHook(Scenario scenario) {
		
			System.out.println(" ,---.                                            ,--.            ,---.     ,--.                      ,--.              ,--. \r\n" + 
		"'   .-'   ,---.  ,---.  ,--,--,   ,--,--. ,--.--. `--'  ,---.    '   .-'  ,-'  '-.  ,--,--. ,--.--. ,-'  '-.  ,---.   ,-|  | \r\n" + 
		"`.  `-.  | .--' | .-. : |      \\ ' ,-.  | |  .--' ,--. | .-. |   `.  `-.  '-.  .-' ' ,-.  | |  .--' '-.  .-' | .-. : ' .-. | \r\n" + 
		".-'    | \\ `--. \\   --. |  ||  | \\ '-'  | |  |    |  | ' '-' '   .-'    |   |  |   \\ '-'  | |  |      |  |   \\   --. \\ `-' | \r\n" + 
		"`-----'   `---'  `----' `--''--'  `--`--' `--'    `--'  `---'    `-----'    `--'    `--`--' `--'      `--'    `----'  `---'  ");
		
			System.out.println("-----------------------------\n");
			System.out.println(scenario.getName().toUpperCase() + " STARTED");
			System.out.println("\n-----------------------------");
		
	}
	
	@After
	public void aferHook(Scenario scenario) {
		
		System.out.println("-----------------------------\n");
		System.out.println(scenario.getName().toUpperCase() + " ENDED \n STATUS : " + scenario.getStatus());
		System.out.println("\n-----------------------------");
		
		if(scenario.isFailed()) {
			CoreFunctions fun = new CoreFunctions(AppiumInstance.getAppiumDriver());
			String outputFileLocation = fun.takeFailedScreenshot(scenario.getName());
			scenario.attach(outputFileLocation.getBytes(), outputFileLocation, scenario.getName());
		}
		
		System.out.println(" ,---.                                            ,--.           ,------.             ,--.            ,--. \r\n" + 
				"'   .-'   ,---.  ,---.  ,--,--,   ,--,--. ,--.--. `--'  ,---.    |  .---' ,--,--,   ,-|  |  ,---.   ,-|  | \r\n" + 
				"`.  `-.  | .--' | .-. : |      \\ ' ,-.  | |  .--' ,--. | .-. |   |  `--,  |      \\ ' .-. | | .-. : ' .-. | \r\n" + 
				".-'    | \\ `--. \\   --. |  ||  | \\ '-'  | |  |    |  | ' '-' '   |  `---. |  ||  | \\ `-' | \\   --. \\ `-' | \r\n" + 
				"`-----'   `---'  `----' `--''--'  `--`--' `--'    `--'  `---'    `------' `--''--'  `---'   `----'  `---'  ");
		
		
	}

}
