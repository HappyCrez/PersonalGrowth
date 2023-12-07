package logickModule;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

    private HBox topBar;
    private Label MonthYear;
    private Button nextMonth, prevMonth;
	
    // Calendar days
    private VBox rowContainer;

    private Button grid[][];

    private static final int rowCount = 6, colCount = 7;
    private static String activeDateClass = "activeDate", todayID = "today", passiveDayID = "passiveDay";

    private Button dayLabelForGrid;
    private LocalDate activeDate;
    private Button activeButton;

    private ExtendedCalendar calendar;
    private int currentDay,
                currentMonth,
                currentYear;
    
    public CalendarBox() {
        content = new VBox();
        topBar = new HBox();
        
        nextMonth = new Button("",new FontIcon("mdi-arrow-up"));
        nextMonth.getStyleClass().add("btnArrow");
        nextMonth.setOnAction(this);

        prevMonth = new Button("",new FontIcon("mdi-arrow-down"));
        prevMonth.getStyleClass().add("btnArrow");
        prevMonth.setOnAction(this);
        
        MonthYear = new Label();
        MonthYear.setId("calendarLabel");

        topBar.getStyleClass().add("calendar-top-bar");
        topBar.getChildren().addAll(MonthYear, nextMonth, prevMonth);

        rowContainer = new VBox();
        rowContainer.getStyleClass().add("calendar-row-container");

        content.getStyleClass().add("calendar-container");
        content.getChildren().addAll(topBar, rowContainer);
        this.getChildren().add(content);

        grid = new Button[rowCount][colCount];

        calendar = new ExtendedCalendar();
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        
        activeDate = LocalDate.now();
        
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        fillCallendarBox(rowContainer);
    }
    
    public void setNextMonth() {
        calendar.add(Calendar.MONTH, 1);
        fillCallendarBox(rowContainer);
    }

    public void setPrevMonth() {
        calendar.add(Calendar.MONTH, -1);
        fillCallendarBox(rowContainer);
    }
    
    private void fillCallendarBox(VBox container) {
        rowContainer.getChildren().clear();
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

                int activeDay = activeDate.getDayOfMonth();
                int activeMonth = (activeDate.getMonthValue() - 1) % 12;
                int activeYear = activeDate.getYear();    

                if (calendar.checkForMonth(activeMonth, activeYear) &&
                    Integer.parseInt(day.getText()) == activeDay &&
                    day.getId() != passiveDayID)
                    { setActiveButton(day); }

                if (calendar.checkForMonth(currentMonth, currentYear) &&
                    Integer.parseInt(day.getText()) == currentDay &&
                    day.getId() != passiveDayID)
                    {   day.setId(todayID); }

                day.setOnMouseClicked(this);
                row.getChildren().add(day);
            }
            container.getChildren().add(row);
        }
    }

    public String getActiveDate() {
        return activeDate.toString();
    }

    @Override
    public void handle(Event event) {
        Button eventItem = (Button)event.getSource();

        if (eventItem == nextMonth)
            setNextMonth();
        else if (eventItem == prevMonth)
            setPrevMonth();
        else if (eventItem.getId() != null && eventItem.getId().equals(passiveDayID))
            passiveHandle(eventItem);
        else
            setActiveButton(eventItem);
    }

    private void passiveHandle(Button eventItem) {
        if (Integer.parseInt(eventItem.getText()) < 15) setPrevMonth();
        else setNextMonth();
    }

    private void setActiveButton(Button eventItem) {
        if (activeButton == eventItem) return;

        updateActiveDate(eventItem);
        if (activeButton != null) activeButton.getStyleClass().remove(activeDateClass);
        activeButton = eventItem;
        activeButton.getStyleClass().add(activeDateClass);
    }

    private void updateActiveDate(Button eventItem) {
        try {
            activeDate = parseDate(eventItem.getText());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private LocalDate parseDate(String day) throws DateTimeParseException {
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        
        
        String month = Integer.toString((calendar.get(Calendar.MONTH) + 1) % 13);
        month = (month.length() == 1)? "0" + month : month; 
        
        
        day = (day.length() == 1)? "0" + day : day;

        String date = String.format("%s-%s-%s", year, month, day);
        return LocalDate.parse(date);
    }
}