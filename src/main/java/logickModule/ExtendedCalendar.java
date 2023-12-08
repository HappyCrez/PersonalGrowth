package logickModule;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ExtendedCalendar extends GregorianCalendar{
    
    public static HashMap <Integer, String> monthDict = new HashMap<>() {{
        put(0,  "Январь"    );
        put(1,  "Февраль"   );
        put(2,  "Март"      );
        put(3,  "Апрель"    );
        put(4,  "Май"       );
        put(5,  "Июнь"      );
        put(6,  "Июль"      );
        put(7,  "Август"    );
        put(8,  "Сентябрь"  );
        put(9,  "Октябрь"   );
        put(10, "Ноябрь"    );
        put(11, "Декабрь"   );
    }};

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

    public String getCurrMonthName() {
        return monthDict.get(this.get(Calendar.MONTH));
    }
}
