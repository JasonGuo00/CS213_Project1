package src;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Manages the I/O of the program.
 * Interprets the commands that the user gives and performs actions based on those valid commands.
 * Handles invalid inputs and displays accordingly.
 * @author Jason Guo, Russel Rivera
 */
public class RosterManager {

    private Roster roster;

    //UI Class -> Reads, processes, and displays the results on input lines into the console.

    /**
     * Method that starts the program by creating the scanner object and allowing inputs.
     */
    public void run() {
        roster = new Roster();
        Scanner obj = new Scanner(System.in);
        boolean stop = false;
        System.out.println("Roster Manager running...");
        while(!stop) {
            String line = obj.nextLine();
            if(!line.isEmpty()) {
                StringTokenizer tokens = new StringTokenizer(line);
                String input = tokens.nextToken();
                if(input.equals("Q")) {
                    stop = true;
                }
                else {
                    interpreter(input, tokens);
                }
            }
        }
        System.out.println("Roster Manager terminated.");
    }

    /**
     * Interprets the command inputted.
     * Figures out which helper methods to call based on the inputted command and uses the remaining tokens in the input line to
     * fill in the parameters of those methods.
     * @param input Command that the user wishes to call
     * @param tokens Remaining inputs from the line that the user inputted, use during method calls
     */
    private void interpreter(String input, StringTokenizer tokens) {
        switch(input) {
            case "A":
                if(tokens.countTokens() == Constants.ARGUMENTS_A) {
                    add(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
                }
                else {
                    System.out.println("Invalid number of arguments.");
                }
                break;
            case "R":
                if(tokens.countTokens() == Constants.ARGUMENTS_R) {
                    remove(tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
                }
                else {
                    System.out.println("Invalid number of arguments.");
                }

                break;
            case "P":
                if(roster.getSize() == 0) {
                    System.out.println("Student Roster is empty!");
                }
                else {
                    pname();
                }
                break;
            case "PS":
                if(roster.getSize() == 0) {
                    System.out.println("Student Roster is empty!");
                }
                else {
                    pstanding();
                }
                break;
            case "PC":
                if(roster.getSize() == 0) {
                    System.out.println("Student Roster is empty!");
                }
                else {
                    pschool();
                }
                break;
            case "L":
                if(tokens.countTokens() == Constants.ARGUMENTS_L) {
                    if(roster.getSize() == 0) {
                        System.out.println("Student Roster is empty!");
                    }
                    else {
                        list(tokens.nextToken());
                    }
                }
                else {
                    System.out.println("Invalid number of arguments.");
                }
                break;
            case "C":
                if(tokens.countTokens() == Constants.ARGUMENTS_C) {
                    change(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
                }
                else {
                    System.out.println("Invalid number of arguments.");
                }
                break;
            default:
                System.out.println(input + " is an invalid command!");
                break;
        }
    }

    /**
     * Adds a Student object to the Roster array
     * @param fname First name of the student
     * @param lname Last name of the student
     * @param date Date of birth of the student
     * @param major Student's major
     * @param cr Number of credits the student has completed (in a String)
     */
    private void add(String fname, String lname, String date, String major, String cr) {
        Date d = new Date(date);
        int credits = -1;
        if(cr.matches("[0-9]+") || cr.matches("-?[0-9]+")) {
            credits = Integer.parseInt(cr);
        }

        if(!d.isValid()) {
            System.out.println("DOB invalid: " + date + " not a valid calendar date!");
        }
        else if(d.isUnderage()) {
            System.out.println("DOB invalid: " + date + " younger than 16 years old.");
        }
        else if(!cr.matches("[0-9]+") && !cr.matches("-?[0-9]+")) {
            System.out.println("Credits completed invalid: not an integer!");
        }
        else if(credits < 0) {
            System.out.println("Credit completed invalid: cannot be negative!");
        }
        else if(getMajor(major) == null) {
            System.out.println("Major code invalid: " + major);
        }
        else {
            Student s = new Student(new Profile(lname, fname, d), getMajor(major), credits);
            if(!roster.contains(s)) {
                roster.add(s);
                System.out.println(fname + " " + lname + " " + date + " added to the roster.");
            }
            else {
                System.out.println(fname + " " + lname + " " + date + " is already in the roster.");
            }
        }
    }

    /**
     * Removes target Student object from the Roster array
     * @param fname First name of the student
     * @param lname Last name of the student
     * @param date Date of birth of the student
     */
    private void remove(String fname, String lname, String date) {
        Student s = new Student(new Profile(lname, fname, new Date(date)), Major.CS, 0);
        if(roster.remove(s)) {
            System.out.println(fname + " " + lname + " " + date + " removed from the roster.");
        }
        else {
            System.out.println(fname + " " + lname + " " + date + " is not on the roster.");
        }
    }

    /**
     * Displays students on the roster sorted by last name, first name, and date of birth.
     */
    private void pname() {
        System.out.println("* Student roster sorted by last name, first name, DOB **");
        roster.print();
        System.out.println("* end of roster **");
    }

    /**
     * Displays students on the roster sorted by school and major.
     */
    private void pschool() {
        System.out.println("* Student roster sorted by school, major **");
        roster.printBySchoolMajor();
        System.out.println("* end of roster **");
    }

    /**
     * Displays students on the roster sorted by their standing.
     */
    private void pstanding(){
        System.out.println("* Student roster sorted by standing **");
        roster.printByStanding();
        System.out.println("* end of roster **");
    }

    /**
     * Displays students on the roster that are within the target school.
     * @param school School that you would like to see the student's of
     */
    private void list(String school) {
        if(!school.equalsIgnoreCase(Major.CS.school) && !school.equalsIgnoreCase(Major.EE.school) && !school.equalsIgnoreCase(Major.BAIT.school) && !school.equalsIgnoreCase(Major.ITI.school)) {
            System.out.println("School doesn't exist: " + school);
        }
        else {
            System.out.println("* Students in " + school + " *");
            roster.printBySchool(school);
            System.out.println("* end of roster **");
        }
    }

    /**
     * Changes the major of the Student object with the given first name, last name, and date of birth.
     * @param fname First name of the student to be accessed.
     * @param lname Last name of the student to be accessed.
     * @param date Date of birth of the student to be accessed.
     * @param major New major for the student.
     */
    private void change(String fname, String lname, String date, String major) {
        if(getMajor(major) == null) {
            System.out.println("Major code invalid: " + major);
        }
        else {
            Student s = new Student(new Profile(lname, fname, new Date(date)), getMajor(major), 0);
            if(roster.changeMajor(s, getMajor(major)) != Constants.NOT_FOUND) {
                System.out.println(fname + " " + lname + " " + date + " major changed to " + major);
            }
            else {
                System.out.println(fname + " " + lname + " " + date + " is not in the roster.");
            }
        }
    }

    /**
     * Obtains the Major enum based on the inputted string.
     * Returns a null if the input does not correspond to an existing Major.
     * @param major String of the major that is being accessed
     * @return Returns the enum of the desired major, or null if it doesn't exist
     */
    private Major getMajor(String major) {
        if(major.equalsIgnoreCase("CS")) {
            return Major.CS;
        }
        else if(major.equalsIgnoreCase("MATH")) {
            return Major.MATH;
        }
        else if(major.equalsIgnoreCase("EE")) {
            return Major.EE;
        }
        else if(major.equalsIgnoreCase("ITI")) {
            return Major.ITI;
        }
        else if(major.equalsIgnoreCase("BAIT")) {
            return Major.BAIT;
        }
        else {
            return null;
        }
    }
}
