package logickModule;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class TaskItem extends AnchorPane{
	private Label contentField;
	private CheckBox checkerField;
	private Label dateField;

	private Date dateInfo;

	public TaskItem(String content, Date date) {
		this.contentField = new Label(content);
		this.dateField = new Label(date.toString());
		this.dateInfo = date;
		this.checkerField = new CheckBox();
		HBox group = new HBox(this.dateField, this.checkerField);
		group.getStyleClass().add("task-left-group");
		
		this.getChildren().addAll(this.contentField, group);
		this.getStyleClass().add("task-item");

		setLeftAnchor(contentField, 0.0);
		setRightAnchor(group, 0.0);
	}
	
	public Date getDateField() {
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
