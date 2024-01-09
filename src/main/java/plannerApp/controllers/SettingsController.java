package plannerApp.controllers;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import plannerApp.javafxWidget.timer.Timer;

public class SettingsController {
    private ScreenController controller;
    
    @FXML
    private TextArea timeField;

    private int timeFieldValue = 25;

    public SettingsController(ScreenController controller) {
        this.controller = controller;
    }

    public void initialize() {
        timeField.setText("Time in minets (25 - standard value)");

        Pattern pattern = Pattern.compile(".{0,3}");
        TextFormatter<Change> formatter = new TextFormatter<Change>((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        timeField.setTextFormatter(formatter);
        timeField.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            {   if (newPropertyValue) // true then field get Focus 
                { timeField.setText(""); }
                else { getTimeFieldValue(); }
            }
        });
    }

    @FXML
    private void toToDoListClicked() {
        controller.activateScreen("toDoListView", Animation.animationStyles.leftToRight);
    }
    
    @FXML
    private void toTimerClicked() {
        controller.activateScreen("timerView", Animation.animationStyles.leftToRight);
    }

    @FXML
    private void increment() {
        timeFieldValue++;
        updateTimeField();
    }

    @FXML
    private void decrement() {
        timeFieldValue--;
        updateTimeField();
    }

    private void getTimeFieldValue() {
        try { 
            int timeCurrentValue = Integer.parseInt(timeField.getText()); 
            if (timeCurrentValue > 120) {
                updateTimeField();    
            }
            else {    
                this.timeFieldValue = timeCurrentValue;   
                updateTimeField();
            }
        } catch (NumberFormatException e) {
            updateTimeField();
        }
    }

    private void updateTimeField() {
        timeField.setText(Integer.toString(timeFieldValue));
        Timer.setTime(timeFieldValue * 60);
    }
}
