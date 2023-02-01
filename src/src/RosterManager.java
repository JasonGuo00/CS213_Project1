package src;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RosterManager {

    private Roster roster;

    //UI Class -> Reads, processes, and displays the results on input lines into the console.
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

    // Method needs to separate the Operation Code from the Data Tokens
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
    private void remove(String fname, String lname, String date) {
        Student s = new Student(new Profile(lname, fname, new Date(date)), Major.CS, 0);
        if(roster.remove(s)) {
            System.out.println(fname + " " + lname + " " + date + " removed from the roster.");
        }
        else {
            System.out.println(fname + " " + lname + " " + date + " is not on the roster.");
        }
    }
    private void pname() {
        System.out.println("* Student roster sorted by last name, first name, DOB **");
        roster.print();
        System.out.println("* end of roster **");
    }
    private void pschool() {
        System.out.println("* Student roster sorted by school, major **");
        roster.printBySchoolMajor();
        System.out.println("* end of roster **");
    }
    private void pstanding(){
        System.out.println("* Student roster sorted by standing **");
        roster.printByStanding();
        System.out.println("* end of roster **");
    }
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
