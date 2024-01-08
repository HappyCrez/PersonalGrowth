package plannerApp.javafxWidget;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import plannerApp.controllers.Container;

public class TaskItem extends AnchorPane{

	private RadioButton checkerField;
	private Button deleteField;
	
	private Label contentField;
	private Label dateField;
	private Label groupField;

	private GroupItem groupData;
	private LocalDate dateInfo;
	private long ID;

	private Timeline strokethrough;

	public TaskItem(TaskItem source) {
		this(source.getContent(), source.getDateField(), source.getID());
		this.setGroup(source.getGroup());
	}

	public TaskItem(String content, LocalDate date, long ID) {
		this(content, date);
		this.ID = ID;
	}

	public TaskItem(String content, LocalDate date) {
		ID = System.currentTimeMillis();

		contentField = new Label(content);
		contentField.getStyleClass().add("taskContent");
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
		dateField = new Label(date.format(format));
		dateField.getStyleClass().add("secondaryInfo");
		if (date.compareTo(LocalDate.now()) < 0) {
			dateField.getStyleClass().add("overdueDate");
		}
		
		groupField = new Label("Tasks");
		groupField.getStyleClass().add("secondaryInfo");
		
		dateInfo = date;
		deleteField = new Button("", new FontIcon("mdi-delete-forever"));
		deleteField.getStyleClass().add("deleteBtn");
		deleteField.setOnAction((e) -> {
			Container.deleteItem(this);
		});

		strokethrough = new Timeline(
			new KeyFrame(Duration.seconds(0.1), null)
		);
		strokethrough.setOnFinished((e) -> {
			Container.checkTask(this);
		});
		checkerField = new RadioButton();
		checkerField.setOnAction((e) -> {
			this.setStyle("-fx-background-color: rgba(150, 150, 150, 0.3);");
			strokethrough.play();
		});

		this.getChildren().addAll(contentField, checkerField, dateField, groupField, deleteField);
		this.getStyleClass().add("taskItem");

		this.setOnMouseClicked((e) -> {
			Container.chooseItem(this);
		});

		setTopAnchor(contentField, 0.0);
		setBottomAnchor(contentField, 10.0);
		setLeftAnchor(contentField, 20.0);
		setRightAnchor(contentField, 30.0);

		setTopAnchor(deleteField, 0.0);
		setBottomAnchor(deleteField, 0.0);
		setRightAnchor(deleteField, -10.0);

		setTopAnchor(checkerField, 0.0);
		setBottomAnchor(checkerField, 0.0);
		setLeftAnchor(checkerField, -2.5);
		
		setBottomAnchor(groupField, -5.0);
		setLeftAnchor(groupField, 20.0);

		setBottomAnchor(dateField, -5.0);
		setRightAnchor(dateField, 30.0);
	}

	public void setChecked() {
		this.getChildren().remove(checkerField);
	}
	
	public long getID() {
		return ID;
	}

	public LocalDate getDateField() {
		return dateInfo;
	}

	public RadioButton getCheckerField() {
		return checkerField;
	}

	public String getContent() {
		return contentField.getText();
	}

	public GroupItem getGroup() {
		return groupData;
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

	public void setGroup(GroupItem group) {
		this.groupData = group;
		this.groupField.setText(group.getName());
	}

	@Override
	public String toString() {
		return String.format("%s§%s§%d",
		contentField.getText(), this.dateField.getText(), this.ID);
	}
}
