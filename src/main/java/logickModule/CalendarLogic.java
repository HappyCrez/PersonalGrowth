package logickModule;

import java.util.Calendar;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CalendarLogic {

	private GridPane calendarGridPane;
    private Label calendarLabel;

    private Label grid[][];

    private static final int rowCount = 6, colCount = 7;
    private static String dayLabelClassName = "day", todayLabelID = "today", passiveLabelID = "passiveDay";

    private Label dayLabelForGrid;

    private ExtendedCalendar calendar;
    private int currentDay,
                currentMonth,
                currentYear;
    
    public CalendarLogic(GridPane calendarGridPane, Label calendarLabel) {
        this.calendarGridPane = calendarGridPane;
        this.calendarLabel = calendarLabel;

        grid = new Label[rowCount][colCount];

        calendar = new ExtendedCalendar();
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        updateGrid();
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
        calendarGridPane.getChildren().clear();
        updateCalendarLabel();
        setMonthDays();
    }

    private void updateCalendarLabel() {
        calendarLabel.setText(calendar.getCurrMonthName() + " " + calendar.get(Calendar.YEAR));
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
            dayLabelForGrid.getStyleClass().add(dayLabelClassName);
            dayLabelForGrid.setId(passiveLabelID);
            grid[0][i] = dayLabelForGrid;
        }
    }

    private void fillDaysOfGridMassive(int dayCount, int firstDayOfWeek) {
        for (int i = 0; i < dayCount; i++) {
            dayLabelForGrid = new Label(i + 1 + "");
            dayLabelForGrid.getStyleClass().add(dayLabelClassName);

            int row = (i + firstDayOfWeek) / 7;
            int col = (i + firstDayOfWeek) % 7;
            grid[row][col] = dayLabelForGrid;
        }
    }
    
    private void fillEndOfGridMassive(int lastFillCellNum) {
        int cellsCount = countFreeCellsAtEndOfMassive(lastFillCellNum);
        for (int i = 0; i < cellsCount; i++) {
            dayLabelForGrid = new Label(i + 1 + "");
            dayLabelForGrid.getStyleClass().add(dayLabelClassName);
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
            for (int k = 0; k < colCount; k++) {
                Label day = grid[i][k];
                if (calendar.checkForMonth(currentMonth, currentYear) &&
                    Integer.parseInt(day.getText()) == currentDay &&
                    day.getId() != passiveLabelID)
                    {
                        day.setId(todayLabelID);
                    }
                calendarGridPane.add(day, k, i);
            }
        }
    }
}