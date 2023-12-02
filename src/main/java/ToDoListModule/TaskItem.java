package ToDoListModule;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.time.LocalDate;

public class TaskItem extends HBox{
	@FXML
	private Label title;
	@FXML
	private Label description;
	@FXML
	private CheckBox checkBox;
	
	private Label date;
	private LocalDate dateInfo;

	public TaskItem(String title, String description, String date) {
		this.title = new Label(title);
		this.description = new Label(description);
		this.date = new Label(date);

		this.checkBox = new CheckBox();
		this.dateInfo = LocalDate.parse(date);

		this.getChildren().addAll(this.date, this.description, this.title);
		this.getStyleClass().add("task-item");
	}
	public String getTitleTask() {
		return title.getText();
	}

	public String getDescriptionTask() {
		return description.getText();
	}

	public LocalDate getDate() {
		return dateInfo;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setTitleTask(String titleTask) {
		this.title.setText(titleTask);
	}
	
	public void setDescriptionTask(String descriptionTask) {
		this.description.setText(descriptionTask);
	}
	
	public void setDateTask(String DateTask) {
		this.date.setText(DateTask);
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
}
