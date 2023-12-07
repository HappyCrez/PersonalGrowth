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

    private Button grid[][];

    private static final int rowCount = 6, colCount = 7;
    private static String todayLabelID = "today", passiveLabelID = "passiveDay";

    private Button dayLabelForGrid;
    private LocalDate activeDate;
    private Button activeButton;

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

        grid = new Button[rowCount][colCount];

        calendar = new ExtendedCalendar();
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        refillGrid();
        
        activeDate = LocalDate.now();
    }
    
    public void setNextMonth() {
        calendar.add(Calendar.MONTH, 1);
        refillGrid();
    }

    public void setPrevMonth() {
        calendar.add(Calendar.MONTH, -1);
        refillGrid();
    }
    
    private void refillGrid() {
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
        
        updateGridPane();
    }

    private void fillDaysBeforeOfGridMassive(int daysCountBefore, int firstDayOfWeek) {
        for (int i = 0; i < firstDayOfWeek; i++) {
            dayLabelForGrid = new Button(daysCountBefore + i - firstDayOfWeek + 1 + "");
            dayLabelForGrid.setId(passiveLabelID);
            grid[0][i] = dayLabelForGrid;
        }
    }

    private void fillDaysOfGridMassive(int dayCount, int firstDayOfWeek) {
        for (int i = 0; i < dayCount; i++) {
            dayLabelForGrid = new Button(i + 1 + "");

            int row = (i + firstDayOfWeek) / 7;
            int col = (i + firstDayOfWeek) % 7;
            grid[row][col] = dayLabelForGrid;
        }
    }
    
    private void fillEndOfGridMassive(int lastFillCellNum) {
        int cellsCount = countFreeCellsAtEndOfMassive(lastFillCellNum);
        for (int i = 0; i < cellsCount; i++) {
            dayLabelForGrid = new Button(i + 1 + "");
            dayLabelForGrid.setId(passiveLabelID);

            int row = rowCount - (cellsCount - i - 1) / 7 - 1;
            int col = (rowCount * colCount - cellsCount + i) % 7;
            grid[row][col] = dayLabelForGrid;
        }
    }

    private int countFreeCellsAtEndOfMassive(int lastFillCellNum) {
        return rowCount * colCount - lastFillCellNum;
    }

    private void updateGridPane() {
        for (int i = 0; i < rowCount; i++) {
            HBox row = new HBox();
            row.getStyleClass().add("calendar-row");
            for (int k = 0; k < colCount; k++) {
                Button day = grid[i][k];
                if (calendar.checkForMonth(currentMonth, currentYear) &&
                    Integer.parseInt(day.getText()) == currentDay &&
                    day.getId() != passiveLabelID)
                    {
                        day.setId(todayLabelID);
                        activeButton = day;
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
        Button eventItem = (Button)event.getSource();

        if (eventItem == nextMonth) {
            setNextMonth();
            return;
        }
        else if (eventItem == prevMonth) {
            setPrevMonth();
            return;
        }

        if (activeButton == eventItem) return;
        if (eventItem.getId() != null && eventItem.getId().equals(passiveLabelID)) return;

        String year = Integer.toString(calendar.get(Calendar.YEAR));
        String month = Integer.toString((calendar.get(Calendar.MONTH) + 1) % 13);
        month = (month.length() == 1)? "0" + month : month; 
        String day = eventItem.getText();
        day = (day.length() == 1)? "0" + day : day;

        String date = String.format("%s-%s-%s", year, month, day);
        
        try {
            activeDate = LocalDate.parse(date);
        } catch (Exception e) {
            System.out.println(String.format("Can't parse string: '%s'", date));
            e.getStackTrace();
        }

        activeButton.getStyleClass().remove("activeDate");
        activeButton = eventItem;
        activeButton.getStyleClass().add("activeDate");
    }
}