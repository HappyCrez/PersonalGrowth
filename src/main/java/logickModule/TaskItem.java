package logickModule;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import java.time.LocalDate;

public class TaskItem extends AnchorPane{
	private Label contentLabel;
	private CheckBox checkTask;
	private Label date;
	
	private String content;
	private LocalDate dateInfo;

	public TaskItem(String content, String date) {
		this.content = content;
		this.contentLabel = new Label(content);
		
		this.date = new Label(date);
		this.dateInfo = LocalDate.parse(date);
		this.checkTask = new CheckBox();
		HBox group = new HBox(this.date, this.checkTask);
		group.getStyleClass().add("task-left-group");
		
		this.getChildren().addAll(this.contentLabel, group);
		this.getStyleClass().add("task-item");

		setLeftAnchor(contentLabel, 0.0);
		setRightAnchor(group, 0.0);
	}
	
	public LocalDate getDate() {
		return dateInfo;
	}

	public CheckBox getCheckTask() {
		return checkTask;
	}

	public void setContent(String content) {
		this.contentLabel.setText(content);
	}
	
	public void setDateTask(String DateTask) {
		this.date.setText(DateTask);
	}

	public void setCheckTask(CheckBox checkBox) {
		this.checkTask = checkBox;
	}
}
