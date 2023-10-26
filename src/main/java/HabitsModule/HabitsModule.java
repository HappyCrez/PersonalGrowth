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
        int firstDayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        int daysBefore = getCountDaysMonthBefore();

        for (int i = 0; i < firstDayOfWeek; i++) {
            dayLabelForGrid = new Label(daysBefore + i - firstDayOfWeek + 1 + "");
            dayLabelForGrid.getStyleClass().add("day");
            dayLabelForGrid.setId("noActiveDay");
            grid[0][i] = dayLabelForGrid;
        }

        for (int i = 0; i < dayCount; i++) {
            dayLabelForGrid = new Label(i + 1 + "");
            dayLabelForGrid.getStyleClass().add("day");
            grid[(i + firstDayOfWeek) / 7][(i + firstDayOfWeek) % 7] = dayLabelForGrid;
        }

        int lastAfterDays = rowCount * colCount - (dayCount + firstDayOfWeek);
        for (int i = 0; i < lastAfterDays; i++) {
            dayLabelForGrid = new Label(i + 1 + "");
            dayLabelForGrid.getStyleClass().add("day");
            dayLabelForGrid.setId("noActiveDay");
            grid[rowCount - (lastAfterDays - i - 1) / 7 - 1][(rowCount * colCount - lastAfterDays + i) % 7] = dayLabelForGrid;
        }

        for (int i = 0; i < rowCount; i++) {
            for (int k = 0; k < colCount; k++) {
                gridPane.add(grid[i][k], k, i);
            }
        }
        
        if (calendar.get(Calendar.DAY_OF_MONTH) == currentDay &&
            calendar.get(Calendar.MONTH) == currentMonth &&
            calendar.get(Calendar.YEAR) == currentYear)
            {
                //TODO::mark current day (color? bg? smthg else?)
            }
    }

    private int getCountDaysMonthBefore() {
        calendar.add(Calendar.MONTH, -1);
        int daysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.MONTH, 1);
        return daysCount;
    }

    public void setNextMonth() {
        calendar.add(Calendar.MONTH, 1);
        updateGrid();
    }

    public void setPrevMonth() {
        calendar.add(Calendar.MONTH, -1);
        updateGrid();
    }
}