package com;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import java.time.LocalDate;

public class TaskItem {
	private SimpleStringProperty titleTask = new SimpleStringProperty();
	private SimpleStringProperty descriptionTask = new SimpleStringProperty();
	private SimpleStringProperty dateTask = new SimpleStringProperty();
	private LocalDate date;
	private CheckBox checkBox;
	private static int count = 0;

	public TaskItem(String titleTask, String descriptionTask, LocalDate dateTask) {
		this.titleTask = new SimpleStringProperty(titleTask);
		this.descriptionTask = new SimpleStringProperty(descriptionTask);
		this.date = dateTask;
		this.dateTask = new SimpleStringProperty(date.toString());
		this.checkBox = new CheckBox();
		this.checkBox.setId(Integer.toString(++count));
	}
	public String getTitleTask() {
		return titleTask.get();
	}

	public String getDescriptionTask() {
		return descriptionTask.get();
	}

	public LocalDate getDateTask() {
		return date;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setTitleTask(String titleTask) {
		this.titleTask.set(titleTask);
	}
	
	public void setDescriptionTask(String descriptionTask) {
		this.descriptionTask.set(descriptionTask);
	}
	
	public void setDateTask(String DateTask) {
		this.dateTask.set(DateTask);
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
}
