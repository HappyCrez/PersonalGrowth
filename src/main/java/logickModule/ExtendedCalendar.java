package logickModule;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ExtendedCalendar extends GregorianCalendar{
    
    public static HashMap <Integer, String> monthDict = new HashMap<>() {{
        put(1,  "Январь"    );
        put(2,  "Февраль"   );
        put(3,  "Март"      );
        put(4,  "Апрель"    );
        put(5,  "Май"       );
        put(6,  "Июнь"      );
        put(7,  "Июль"      );
        put(8,  "Август"    );
        put(9,  "Сентябрь"  );
        put(10, "Октябрь"   );
        put(11, "Ноябрь"    );
        put(12, "Декабрь"   );
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

    @Override
    public int get(int field) {
        // Java numerate months starts from 0
        if (field == Calendar.MONTH)
            return super.get(field) + 1;
        return super.get(field);
    }
}
