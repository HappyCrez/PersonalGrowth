package com;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class TaskList {
	private SimpleStringProperty titleTask;
	private SimpleStringProperty descriptionTask;
	private SimpleStringProperty dateTask;
	private CheckBox checkBox;
	
	public TaskList(String titleTask, String descriptionTask, String dateTask) {
		this.titleTask = new SimpleStringProperty(titleTask);
		this.descriptionTask = new SimpleStringProperty(descriptionTask);
		this.dateTask = new SimpleStringProperty(dateTask);
		this.checkBox = new CheckBox();
	}
	
	public String getTitleTask() {
		return titleTask.get();
	}
	
	public void setTitleTask(String titleTask) {
		this.titleTask.set(titleTask);
	}
	
	public String getDescriptionTask() {
		return descriptionTask.get();
	}
	
	public void setDescriptionTask(String descriptionTask) {
		this.descriptionTask.set(descriptionTask);
	}
	
	public String getDateTask() {
		return dateTask.get();
	}
	
	public void setDateTask(String DateTask) {
		this.dateTask.set(DateTask);
	}
}
