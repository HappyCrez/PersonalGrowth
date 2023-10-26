package HabitsModule;

import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HabitsModule {

	private GridPane gridPane;
    private Label calendarLabel;

    private Label grid[][];

    private static final int rowCount = 6, colCount = 7;
    private static String dayLabelClassName = "day", todayLabelID = "today", passiveLabelID = "passiveDay";

    private Label dayLabelForGrid;

    private Calendar calendar;
    private int currentDay,
                currentMonth,
                currentYear;
    
    public HabitsModule(GridPane gridPane, Label calendarLabel) {
        this.gridPane = gridPane;
        this.calendarLabel = calendarLabel;

        grid = new Label[rowCount][colCount];

        calendar = new GregorianCalendar();
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
        gridPane.getChildren().clear();
        updateCalendarLabel();
        setMonthDays();
    }

    private void updateCalendarLabel() {
        calendarLabel.setText(Month.of(calendar.get(Calendar.MONTH) + 1) + " " + calendar.get(Calendar.YEAR));
    }

    private void setMonthDays() {
        int dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7; // At java Monday - 2 ((2 + 5) % 7 = 0)
        int daysCountBefore = getCountDaysMonthBefore();
        int lastFillCellNum = dayCount + firstDayOfWeek;

        fillDaysBeforeOfGridMassive(daysCountBefore, firstDayOfWeek);
        fillDaysOfGridMassive(dayCount, firstDayOfWeek);
        fillEndOfGridMassive(lastFillCellNum);
        
        fillGridPane();
    }
    
    private int getCountDaysMonthBefore() {
        calendar.add(Calendar.MONTH, -1);
        int daysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.MONTH, 1);
        return daysCount;
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
                if (checkCurrentDate(Integer.parseInt(day.getText())) && day.getId() != passiveLabelID)
                    day.setId(todayLabelID);
                gridPane.add(day, k, i);
            }
        }
    }

    private boolean checkCurrentDate(int todayDay) {
        if (todayDay == currentDay &&
            calendar.get(Calendar.MONTH) == currentMonth &&
            calendar.get(Calendar.YEAR) == currentYear)
            {
                return true;
            }
        return false;
    }
}