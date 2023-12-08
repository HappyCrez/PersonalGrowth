package logickModule;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.event.ActionEvent;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CalendarBox extends StackPane {
    
    private VBox content;

    private HBox topBar;
    private Label MonthYear;
    private Button nextMonth, prevMonth;
	
    // Calendar containers
    private VBox calendarContainer;
    private VBox nextCalendarContainer;

    private Button grid[][];

    private static final int rowCount = 6, colCount = 7;
    private static String activeDateClass = "activeDate", todayID = "today", passiveDayID = "passiveDay";

    private Button dayLabelForGrid;
    private Date activeDate;
    private Button activeButton;

    private ExtendedCalendar calendar;
    private int currentDay,
                currentMonth,
                currentYear;
    
    public CalendarBox() {
        content = new VBox();
        topBar = new HBox();
        
        nextMonth = createArrow(new FontIcon("mdi-arrow-up"));
        nextMonth.setOnAction((ActionEvent e)-> {setNextMonth();});
        prevMonth = createArrow(new FontIcon("mdi-arrow-down"));
        prevMonth.setOnAction((ActionEvent e)-> {setPrevMonth();});
        
        MonthYear = new Label();
        MonthYear.setId("calendarLabel");

        topBar.getStyleClass().add("calendar-top-bar");
        topBar.getChildren().addAll(MonthYear, nextMonth, prevMonth);

        calendarContainer = new VBox();
        calendarContainer.getStyleClass().add("calendar-row-container");

        content.getStyleClass().add("calendar-container");
        content.getChildren().addAll(topBar, calendarContainer);
        this.getChildren().add(content);

        grid = new Button[rowCount][colCount];

        calendar = new ExtendedCalendar();
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        
        activeDate = Date.now();
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        fillCallendarBox(calendarContainer);
    }
    
    public void setNextMonth() {
        calendar.add(Calendar.MONTH, 1);
        fillCallendarBox(calendarContainer);
    }

    public void setPrevMonth() {
        calendar.add(Calendar.MONTH, -1);
        fillCallendarBox(calendarContainer);
    }

    public Date getActiveDate() {
        return activeDate;
    }

    private void onSwitchAnimation(final Pane prevPane, final Pane nextPane) {
        nextPane.translateYProperty().set(prevPane.getHeight());
		this.getChildren().add(nextPane);
        
		Timeline timeline = new Timeline();
        float timeDelay = 0.1f;
		KeyValue prevPaneMovement = new KeyValue(prevPane.translateYProperty(), -prevPane.getHeight(), Interpolator.LINEAR);
		KeyFrame prevPaneKeyFrame = new KeyFrame(Duration.seconds(timeDelay), prevPaneMovement);
		KeyValue nextPaneMovement = new KeyValue(nextPane.translateYProperty(), 40, Interpolator.LINEAR);
		KeyFrame nextPaneKeyFrame = new KeyFrame(Duration.seconds(timeDelay), nextPaneMovement);
		timeline.getKeyFrames().addAll(prevPaneKeyFrame, nextPaneKeyFrame);
        timeline.onFinishedProperty().set((ActionEvent e)->{
            prevPane.getChildren().clear();
            calendarContainer = nextCalendarContainer;
        });
		timeline.play();
    }
    
    private Button createArrow(FontIcon content) {
        Button btnArrow = new Button("", content);
        btnArrow.getStyleClass().add("btnArrow");
        return btnArrow;
    }
    
    private void fillCallendarBox(VBox container) {
        container.getChildren().clear();
        updateCalendarLabel();
        setMonthDays(container);
    }

    private void updateCalendarLabel() {
        MonthYear.setText(calendar.getCurrMonthName() + " " + calendar.get(Calendar.YEAR));
    }

    private void setMonthDays(VBox container) {
        int dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7; // At java Monday - 2 ((2 + 5) % 7 = 0)
        int daysCountBefore = calendar.getCountDaysMonthBefore();
        int lastFillCellNum = dayCount + firstDayOfWeek;

        fillDaysBeforeOfGridMassive(daysCountBefore, firstDayOfWeek);
        fillDaysOfGridMassive(dayCount, firstDayOfWeek);
        fillEndOfGridMassive(lastFillCellNum);
        
        updateGridPane(container);
    }

    private void fillDaysBeforeOfGridMassive(int daysCountBefore, int firstDayOfWeek) {
        for (int i = 0; i < firstDayOfWeek; i++) {
            String dayText = Integer.toString(daysCountBefore + i - firstDayOfWeek + 1);
            dayLabelForGrid = new Button(dayText);
            dayLabelForGrid.setId(passiveDayID);
            grid[0][i] = dayLabelForGrid;
        }
    }

    private void fillDaysOfGridMassive(int dayCount, int firstDayOfWeek) {
        for (int i = 0; i < dayCount; i++) {
            String dayText = Integer.toString(i + 1);
            dayLabelForGrid = new Button(dayText);
            int row = (i + firstDayOfWeek) / 7;
            int col = (i + firstDayOfWeek) % 7;
            grid[row][col] = dayLabelForGrid;
        }
    }
    
    private void fillEndOfGridMassive(int lastFillCellNum) {
        int cellsCount = countFreeCellsAtEndOfMassive(lastFillCellNum);
        for (int i = 0; i < cellsCount; i++) {
            String dayText = Integer.toString(i + 1);
            dayLabelForGrid = new Button(dayText);
            dayLabelForGrid.setId(passiveDayID);

            int row = rowCount - (cellsCount - i - 1) / 7 - 1;
            int col = (rowCount * colCount - cellsCount + i) % 7;
            grid[row][col] = dayLabelForGrid;
        }
    }

    private int countFreeCellsAtEndOfMassive(int lastFillCellNum) {
        return rowCount * colCount - lastFillCellNum;
    }

    private void updateGridPane(VBox container) {
        for (int i = 0; i < rowCount; i++) {
            HBox row = new HBox();
            row.getStyleClass().add("calendar-row");
            for (int k = 0; k < colCount; k++) {
                Button day = grid[i][k];
                row.getChildren().add(day);
                
                if (day.getId() == passiveDayID) {
                    day.setOnAction((ActionEvent e) -> { passiveHandle(e); });
                    continue;
                }
                else {
                    day.setOnAction((ActionEvent e) -> { activeHandle(e); });
                }

                if (compareActiveDate(day))
                    { setActiveButton(day); }

                if (calendar.compareMonthYear(currentMonth, currentYear) &&
                    Integer.parseInt(day.getText()) == currentDay)
                    { day.setId(todayID); }

            }
            container.getChildren().add(row);
        }
    }

    private boolean compareActiveDate(Button day) {
        int activeDay = activeDate.getDate();
        int activeMonth = activeDate.getMonth();
        int activeYear = activeDate.getYear();

        if (calendar.compareMonthYear(activeMonth, activeYear) &&
            Integer.parseInt(day.getText()) == activeDay)
            return true;
        return false;
    }

    private void passiveHandle(ActionEvent event) {
        Button eventItem = (Button)event.getSource();
        if (Integer.parseInt(eventItem.getText()) < 15) setPrevMonth();
        else setNextMonth();
    }

    private void activeHandle(ActionEvent event) {
        Button eventItem = (Button)event.getSource();
        setActiveButton(eventItem);
    }

    private void setActiveButton(Button btn) {
        if (activeButton == btn) return;

        updateActiveDate(btn);
        if (activeButton != null) activeButton.getStyleClass().remove(activeDateClass);
        activeButton = btn;
        activeButton.getStyleClass().add(activeDateClass);
    }

    private void updateActiveDate(Button eventItem) {
        try {
            activeDate = parseDate(eventItem.getText());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private Date parseDate(String day) throws DateTimeParseException {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return new Date(Integer.parseInt(day), month, year);
    }
}