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
        while(!stop) {
            System.out.println("Roster Manager running...");
            StringTokenizer tokens = new StringTokenizer(obj.nextLine());
            String input = tokens.nextToken();
            if(input.equals("Q")) {
                stop = true;
            }
            else {
                interpreter(input, tokens);
            }
        }
        System.out.println("Roster Manager terminated.");
    }

    // Method needs to separate the Operation Code from the Data Tokens
    private void interpreter(String input, StringTokenizer tokens) {
        switch(input) {
            case "A":
                if(tokens.countTokens() == 5) {
                    add(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), Integer.parseInt(tokens.nextToken()));
                }
                else {
                    System.out.println("Invalid number of arguments.");
                }
                break;
            case "R":
                if(tokens.countTokens() == 3) {
                    remove(tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
                    System.out.println("Remove to be implemented.");
                }
                else {
                    System.out.println("Invalid number of arguments.");
                }

                break;
            case "P":
                pname();
                break;
            case "PS":
                pschool();
                break;
            case "PC":
                pstanding();
                break;
            case "L":
                if(tokens.countTokens() == 1) {
                    list(tokens.nextToken());
                    System.out.println("List students in a Specified School, sorted, to be implemented.");
                }
                else {
                    System.out.println("Invalid number of arguments.");
                }
                break;
            case "C":
                if(tokens.countTokens() == 4) {
                    change(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
                    System.out.println("Change a student's major to be implemented.");
                }
                else {
                    System.out.println("Invalid number of arguments.");
                }
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }

    private void add(String fname, String lname, String date, String major, int credits) {
        Date d = new Date(date);
        if(d.isValid() && !d.isUnderage() && credits >= 0 && getMajor(major) != null) {
            Student s = new Student(new Profile(lname, fname, d), getMajor(major), credits);
            if(!roster.contains(s)) {
                roster.add(s);
                System.out.println("Successfully added: " + fname + " " + lname + " " + date + " " + major + " " + credits);
            }
        }
        else {
            System.out.println("Student was unable to be added.");
        }
    }
    private void remove(String fname, String lname, String date) {
        Student s = new Student(new Profile(lname, fname, new Date(date)), Major.CS, 0);
        System.out.println(roster.contains(s));
        if(roster.remove(s)) {
            System.out.println(fname + " " + lname + " was successfully removed.");
        }
        else {
            System.out.println("Failed to remove: student is not on the roster.");
        }
    }
    private void pname() {
        roster.print();
    }
    private void pschool() {
        roster.printBySchoolMajor();
    }
    private void pstanding(){
        roster.printByStanding();
    }
    private void list(String school) {

    }
    private void change(String fname, String lname, String date, String major) {

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
