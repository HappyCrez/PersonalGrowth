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

	private LocalDate dateInfo;

	public TaskItem(String content, LocalDate date) {
		this.contentField = new Label(content);
		this.contentField.getStyleClass().add("taskContent");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		this.dateField = new Label(date.format(format));
		this.dateField.getStyleClass().add("secondaryInfo");
		this.dateInfo = date;
		this.checkerField = new RadioButton();

		
		this.getChildren().addAll(this.contentField, this.checkerField, this.dateField);
		this.getStyleClass().add("taskItem");


		setTopAnchor(contentField, 0.0);
		setBottomAnchor(contentField, 10.0);
		setLeftAnchor(contentField, 20.0);
		setRightAnchor(contentField, 0.0);

		setTopAnchor(checkerField, 0.0);
		setBottomAnchor(checkerField, 10.0);
		setLeftAnchor(checkerField, -2.5);
		
		setBottomAnchor(dateField, -5.0);
		setRightAnchor(dateField, 0.0);
		setLeftAnchor(dateField, 0.0);
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
