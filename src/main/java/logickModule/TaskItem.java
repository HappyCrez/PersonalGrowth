package logickModule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class TaskItem extends AnchorPane{
	private Label contentField;
	private RadioButton checkerField;
	private Label dateField;
	private Label groupField;

	private LocalDate dateInfo;

	public TaskItem(String content, LocalDate date, TaskGroup group) {
		contentField = new Label(content);
		contentField.getStyleClass().add("taskContent");
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		dateField = new Label(date.format(format));
		dateField.getStyleClass().add("secondaryInfo");
		
		groupField = new Label(group.getName());
		groupField.getStyleClass().add("secondaryInfo");
		
		dateInfo = date;
		checkerField = new RadioButton();

		this.getChildren().addAll(contentField, checkerField, dateField, groupField);
		this.getStyleClass().add("taskItem");

		setTopAnchor(contentField, 0.0);
		setBottomAnchor(contentField, 10.0);
		setLeftAnchor(contentField, 20.0);
		setRightAnchor(contentField, 0.0);

		setTopAnchor(checkerField, 0.0);
		setBottomAnchor(checkerField, 10.0);
		setLeftAnchor(checkerField, -2.5);
		
		setBottomAnchor(groupField, -5.0);
		setLeftAnchor(groupField, 20.0);

		setBottomAnchor(dateField, -5.0);
		setRightAnchor(dateField, 0.0);
	}
	
	public LocalDate getDateField() {
		return dateInfo;
	}

	public RadioButton getCheckerField() {
		return checkerField;
	}

	public void setContent(String content) {
		this.contentField.setText(content);
	}
	
	public void setDateTask(String DateTask) {
		this.dateField.setText(DateTask);
	}

	public void setCheckerField(RadioButton checkBox) {
		this.checkerField = checkBox;
	}
}
