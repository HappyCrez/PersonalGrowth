package logickModule;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ExtendedCalendar extends GregorianCalendar{
    
    public int getCountDaysMonthBefore() {
        add(Calendar.MONTH, -1);
        int daysCount = getActualMaximum(Calendar.DAY_OF_MONTH);
        add(Calendar.MONTH, 1);
        return daysCount;
    }

    public boolean compareMonthYear(int month, int year) {
        if (get(Calendar.MONTH) == month &&
            get(Calendar.YEAR) == year)
                return true;
        return false;
    }

    String getCurrMonthName() {
        switch (get(Calendar.MONTH)) {
            case 0:
                return "Январь";
            case 1:
                return "Февраль";
            case 2:
                return "Март";
            case 3:
                return "Апрель";
            case 4:
                return "Май";
            case 5:
                return "Июнь";
            case 6:
                return "Июль";
            case 7:
                return "Август";
            case 8:
                return "Сентябрь";
            case 9:
                return "Октябрь";
            case 10:
                return "Ноябрь";
            case 11:
                return "Декабрь";
            default:
                return "0";
        }
    }
}
