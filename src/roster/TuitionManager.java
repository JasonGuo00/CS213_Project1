package roster;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Manages the I/O of the program.
 * Interprets the commands that the user gives and performs actions based on those valid commands.
 * Handles invalid inputs and displays accordingly.
 * @author Jason Guo, Russel Rivera
 */
public class TuitionManager {

    private Roster roster;
    private Enrollment enrollment;

    //UI Class -> Reads, processes, and displays the results on input lines into the console.

    /**
     * Method that starts the program by creating the scanner object and allowing inputs.
     */
    public void run() {
        roster = new Roster();
        enrollment = new Enrollment();
        Scanner obj = new Scanner(System.in);
        boolean stop = false;
        System.out.println("Tuition Manager running...");
        while (!stop) {
            String line = obj.nextLine();
            if (!line.isEmpty()) {
                StringTokenizer tokens = new StringTokenizer(line);
                String input = tokens.nextToken();
                if (input.equals("Q")) {
                    stop = true;
                } else {
                    interpreter(input, tokens);
                }
            }
        }
        System.out.println("Tuition Manager terminated.");
    }

    /**
     * Interprets the command inputted.
     * Figures out which helper methods to call based on the inputted command and uses the remaining tokens in the input line to
     * fill in the parameters of those methods.
     *
     * @param input  Command that the user wishes to call
     * @param tokens Remaining inputs from the line that the user inputted, use during method calls
     */
    private void interpreter(String input, StringTokenizer tokens) {
        switch (input) {
            case "A" -> processA(tokens);
            case "AR" -> processAR(tokens);
            case "AN" -> processAN(tokens);
            case "AT" -> processAT(tokens);
            case "AI" -> processAI(tokens);
            case "R" -> processR(tokens);
            case "P" -> processP();
            case "PS" -> processPS();
            case "PC" -> processPC();
            case "L" -> processL(tokens);
            case "C" -> processC(tokens);
            case "LS" -> processLS(tokens);
            case "E" -> processE(tokens);
            case "D" -> processD(tokens);
            case "S" -> processS(tokens);
            case "PE" -> processPE(tokens);
            case "PT" -> processPT(tokens);
            case "SE" -> processSE(tokens);
            default -> System.out.println(input + " is an invalid command!");
        }
    }

    /**
     * Processes A commands, separating arguments and passing them to the correct function
     * @param tokens StringTokenizer object holding an input line
     */
    void processA(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_AR) {
            String token1 = tokens.nextToken();
            String token2 = tokens.nextToken();
            String token3 = tokens.nextToken();
            String token4 = tokens.nextToken();
            String token5 = tokens.nextToken();

            addR(token1, token2, token3, token4, token5, true);
        } else if (tokens.countTokens() == Constants.ARGUMENTS_AI_ABROAD) {
            String token1 = tokens.nextToken();
            String token2 = tokens.nextToken();
            String token3 = tokens.nextToken();
            String token4 = tokens.nextToken();
            String token5 = tokens.nextToken();
            String token6 = tokens.nextToken();

            if (token6.equalsIgnoreCase("true") || token6.equalsIgnoreCase("false")) {
                addI(token1, token2, token3, token4, token5, token6, true);
            } else {
                addT(token1, token2, token3, token4, token5, token6, true);
            }
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes AR command: checks the number of arguments is valid and runs the corresponding add() method.
     * @param tokens StringTokenizer object holding an input line.
     */
    void processAR(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_AR) {
            addR(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), true);
        } else if (tokens.countTokens() < Constants.ARGUMENTS_AR) {
            System.out.println("Missing data in line command.");
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes AN command: checks the number of arguments is valid and runs the corresponding add() method.
     * @param tokens StringTokenizer object holding an input line.
     */
    void processAN(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_AN) {
            addN(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), true);
        } else if (tokens.countTokens() < Constants.ARGUMENTS_AN) {
            System.out.println("Missing data in line command.");
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes AT command: checks the number of arguments is valid and runs the corresponding add() method.
     * @param tokens StringTokenizer object holding an input line.
     */
    void processAT(StringTokenizer tokens) {
        if (tokens.countTokens() == 6) {
            addT(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), true);
        } else if (tokens.countTokens() == 5) {
            System.out.println("Missing the state code.");
        } else if (tokens.countTokens() == 3) {
            System.out.println("Missing data in command line.");
        } else {
            System.out.println("Missing data in line command.");
        }
    }

    /**
     * Processes AI command: checks the number of arguments is valid and runs the corresponding add() method.
     * @param tokens StringTokenizer object holding an input line.
     */
    void processAI(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_AI_NO_ABROAD) {
            addI(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), "false", true);
        } else if (tokens.countTokens() == Constants.ARGUMENTS_AI_ABROAD) {
            addI(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), true);
        } else if (tokens.countTokens() < Constants.ARGUMENTS_AI_NO_ABROAD) {
            System.out.println("Missing data in line command.");
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes R command: checks the number of arguments is valid and runs the remove() method.
     * @param tokens StringTokenizer object holding an input line.
     */
    void processR(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_R) {
            remove(tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes P command: checks the number of arguments is valid and runs the corresponding print() method.
     */
    void processP() {
        if (roster.getSize() == 0) {
            System.out.println("Student roster is empty!");
        } else {
            pname();
        }
    }

    /**
     * Processes PS command: checks the number of arguments is valid and runs the corresponding print() method.
     */
    void processPS() {
        if (roster.getSize() == 0) {
            System.out.println("Student roster is empty!");
        } else {
            pstanding();
        }
    }

    /**
     * Processes PC command: checks the number of arguments is valid and runs the corresponding print() method.
     */
    void processPC() {
        if (roster.getSize() == 0) {
            System.out.println("Student roster is empty!");
        } else {
            pschool();
        }
    }

    /**
     * Processes L command: checks the number of arguments is valid and runs the list() method.
     */
    void processL(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_L) {
            if (roster.getSize() == 0) {
                System.out.println("Student roster is empty!");
            } else {
                list(tokens.nextToken());
            }
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes C command: checks the number of arguments is valid and runs the change() method.
     */
    void processC(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_C) {
            change(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes LS command: checks the number of arguments is valid and runs the addList() method.
     */
    void processLS(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_LS) {
            addList(tokens.nextToken());
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes E command: checks the number of arguments is valid and runs the enroll() method.
     */
    void processE(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_E) {
            enroll(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
        } else if (tokens.countTokens() < Constants.ARGUMENTS_E) {
            System.out.println("Missing data in line command.");
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes D command: checks the number of arguments is valid and runs the dropEnroll() method.
     */
    void processD(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_D) {
            dropEnroll(tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes S command: checks the number of arguments is valid and runs the awardScholarship() method.
     */
    void processS(StringTokenizer tokens) {
        if (tokens.countTokens() < Constants.ARGUMENTS_PROFILE) {
            System.out.println("Missing data in line command.");
        } else {
            String fname = tokens.nextToken();
            String lname = tokens.nextToken();
            String date = tokens.nextToken();


            if (!checkDateValid(date)) {
                return;
            }

            Date d = new Date(date);
            Profile profile = new Profile(lname, fname, d);
            Student student = roster.getStudent(new Resident(profile, Major.CS, 0));

            if (student == null) {
                System.out.println(profile + " is not in the roster.");
                return;
            }

            if (tokens.countTokens() == Constants.ARGUMENTS_S - Constants.ARGUMENTS_PROFILE) {
                awardScholarship(fname, lname, date, tokens.nextToken());
            } else if (tokens.countTokens() < Constants.ARGUMENTS_S - Constants.ARGUMENTS_PROFILE) {
                System.out.println("Missing data in line command.");
            } else {
                System.out.println("Invalid number of arguments.");
            }
        }
    }

    /**
     * Processes PE command: checks the number of arguments is valid and runs the printEnrollment() method.
     */
    void processPE(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_PE) {
            if (enrollment.getSize() == 0) {
                System.out.println("Enrollment is empty!");
            } else {
                printEnrollment();
            }
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes PT command: checks the number of arguments is valid and runs the printTuition() method.
     */
    void processPT(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_PT) {
            if (roster.getSize() == 0) {
                System.out.println("Student roster is empty!");
            } else {
                printTuition();
            }
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Processes SE command: checks the number of arguments is valid and runs the semesterEnd() method.
     */
    void processSE(StringTokenizer tokens) {
        if (tokens.countTokens() == Constants.ARGUMENTS_SE) {
            semesterEnd();
        } else {
            System.out.println("Invalid number of arguments.");
        }
    }

    /**
     * Adds a Resident object to the Roster array
     * @param fname First name of the student
     * @param lname Last name of the student
     * @param date Date of birth of the student
     * @param major Student's major
     * @param cr Number of credits the student has completed (in a String)
     */
    private void addR(String fname, String lname, String date, String major, String cr, boolean print) {
        if (checkDateValid(date) && checkMajorValid(major) && checkCreditsCompletedValid(cr)) {
            Date d = new Date(date);
            int credits = Integer.parseInt(cr);

            Student s = new Resident(new Profile(lname, fname, d), getMajor(major), credits);

            if(!roster.contains(s)) {
                roster.add(s);

                if (print)
                    System.out.println(fname + " " + lname + " " + date + " added to the roster.");
            } else if (print) {
                System.out.println(fname + " " + lname + " " + date + " is already in the roster.");
            }
        }
    }

    /**
     * Adds a NonResident object to the Roster array
     * @param fname First name of the student
     * @param lname Last name of the student
     * @param date Date of birth of the student
     * @param major Student's major
     * @param cr Number of credits the student has completed (in a String)
     */
    private void addN(String fname, String lname, String date, String major, String cr, boolean print) {
        if (checkDateValid(date) && checkMajorValid(major) && checkCreditsCompletedValid(cr)) {
            Date d = new Date(date);
            int credits = Integer.parseInt(cr);

            Student s = new NonResident(new Profile(lname, fname, d), getMajor(major), credits);

            if(!roster.contains(s)) {
                roster.add(s);

                if (print)
                    System.out.println(fname + " " + lname + " " + date + " added to the roster.");
            } else if (print) {
                System.out.println(fname + " " + lname + " " + date + " is already in the roster.");
            }
        }
    }

    /**
     * Adds a Tristate object to the Roster array
     * @param fname First name of the student
     * @param lname Last name of the student
     * @param date Date of birth of the student
     * @param major Student's major
     * @param cr Number of credits the student has completed (in a String)
     */
    private void addT(String fname, String lname, String date, String major, String cr, String state, boolean print) {
        if (!state.equalsIgnoreCase("NY") && !state.equalsIgnoreCase("CT")) {
            System.out.println(state + ": Invalid state code.");
            return;
        }

        if (checkDateValid(date) && checkMajorValid(major) && checkCreditsCompletedValid(cr)) {
            Date d = new Date(date);
            int credits = Integer.parseInt(cr);

            Student s = new TriState(new Profile(lname, fname, d), getMajor(major), credits, state.toUpperCase());

            if(!roster.contains(s)) {
                roster.add(s);

                if (print)
                    System.out.println(fname + " " + lname + " " + date + " added to the roster.");
            } else if (print) {
                System.out.println(fname + " " + lname + " " + date + " is already in the roster.");
            }
        }
    }

    /**
     * Adds an International object to the Roster array
     * @param fname First name of the student
     * @param lname Last name of the student
     * @param date Date of birth of the student
     * @param major Student's major
     * @param cr Number of credits the student has completed (in a String)
     */
    private void addI(String fname, String lname, String date, String major, String cr, String abroad, boolean print) {
        boolean abroad_bool;

        if (abroad.equalsIgnoreCase("true") || abroad.equalsIgnoreCase("false")) {
            abroad_bool = Boolean.parseBoolean(abroad);
        } else {
            System.out.println("Invalid token: " + abroad);
            return;
        }

        if (checkDateValid(date) && checkMajorValid(major) && checkCreditsCompletedValid(cr)) {
            Date d = new Date(date);
            int credits = Integer.parseInt(cr);

            Student s = new International(new Profile(lname, fname, d), getMajor(major), credits, abroad_bool);

            if(!roster.contains(s)) {
                roster.add(s);

                if (print)
                    System.out.println(fname + " " + lname + " " + date + " added to the roster.");
            } else if (print) {
                System.out.println(fname + " " + lname + " " + date + " is already in the roster.");
            }
        }
    }

    /**
     * Reads a list of students along with their residence status.
     * Adds said students into the roster using the add method that corresponds with their residence type.
     * @param filename Name of the text file that contains the students.
     */
    private void addList(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                StringTokenizer tokens = new StringTokenizer(line, ",");
                String status = tokens.nextToken();
                switch (status) {
                    case "R" ->
                        addR(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), false);
                    case "N" ->
                        addN(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), false);
                    case "T" ->
                        addT(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), false);
                    case "I" -> {
                        if (tokens.countTokens() == Constants.ARGUMENTS_AI_NO_ABROAD) {
                            addI(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), "false", false);
                        } else if (tokens.countTokens() == Constants.ARGUMENTS_AI_ABROAD) {
                            addI(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), false);
                        }
                    }
                    default -> {
                    }
                }
            }

            System.out.println("Students loaded to the roster.");
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + filename);
        }
    }

    /**
     * Removes target Student object from the Roster array
     * @param fname First name of the student
     * @param lname Last name of the student
     * @param date Date of birth of the student
     */
    private void remove(String fname, String lname, String date) {
        Resident s = new Resident(new Profile(lname, fname, new Date(date)), Major.CS, 0);
        if(roster.remove(s)) {
            System.out.println(fname + " " + lname + " " + date + " removed from the roster.");
        }
        else {
            System.out.println(fname + " " + lname + " " + date + " is not in the roster.");
        }
    }

    /**
     * Displays students on the roster sorted by last name, first name, and date of birth.
     */
    private void pname() {
        System.out.println("** Student roster sorted by last name, first name, DOB **");
        roster.print();
        System.out.println("* end of roster *");
    }

    /**
     * Displays students on the roster sorted by school and major.
     */
    private void pschool() {
        System.out.println("** Student roster sorted by school, major **");
        roster.printBySchoolMajor();
        System.out.println("* end of roster *");
    }

    /**
     * Displays students on the roster sorted by their standing.
     */
    private void pstanding(){
        System.out.println("** Student roster sorted by standing **");
        roster.printByStanding();
        System.out.println("* end of roster *");
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
            Student s = new Resident(new Profile(lname, fname, new Date(date)), getMajor(major), 0);
            if(roster.changeMajor(s, getMajor(major))) {
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

    /**
     * Check whether the date is a valid calendar date, or whether the student is under 16.
     * @param date Student's D.O.B.
     * @return true if the date is a valid calendar date and student is 16 or older.  False otherwise
     */
    private boolean checkDateValid(String date) {
        Date d = new Date(date);

        if (!d.isValid()) {
            System.out.println("DOB invalid: " + date + " not a valid calendar date!");
            return false;
        } else if (d.isUnderage()) {
            System.out.println("DOB invalid: " + date + " younger than 16 years old.");
            return false;
        }

        return true;
    }

    /**
     * Check whether a input string can be read as an integer, and whether that integer is positive or negative.
     * @param i Input String.
     * @return Whether the String is a readable integer: NOT_INT(-1), POS_INT(0), NEG_INT(-2)
     */
    private int checkIntValid(String i) {
        if (!i.matches("-?[0-9]+")) {
            return Constants.NOT_INT;
        } else if (i.matches("-[0-9]+")) {
            return Constants.NEG_INT;
        }

        return Constants.POS_INT;
    }

    /**
     * Check if the creditsCompleted property of Students is satisfied (is a positive integer).
     * @param i Input String representing the credits student has completed.
     * @return true or false.
     */
    private boolean checkCreditsCompletedValid(String i) {
        int credits_valid = checkIntValid(i);

        if (credits_valid == Constants.NOT_INT) {
            System.out.println("Credits completed invalid: not an integer!");
            return false;
        } else if (credits_valid == Constants.NEG_INT) {
            System.out.println("Credits completed invalid: cannot be negative!");
            return false;
        }

        return true;
    }

    /**
     * Check whether the credits enrolled by the student is a valid integer.
     * @param i Input String representing the credits enrolled by a student.
     * @return true or falses.
     */
    private boolean checkCreditsEnrolledValid(String i) {
        int credits_valid = checkIntValid(i);

        if (credits_valid == Constants.NOT_INT) {
            System.out.println("Credits enrolled is not an integer.");
            return false;
        } else if (credits_valid == Constants.NEG_INT) {
            System.out.println("Credits enrolled cannot be negative.");
            return false;
        }

        return true;
    }

    /**
     * Check that the input major is a valid major.
     * @param major String representing the student's chosen major.
     * @return true or false.
     */
    private boolean checkMajorValid(String major) {
        if (getMajor(major) == null) {
            System.out.println("Major code invalid: " + major);
            return false;
        }

        return true;
    }

    /**
     * Return the residence status of the student. (Residence, NonResidence, TriState, International (Abroad / Not Abroad))
     * @param student The student whose status is being checked.
     * @return String representing the student's residence status.
     */
    private String getStatus(Student student) {
        if (student instanceof Resident) {
            return "Resident";
        } else if (student instanceof TriState) {
            return "Tri-state " + ((TriState) student).getState();
        } else if (student instanceof International) {
            if (((International) student).isStudyAbroad()) {
                return "International studentstudy abroad";
            } else {
                return "International student";
            }
        } else if (student instanceof NonResident) {
            return "Non-Resident";
        } else {
            return "";
        }
    }

    /**
     * Enroll the student with the given name and D.O.B. with the number of credits inputted.
     * Checks whether the input parameters are valid and the student exists in the roster before enrolling.
     * @param fname First name of student.
     * @param lname Last name of student.
     * @param date D.O.B. of student.
     * @param cr Number of credits student is enrolling in.
     */
    private void enroll(String fname, String lname, String date, String cr) {
        if (!checkCreditsEnrolledValid(cr) || !checkDateValid(date)) {
            return;
        }

        Date d = new Date(date);
        int credits = Integer.parseInt(cr);
        Profile profile = new Profile(lname, fname, d);
        Student student = roster.getStudent(new Resident(profile, Major.CS, 0));

        if (student == null) {
            System.out.println("Cannot enroll: " + profile + " is not in the roster.");
            return;
        }

        if (!roster.checkValid(student, credits)) {
            System.out.println("(" + getStatus(student) + ") " + credits + ": invalid credit hours.");
            return;
        }

        EnrollStudent enroll_student = new EnrollStudent(lname, fname, d, credits);
        enrollment.add(enroll_student);
        System.out.println(enroll_student);
    }

    /**
     * Drop target student's enrollment.
     * @param fname First name of target student.
     * @param lname Last name of target student.
     * @param date D.O.B. of target student.
     */
    private void dropEnroll(String fname, String lname, String date) {
        if (!checkDateValid(date)) {
            return;
        }

        Date d = new Date(date);
        EnrollStudent student = new EnrollStudent(lname, fname, d, 0);

        if (enrollment.getStudent(student) == null) {
            System.out.println(student.getProfile().toString() + " is not enrolled.");
            return;
        }

        enrollment.remove(student);
        System.out.println(student.getProfile().toString() + " dropped.");
    }

    /**
     * Award a Resident student a specified amount of scholarships.
     * @param fname First name of student.
     * @param lname Last name of student.
     * @param date D.O.B. of student.
     * @param scholarship String representing the scholarship amount.
     */
    private void awardScholarship(String fname, String lname, String date, String scholarship) {

        if (!checkDateValid(date)) {
            return;
        }

        if (checkIntValid(scholarship) < 0) {
            System.out.println("Amount is not an integer.");
            return;
        }

        Date d = new Date(date);
        int scholarship_value = Integer.parseInt(scholarship);

        if (scholarship_value < Constants.SCHOLARSHIP_MIN || scholarship_value > Constants.SCHOLARSHIP_MAX) {
            System.out.println(scholarship_value + ": invalid amount.");
            return;
        }

        Student student = roster.getStudent(new Resident(new Profile(lname, fname, d), Major.CS, 0));
        EnrollStudent student_enroll = enrollment.getStudent(new EnrollStudent(lname, fname, d, 0));

        if (student_enroll == null) {
            System.out.println(student.getProfile().toString() + " is not in the roster.");
            return;
        } else if (student instanceof NonResident) {
            System.out.println(student.getProfile().toString() + " (" + getStatus(student) + ") is not eligible for the scholarship.");
            return;
        } else if (student_enroll.getCredits() < 12) {
            System.out.println(student.getProfile().toString() + " part time student is not eligible for the scholarship.");
            return;
        }

        ((Resident)student).setScholarship(scholarship_value);
        System.out.println(student.getProfile().toString() + ": scholarship amount updated.");
    }

    /**
     * Print the enrollment list.
     */
    private void printEnrollment() {
        EnrollStudent[] students = enrollment.getEnrollStudents();

        System.out.println("** Enrollment **");

        for (int i = 0; i < enrollment.getSize(); i++) {
            System.out.println(students[i].getProfile().toString() + ": credits enrolled: " + students[i].getCredits());
        }

        System.out.println("* end of enrollment *");
    }

    /**
     * Print a list of enrolled students and their tuition due.
     */
    private void printTuition() {
        EnrollStudent[] students = enrollment.getEnrollStudents();

        System.out.println("** Tuition due **");

        for (int i = 0; i < enrollment.getSize(); i++) {
            Student student = roster.getStudent(new Resident(students[i].getProfile(), Major.CS, 0));
            if (student != null) {
                DecimalFormat formatter = new DecimalFormat("###,###.00");
                String tuition = formatter.format(student.tuitionDue(students[i].getCredits()));
                System.out.println(student.getProfile().toString() + " (" + getStatus(student) + ") enrolled " + students[i].getCredits() + " credits: tuition due: $" + tuition);
            }
        }

        System.out.println("* end of tuition due *");
    }

    /**
     * For all enrolled students, add the amount of enrolled credits they have to their credits completed at the end
     * of the semester.
     */
    private void semesterEnd() {
        EnrollStudent[] students = enrollment.getEnrollStudents();

        System.out.println("Credit completed has been updated.");
        System.out.println("** list of students eligible for graduation **");

        for (int i = 0; i < enrollment.getSize(); i++) {
            Student student = roster.getStudent(new Resident(students[i].getProfile(), Major.CS, 0));
            if (student != null) {
                student.setCreditCompleted(students[i].getCredits() + student.getCreditCompleted());

                if (student.getCreditCompleted() >= Constants.GRADUATION_CREDITS) {
                    System.out.printf(student + "\n");
                }
            }
        }
    }
}
