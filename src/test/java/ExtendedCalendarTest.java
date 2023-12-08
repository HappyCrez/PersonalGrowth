
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import logickModule.ExtendedCalendar;

class ExtendedCalendarTest {
    ExtendedCalendar calendar;

    ExtendedCalendarTest () {
        calendar = new ExtendedCalendar();
    }
    
    @Test
    public void compareMonthYearTest() {
        int monthTest = 1, yearTest = 1970;
        int anotherMonth = 2, anotherYear = 1971;

        calendar.set(yearTest, monthTest, 1);
        assertEquals(true, calendar.compareMonthYear(monthTest, yearTest));
        assertEquals(false, calendar.compareMonthYear(monthTest, anotherYear));
        assertEquals(false, calendar.compareMonthYear(anotherMonth, yearTest));
        assertEquals(false, calendar.compareMonthYear(anotherMonth, anotherYear));
    }
}