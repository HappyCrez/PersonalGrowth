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
    
    public void setDate(int date) {
        this.date = date;
    }
    public void setMonth(int month) {
        this.month = month - 1;
    }
    public void setYear(int year) {
        this.year = year ;
    }

    public String toString() {
        String month = (this.month + 1) < 10 ? String.format("0%d", this.month + 1) : String.format("%d", this.month + 1);
        String date = this.date < 10 ? String.format("0%d", this.date) : String.format("%d", this.date);
        System.out.println(String.format("%s.%s.%d", date, month, this.year));
        return String.format("%s.%s.%d", date, month, this.year);
    }

    public static Date now() {
        LocalDate date = LocalDate.now();
        return new Date(date.getDayOfMonth(), date.getMonthValue() - 1, date.getYear());
    }
}
