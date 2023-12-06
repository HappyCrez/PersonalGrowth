package logickModule;

import java.time.LocalDate;
import java.util.Calendar;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CalendarBox extends StackPane implements EventHandler{
    
    private VBox content;

    private HBox MonthYearBar;
    private Label MonthYear;
    private Button nextMonth, prevMonth;
	
    // Calendar days
    private VBox rowContainer;

    private Label grid[][];

    private static final int rowCount = 6, colCount = 7;
    private static String todayLabelID = "today", passiveLabelID = "passiveDay";

    private Label dayLabelForGrid;
    private LocalDate activeDate;

    private ExtendedCalendar calendar;
    private int currentDay,
                currentMonth,
                currentYear;
    
    public CalendarBox() {
        content = new VBox();
        MonthYearBar = new HBox();
        
        nextMonth = new Button("",new FontIcon("mdi-arrow-up"));
        nextMonth.getStyleClass().add("btnArrow");
        nextMonth.setOnAction(this);

        prevMonth = new Button("",new FontIcon("mdi-arrow-down"));
        prevMonth.getStyleClass().add("btnArrow");
        prevMonth.setOnAction(this);
        
        MonthYear = new Label();
        MonthYear.setId("calendarLabel");

        MonthYearBar.getStyleClass().add("calendar-top-bar");
        MonthYearBar.getChildren().addAll(MonthYear, nextMonth, prevMonth);

        rowContainer = new VBox();
        rowContainer.getStyleClass().add("calendar-row-container");

        content.getStyleClass().add("calendar-container");
        content.getChildren().addAll(MonthYearBar, rowContainer);
        this.getChildren().add(content);

        grid = new Label[rowCount][colCount];

        calendar = new ExtendedCalendar();
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        updateGrid();
        
        activeDate = LocalDate.now();
    }
    
    public void setNextMonth() {
        calendar.add(Calendar.MONTH, 1);
        updateGrid();
    }

    public void setPrevMonth() {
        calendar.add(Calendar.MONTH, -1);
        updateGrid();
    }
    
    private void updateGrid() {
        rowContainer.getChildren().clear();
        updateCalendarLabel();
        setMonthDays();
    }

    private void updateCalendarLabel() {
        MonthYear.setText(calendar.getCurrMonthName() + " " + calendar.get(Calendar.YEAR));
    }

    private void setMonthDays() {
        int dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7; // At java Monday - 2 ((2 + 5) % 7 = 0)
        int daysCountBefore = calendar.getCountDaysMonthBefore();
        int lastFillCellNum = dayCount + firstDayOfWeek;

        fillDaysBeforeOfGridMassive(daysCountBefore, firstDayOfWeek);
        fillDaysOfGridMassive(dayCount, firstDayOfWeek);
        fillEndOfGridMassive(lastFillCellNum);
        
        fillGridPane();
    }

    private void fillDaysBeforeOfGridMassive(int daysCountBefore, int firstDayOfWeek) {
        for (int i = 0; i < firstDayOfWeek; i++) {
            dayLabelForGrid = new Label(daysCountBefore + i - firstDayOfWeek + 1 + "");
            dayLabelForGrid.setId(passiveLabelID);
            grid[0][i] = dayLabelForGrid;
        }
    }

    private void fillDaysOfGridMassive(int dayCount, int firstDayOfWeek) {
        for (int i = 0; i < dayCount; i++) {
            dayLabelForGrid = new Label(i + 1 + "");

            int row = (i + firstDayOfWeek) / 7;
            int col = (i + firstDayOfWeek) % 7;
            grid[row][col] = dayLabelForGrid;
        }
    }
    
    private void fillEndOfGridMassive(int lastFillCellNum) {
        int cellsCount = countFreeCellsAtEndOfMassive(lastFillCellNum);
        for (int i = 0; i < cellsCount; i++) {
            dayLabelForGrid = new Label(i + 1 + "");
            dayLabelForGrid.setId(passiveLabelID);

            int row = rowCount - (cellsCount - i - 1) / 7 - 1;
            int col = (rowCount * colCount - cellsCount + i) % 7;
            grid[row][col] = dayLabelForGrid;
        }
    }

    private int countFreeCellsAtEndOfMassive(int lastFillCellNum) {
        return rowCount * colCount - lastFillCellNum;
    }

    private void fillGridPane() {
        for (int i = 0; i < rowCount; i++) {
            HBox row = new HBox();
            row.getStyleClass().add("calendar-row");
            for (int k = 0; k < colCount; k++) {
                Label day = grid[i][k];
                if (calendar.checkForMonth(currentMonth, currentYear) &&
                    Integer.parseInt(day.getText()) == currentDay &&
                    day.getId() != passiveLabelID)
                    {
                        day.setId(todayLabelID);
                    }
                day.setOnMouseClicked(this);
                row.getChildren().add(day);
            }
            rowContainer.getChildren().add(row);
        }
    }

    public String getActiveDate() {
        return activeDate.toString();
    }

    @Override
    public void handle(Event event) {
        if (event.getSource().getClass().getSimpleName().equals("Label")) {
            Label lbl = (Label) event.getSource();
            if (lbl.getId() != null || lbl.getId() == passiveLabelID) return;
            String date =
                calendar.get(Calendar.YEAR) + "-" +
                calendar.get(Calendar.MONTH) + "-" +
                ((lbl.getText().length() > 1) ? lbl.getText(): "0" + lbl.getText());
            
            activeDate = LocalDate.parse(date);
        }
        else {

            setNextMonth();
        }
    }
}