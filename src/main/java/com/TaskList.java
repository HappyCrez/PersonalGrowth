package com;

import javafx.beans.property.SimpleStringProperty;

public class TaskList {
	private SimpleStringProperty titleTask = new SimpleStringProperty();
	private SimpleStringProperty descriptionTask = new SimpleStringProperty();
	private SimpleStringProperty dateTask = new SimpleStringProperty();
	
	public TaskList(String titleTask, String descriptionTask, String dateTask) {
		super();
		this.titleTask = new SimpleStringProperty(titleTask);
		this.descriptionTask = new SimpleStringProperty(descriptionTask);
		this.dateTask = new SimpleStringProperty(dateTask);
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
