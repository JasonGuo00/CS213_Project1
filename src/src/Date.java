package src;
import java.util.Calendar;

public class Date implements Comparable<Date> {
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Date) {
            Date date = (Date)obj;
            if(year == date.year && day == date.day && month == date.month) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Date date) {
        // If the dates are equal
        if(year == date.year && day == date.day && month == date.month) {
            return Constants.EQUALS;
        }

        // Same year
        if(date.year > year) {
            return Constants.LESSER;
        }
        else if(date.year < year) {
            return Constants.GREATER;
        }
        else {
            // Same date
            if(date.month > month) {
                return Constants.LESSER;
            }
            else if(date.month < month) {
                return Constants.GREATER;
            }
            else {
                if(date.day > day) {
                    return Constants.LESSER;
                }
                else {
                    return Constants.GREATER;
                }
            }
        }
    }
    @Override
    public String toString() {
        return (month + "/" + day + "/" + year);
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

    public boolean isUnderage() {
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.YEAR) - year > Constants.MINAGE) {
            return false;
        }
        else if(calendar.get(Calendar.YEAR) - year < Constants.MINAGE) {
            return true;
        }
        else {
            if(calendar.get(Calendar.MONTH) < month) {
                return true;
            }
            else if(calendar.get(Calendar.MONTH) > month) {
                return false;
            }
            else {
                if(calendar.get(Calendar.DATE) < day) {
                    return true;
                }
                return false;
            }
        }
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

    public static void main(String[] args) {

    }

}
