package src;
import java.util.Scanner;

public class RosterManager {
    //UI Class -> Reads, processes, and displays the results on input lines into the console.
    public void run() {
        Scanner obj = new Scanner(System.in);
        boolean stop = false;
        while(!stop) {
            System.out.println("Roster Manager running...");
            String input = obj.nextLine();
            if(input.equals("Q")) {
                stop = true;
            }
            else {
                interpreter(input);
            }
        }
        System.out.println("Roster Manager terminated.");
    }

    // Method needs to separate the Operation Code from the Data Tokens
    public void interpreter(String input) {
        switch(input) {
            case "A":
                System.out.println("Add student to be implemented.");
                break;
            case "R":
                System.out.println("Remove to be implemented.");
                break;
            case "P":
                System.out.println("Display by Last Name, First Name, and DOB to be implemented.");
                break;
            case "PS":
                System.out.println("Display by Standing to be implemented.");
                break;
            case "PC":
                System.out.println("Display by School and Major to be implemented.");
                break;
            case "L":
                System.out.println("List students in a Specified School, sorted, to be implemented.");
                break;
            case "C":
                System.out.println("Change a student's major to be implemented.");
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }

    private void add() {

    }
    private void remove() {

    }
    private void pname() {

    }
    private void pschool() {

    }
    private void pstanding(){

    }
    private void list() {

    }
    private void change() {
        
    }
}
