package logickModule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class TaskItem extends AnchorPane{
	private Label contentField;
	private CheckBox checkerField;
	private Label dateField;

	private LocalDate dateInfo;

	public TaskItem(String content, LocalDate date) {
		this.contentField = new Label(content);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		this.dateField = new Label(date.format(format));
		this.dateInfo = date;
		this.checkerField = new CheckBox();
		HBox group = new HBox(this.dateField, this.checkerField);
		group.getStyleClass().add("task-left-group");
		
		this.getChildren().addAll(this.contentField, group);
		this.getStyleClass().add("task-item");

		setLeftAnchor(contentField, 0.0);
		setRightAnchor(group, 0.0);
	}
	
	public LocalDate getDateField() {
		return dateInfo;
	}

	public CheckBox getCheckerField() {
		return checkerField;
	}

	public void setContent(String content) {
		this.contentField.setText(content);
	}
	
	public void setDateTask(String DateTask) {
		this.dateField.setText(DateTask);
	}

	public void setCheckerField(CheckBox checkBox) {
		this.checkerField = checkBox;
	}
}
