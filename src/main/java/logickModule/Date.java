package logickModule;

import java.time.LocalDate;

public class Date {
    int date, month, year;

    public Date (int date, int month, int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public int getDate() {
        return date;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    
    public int setDate() {
        return date;
    }
    public int setMonth() {
        return month;
    }
    public int setYear() {
        return year;
    }

    public String toString() {
        String month = (this.month + 1) < 10 ? String.format("0%i", this.month + 1) : String.format("%i", this.month + 1);
        String date = this.date < 10 ? String.format("0%i", this.date) : String.format("%i", this.date);
        return String.format("%s.%s.%s", date, month, this.year);
    }

    public static Date now() {
        LocalDate date = LocalDate.now();
        return new Date(date.getDayOfMonth(), date.getMonthValue() - 1, date.getYear());
    }
}
