package src;
import java.util.Calendar;

public class Date {
    private int year;
    private int month;
    private int day;

    public Date() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + Constants.CALENDAR_MONTH_OFFSET;
        day = calendar.get(Calendar.DATE);
    }

    public Date(String date) {
        String[] list = date.split("/");
        month = Integer.parseInt(list[Constants.MONTH]);
        day = Integer.parseInt(list[Constants.DAY]);
        year = Integer.parseInt(list[Constants.YEAR]);
    }

    public boolean isValid() {
        Calendar calendar = Calendar.getInstance();
        // Check if input date is possible based on common sense
        if(year > calendar.get(Calendar.YEAR) || day > 31 || day < 1 || month > 12 || month < 1) {
            return false;
        }
        // Check if the input date is in the future
        if(year == calendar.get(Calendar.YEAR)) {
            if(month > calendar.get(Calendar.MONTH) + Constants.CALENDAR_MONTH_OFFSET) {
                return false;
            }
            else {
                if(day > calendar.get(Calendar.DATE)) {
                    return false;
                }
            }
        }
        // Check dates with 30 days instead of 31
        if(month == Constants.APRIL || month == Constants.JUNE || month == Constants.SEP || month == Constants.NOV) {
            if(day > 30) {
                return false;
            }
        }
        // Check February
        if(month == Constants.FEB) {
            if(isLeapYear()) {
                if(day > 29) {
                    return false;
                }
            }
            else {
                if(day > 28) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isLeapYear() {
        if(year % 4 == 0) {
            if(year % 100 == 0) {
                if(year % 400 == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
//    public void print() {
//        System.out.println(month + "/" + day + "/" + year);
//        System.out.println(isValid());
//    }


}
