package src;
import java.util.Calendar;

/**
 * Date object tracks a year, month, and day, all numerically.
 * @author Jason Guo, Russel Rivera
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Default constructor: creates the Date object based on the current calendar date
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + Constants.CALENDAR_MONTH_OFFSET;
        day = calendar.get(Calendar.DATE);
    }

    /**
     * Constructor that takes in a string that describes a date.  Initiates
     * the year, month, and day based on the date inputted.
     * @param date String in the form of mm/dd/yyyy that describes a date
     */
    public Date(String date) {
        String[] list = date.split("/");
        month = Integer.parseInt(list[Constants.MONTH]);
        day = Integer.parseInt(list[Constants.DAY]);
        year = Integer.parseInt(list[Constants.YEAR]);
    }

    /**
     * Override of the equals method.
     * Checks if two dates are equals.
     * @param obj Date to be compared to
     * @return Returns true if the two dates are the same, false otherwise
     */
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

    /**
     * Overrides the compareTo method.
     * Compares dates based on whether one date comes before, after, or is the same as the other.
     * @param date the object to be compared.
     * @return Returns -1, 0, or 1 based on whether the first date comes before, is equal to, or after the other.
     */
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

    /**
     * Overrides the toString method.
     * @return Returns a string of the date in the form of mm/dd/yyyy
     */
    @Override
    public String toString() {
        return (month + "/" + day + "/" + year);
    }

    /**
     * Checks if a date is valid calendar date.
     * Checks for leap years, if the day is within the bounds of the month, whether the date is in the future.
     * @return Returns true if the date is valid, false otherwise.
     */
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

    /**
     * Checks if a student is younger than 16 based on their date of birth and the current date.
     * @return Returns true if the student is younger than 16, false otherwise.
     */
    public boolean isUnderage() {
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.YEAR) - year > Constants.MINAGE) {
            return false;
        }
        else if(calendar.get(Calendar.YEAR) - year < Constants.MINAGE) {
            return true;
        }
        else {
            if(calendar.get(Calendar.MONTH) + Constants.CALENDAR_MONTH_OFFSET < month) {
                return true;
            }
            else if(calendar.get(Calendar.MONTH) + Constants.CALENDAR_MONTH_OFFSET > month) {
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

    /**
     * Checks if a given date is a leap year.
     * @return Returns true if a given date is a leap year, false otherwise
     */
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

    /**
     * Testbed Main.
     * Used to test functionality of the Date objects.
     * @param args
     */
    public static void main(String[] args) {

    }

}
