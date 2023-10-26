package HabitsModule;

import com.ScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HabitsController {
	
	private HabitsModule habitsModule;
	private ScreenController screenController;
	
	@FXML
	private Button toToDoList, toTimer, toSettings;
	
	@FXML
	private Label calendarLabel;

	@FXML
	private GridPane gridPane;

	public HabitsController (ScreenController screenController) {
		this.screenController = screenController;
	}
	
	@FXML
	private void initialize() {
		habitsModule = new HabitsModule(gridPane, calendarLabel);
	}

	@FXML
	private void prevMounth() {
		habitsModule.setPrevMonth();
	}
	
	@FXML
	private void nextMounth() {
		habitsModule.setNextMonth();
	}
	
	@FXML
	private void toToDoListClicked() {
		screenController.activateScreen("toDoListView", ScreenController.animationStyles.leftToRight);
	}
	
	@FXML
	private void toTimerClicked() {
		System.out.println("to ToTimerBtn clicked");
	}
	
	@FXML
	private void toSettingsClicked() {
		System.out.println("to ToSettingsBtn clicked");
	}
}
