package src;

/**
 * Holds all the important information that needs to be tracked for each Student in the roster.
 * Includes the Profile of the Student: their name and date of birth.
 * Also includes additional information on their declared majors and how many credit they've completed.
 * @author Jason Guo, Russel Rivera
 */
public class Student implements Comparable<Student>{
    private Profile profile;
    private Major major;
    private int creditCompleted;

    /**
     * Creates a Student object with the given parameters.
     * @param profile Profile of the student
     * @param major Major of the student
     * @param creditCompleted How many credits the student has completed
     */
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    /**
     * Getter for the student's profile.
     * @return Returns the profile (Profile) of the student
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Getter for the major.
     * @return Returns the major (enum) of the student
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Setter for the major.
     * @param major New major for the student
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * Getter for credits completed.
     * @return returns the number of credits completed
     */
    public int getCreditCompleted() {
        return creditCompleted;
    }

    /**
     * Obtains the seniority of a student based on how many credits they've completed.
     * @return Returns a string that identifies the student's seniority
     */
    public String getSeniority() {
        if (creditCompleted < 30) {
            return "Freshman";
        } else if (creditCompleted < 60) {
            return "Sophomore";
        } else if (creditCompleted < 90) {
            return "Junior";
        } else {
            return "Senior";
        }
    }

    /**
     * Overrides compareTo method, compares the portfolios of the two students.
     * @param student the object to be compared.
     * @return Returns 1, 0, or -1 depending on the portfolios of the compared students
     */
    @Override
    public int compareTo(Student student) {
        return profile.compareTo(student.profile);
    }

    /**
     * Overrides the toString method.
     * @return Returns the name, d.o.b, major code, major, school, credits completed, and seniority of the student
     */
    @Override
    public String toString() {
        return (profile.toString() + " (" + major.code + " " + major + " " + major.school + ") credits completed: " + creditCompleted + " (" + getSeniority() + ")");
    }

    /**
     * Overrides the equals method.
     * @param obj object to be compared
     * @return Returns true if the two students are the same, and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student) {
            Student student = (Student)obj;
            if(profile.equals(student.profile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Testbed Main.
     * Used to test the functionality of the Student class.
     * @param args
     */
    public static void main(String[] args) {

    }
}
